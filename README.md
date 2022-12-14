# Торговая площадка

## Что это такое?
  Сервис, который позволяет автоматизировать закупку товара у поставщиков.
  В рамках осуществления закупок существует ряд определяющих факторов, таких как:
+ Цена товара
+ Срок годности товара
+ Кратность отгрузки поставщиком
+ Отсрочка платежа по договору и процент, выплачиваемый за ее использование
+ Остаточное количество товара на складе поставщика
+ Минимальная сумма заказа у поставщика

К тому же, наименования и коды номенклатуры поставщиков могут отличаться от наименований и кодов номенклатуры организации, для которой осуществляется закупка.
Цель данного сервиса автоматизировать выбор лучшего предложения среди прайс-листов поставщиков, минимизировать риски просрочки товар до его продажи торговой точкой,
а также минимизировать переплату за перезаказ по кратности.
  
## Используемые технологии:
+ Java 8
+ Spring boot
+ Spring data JPA
+ Spring security
+ PostgreSQL
+ Thymeleaf

## Как это работает?
      
  Сервису предоставляются следующие данные:
+ По API
  + От поставщиков: прайс листы с информацией о наименовании, цене, сроке годности товара, а также о кратности его отгрузки и его остатке на складе
  + От сервиса по расчету потребности складов в товаре: наименование товара, остаточное количество, количество к закупке и скорость продаж товара  
+ Пользователями:
  + Соглашение, заключенное между поставщиком и организацией, которой принадлежит склад. Включает в себя отсрочку платежа, процент за ее использование и минимальную сумму заказа
  + Сопоставление номенклатуры поставщика с номенклатурой внутренней организации
  + Пороговые ограничения для торгов

Пользователем создается документ "Торговая площадка" (сущность TradingMarket), добавляются склады, по котором будут осуществлены торги и запускается расчет.
Сервис отбирает потребности в товаре по указанным складам, ищет соглашения, заключенные между организациями складов и поставщиками. Сопоставляет номенклатуру в потребности с номенклатурами отобранных контрагентов, ищет предложения по ним в прайсах. К найденым позициям в прайсе накладываются ограничения, заданные пользователем для торгов. 
Исходя из наложенных ограний высчитываются отказы (контроли):
+ Наличие в прайс-листе поставщика
+ Наличие сопоставленной номенклатуры поставщика
+ Контроль остаточного срока годности - проверяется, что с текущей скоростью продаж последняя продажа товара будет осуществлена с запасом по сроку годности в N дней, где N - количество дней, указанное в пороговых ограничениях для торгов
+ Контроль минимального остатка на складе поставщика - проверяется, что количество в прайсе больше, чем пороговое значение, указанное в ограничениях для торгов
+ Контроль максимального остатка торговой точки - проверяется, что при заказе с учетом остатка товара на складе торговой точки, суммарный товарный запас не будет превышать N дней, где N - количество дней, указанное в пороговых ограничениях для торгов
+ Контроль отклонения от лучшей цены - проверяется, что цена поставщика не превышает лучшую цену среди всех предложения на N процентов, где N - количество дней, указанное в пороговых ограничениях для торгов
+ Контроль превышения по кратности - проверяется 2 условия
  + с текущей скоростью продаж и учетом кратности последняя продажа товара будет осуществлена с запасом по сроку годности в N дней, где N - количество дней, указанное в пороговых ограничениях для торгов
  + при заказе с учетом кратности и остатка товара на складе торговой точки, суммарный товарный запас не будет превышать N дней, где N - количество дней, указанное в пороговых ограничениях для торгов

Из предложений, прошедших все ограничения сервис выбирает лучшие в разрезе приведенной цены и остатка на складе поставщика и формуирет заказы.
Приведенная цена - цена с учетом переплаты за кратность поставки и отсрочку платежа.

## Коротко о сущностях и их свойствах:
+ PriceZone (ценовая зона) - территориальное подразделение. Торговые точки, находящиеся в разных ценовых зонах могут обслуживаться разными складами поставщика, следовательно и предложения в прайсах для этих торговых точек будут отличаться.
  + id - идентификатор
  + name - наименование
  + description - описание
