package ru.bspl.pet.tradingmarket.models;

public enum ThresholdCategories {
    BESTPRICEDEVIATION, //Отклонение от лучшей цены
    EXPIRATIONDATEDEVIATION, //Отклонение по сроку годности
    MULTIPLICITYDEVIATION, //Отклонение по кратности поставки
    MINSUPPLBALANCE, //Минимальный остаток у поставщика
    MAXSTOCK //Максимальный товарный запас
}
