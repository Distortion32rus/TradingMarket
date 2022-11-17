package ru.bspl.pet.tradingmarket.models;

public enum ThresholdCategories {
    BESTPRICEDEVIATION ("BestPriceDeviation"), //Отклонение от лучшей цены
    EXPIRATIONDATEDEVIATION ("ExpirationDateDeviation"), //Отклонение по сроку годности
    MULTIPLICITYDEVIATION ("MultiplicityDeviation"), //Отклонение по кратности поставки
    MINSUPPLBALANCE ("MinSupplBalance"), //Минимальный остаток у поставщика
    MAXSTOCK ("MaxStock");//Максимальный товарный запас ;

    private final String name;

    ThresholdCategories(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