+ BusinessUnit - подразделение по брэндам. планируется использоваться для отборов складов.
  + id - идентификатор
  + name - наименование
  + description - описание
+ Organization - организация (юридическое лицо) 
  + id - идентификатор
  + name - наименование
  + description - описание
+ Counterparty - поставщик
  + id - идентификатор
  + name - наименование
  + description - описание  
+ Agreement - соглашение между поставщикои и организацией
  + id - идентификатор
  + counterparty - ссылка на поставщика (ManyToOne)
  + organization - ссылка на организацию (ManyToOne) 
  + defermentOfPayment - отсрочка платежа (в днях)
  + defermentRate - процентная ставка по отсрочке
  + minFirstOrderAmoung - минимальная сумма первичного (в течении дня) заказа
  + minSecondaryOrderAmoung - минимальная сумма последующих заказов
+ Store - склад (торговая точка)
  + id - идентификатор
  + name - наименование
  + description - описание
  + priceZone - ссылка на ценовую зону, в которой находится склад (ManyToOne)
  + businessUnit - ссылка на бизнес единицу, в которой находится склад (ManyToOne)
  + organization - ссылка на организацию, к которой принадлежит склад (ManyToOne)
  + thresholdValues - ссылка на ведомость пороговых значений, которые будут применяться к складу при расчете (ManyToOne)
+ ThresholdValues - ведомость пороговых значений (шапка)
  + id - идентификатор
  + name - наименование
+ ThresholdValuesTable - пороговые значение (позиции ведомости)
  + id - идентификатор
  + thresholdValues - ссылка на шапку ведомости
  + thresholdCategory - категория ограничени (перечисление)  
  + minPrice - минимальная цена (все отклонения задаются в разрезе ценовых диапазонов, например, просрочка дорогостоящего и дешевого довара влекут за собой различные финансовые потери, соответствено и ограничения для этих ценовых диапазонов будут разные)
  + maxPrice - максимальная цена
  + deviation - отклонение (пороговое значение)
+ Nomenclature - номенклатура (наименование товара, в котором производится его учет)
  + id - идентификатор
  + name - наименование
  + assortmentPlan - ссылка на ассортиментный план (ManyToOne) 
+ AssortmentPlan - ассортиментный план (уровень абстракции над номенклатурой без учета производителя)
  + id - идентификатор
  + name - наименование
  + typeOfAssortmentPlans - ссылка на тип ассортиментного плана (ManyToOne)
+ TypeOfAssortmentPlans - типы ассортиментных планов (товарные категории), планируется использоваться для учета cashback-ов поставщиков
  + id - идентификатор
  + name - наименование  
+ CounterpartsNomenclature - номенклатура поставщика
  + id - идентификатор (код поставщика)
  + name - наименование (как у поставщика)
  + multiplicityOf - кратность поставки
  + nomenclature - ссылка на номенклатуру организации (ManyToOne)
  + counterparty - ссылка на поставщика - владельца номенклатуры (ManyToOne)
+ Demand - потребность в товаре
  + id - EmbeddedId (составной ключ из склада и ассортиментного плана)  
  + store - ссылка на склад (ManyToOne)
  + assortmentPlan - ссылка на ассортиментный план (ManyToOne)
  + demandQNT - требуемое количество 
  + stockQNT - остаточное количество на складе 
  + salesSpeed - скорость продаж этой позиции
+ PriceList - прайс лист поставщика 
  + id - EmbeddedId (составной ключ из ценовой зоны, поставщика и номенклатуры поставщика) 
  + priceZone - ссылка на ценовую зону (ManyToOne)
  + counterparty - ссылка на поставщика 
  + counterpartsNomenclature - ссылка на номенклатуру поставщика
  + counterpartysPrice - цена поставщика
  + shelfLife - срок годности 
  + multiplicityOf - кратность поставки
  + counterpartysStock - остаток на складе поставщика
+ TradingMarket - торговая площадка (шапка)
  + id - идентификатор
  + name - наименование 
+ TradingMarketStores - список складов, по которым будут осуществлены торги
  + id - EmbeddedId (составной ключ из склада и торговой площадки)
  + tradingMarket - ссылка на торговую площадку (ManyToOne)
  + store - ссылка на склад (ManyToOne)
+ TradingMarketDistribution - распределение торговой площадки (результат расчета)
  + id - EmbeddedId (составной ключ из торговой площадки, склада, ассортиментного плана, поставщика и номенклатуры поставщика)
  + tradingMarket - ссылка на торговую площадку (ManyToOne)
  + store - ссылка на склад (ManyToOne)
  + assortmentPlan - ссылка на ассортиментный план (ManyToOne)
  + counterparty - ссылка на поставщика (ManyToOne) 
  + counterpartsNomenclature - ссылка на номенклатуру поставщика (ManyToOne)
  + demandQNT - количество в потребности
  + roundDemandQNT - потребность, округленная до кратности
  + onStockQNT - количество на остатках склада
  + multiplicityOf - кратность поставки
  + counterpartiesStock - остаток поставщика
  + counterpartiesPrice - цена поставщика 
  + convertedPrice - приведенная цена поставщика (с учетом переплаты за кратность и отсрочку платежа)
  + bestPrice - лучшая цена среди прайсов поставщиков, участвовавших в торгах
  + salesSpeed - скорость продаж (уп в день)
  + shelfLife - срок годности
  + inPriceControl - контроль наличий позиции в прайсе поставщика (булево)
  + nomenComparitionControl - контроль наличия сопоставленной номенклатуры поставщика (булево)
  + shelfLifeControl - контроль остаточного срока годности (булево)
  + multiplicityControl - контроль отклонения по кратности (булево)
  + minSupplBalanceControl - контроль минимального остатка поставщика (булево)
  + maxStockControl - контроль максимального остатка торговой точки (булево)
  + bestPriceControl - контроль отклонения от лучшей цены (булево)
+ Order - заказ (шапка)
  + id - идентификатор
  + store - ссылка на склад (ManyToOne)  
  + tradingMarket - ссылка на торговую площадку (ManyToOne)
  + counterparty - ссылка на поставщика (ManyToOne) 
  + dateOfCreation - дата заказа 
+ OrderPosition - позиция заказа
  + id - ManyToOne (составной ключ из заказа, ассортиментного плана и номенклатуры поставщика)
  + order - ссылка на заказ  (ManyToOne) 
  + assortmentPlan - ссылка на ассортиментный план (ManyToOne) 
  + counterpartsNomenclature - ссылка на номенклатуру поставщика  (ManyToOne) 
  + quantity - количество 
  + shelfLife - срок годности
+ ThresholdCategories - категории пороговых значений
  + BESTPRICEDEVIATION - отклонение от лучшей цены, %
  + EXPIRATIONDATEDEVIATION - запас до истечения срока годности, дни (после продажи последней упаковки товара с учетом текущей скорости продаж) 
  + MAXSTOCK - максимальный товарный запас, дни
  + MULTIPLICITYDEVIATION - максимальный товарный запас, с учетом кратности поставки, дни
  + MINSUPPLBALANCE - минимальный остаток поставщика, дни 
   
## Реализовано:
- [X] CRUD для настройки сервиса и заполнения БД
- [X] API для получение потребности от сервиса расчета потребности/розничного ПО
- [X] API для получения прайс-листов от поставщиков
- [X] Распределение потребности среди предложений поставщиков (логика реализована на стороне БД - функция *func_trading_market_calc*)

## В процессе:
- [ ] Многопоточное формирование заказов с учетом остатка в прайс-листах поставщиков

## В планах:
- [ ] API для получения поставщиками готовых заказов
- [ ] Валидация при помощи  hibernate validator
- [ ] Пагинация и сортировка
- [ ] Реализация дозаказа до минимальной суммы поставщика
- [ ] Пользовательский интерфейс для принудительного согласования отказов торговой площадки
- [ ] Учет cashback-ов поставщиков 
- [ ] Архивирование прайс-листов
- [ ] Контроль роста цен на товар в прайс-листах поставщиков
- [ ] Фронт на JS и его фрэймворках  