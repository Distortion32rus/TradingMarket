package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="THRESHOLD_VALUES_TABLE", sequenceName="THRESHOLD_VALUES_TABLE_GENERATOR")
public class ThresholdValuesTable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="THRESHOLD_VALUES_TABLE")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "threshold_values_id")
    private ThresholdValues thresholdValues;

    @Enumerated(EnumType.STRING)
    private ThresholdCategories thresholdCategory;

    private int maxPrice, minPrice, deviation;


    public ThresholdValuesTable(ThresholdValues thresholdValues) {
        this.thresholdValues = thresholdValues;
    }

    public ThresholdValuesTable(ThresholdValues thresholdValues, ThresholdCategories thresholdCategory, int maxPrice, int minPrice, int deviation) {
        this.thresholdValues = thresholdValues;
        this.thresholdCategory = thresholdCategory;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.deviation = deviation;
    }

    public ThresholdValuesTable() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ThresholdCategories getThresholdCategory() {
        return thresholdCategory;
    }

    public void setThresholdCategory(ThresholdCategories thresholdCategory) {
        this.thresholdCategory = thresholdCategory;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getDeviation() {
        return deviation;
    }

    public void setDeviation(int deviation) {
        this.deviation = deviation;
    }



    public ThresholdValues getThresholdValues() {
        return thresholdValues;
    }

    public void setThresholdValues(ThresholdValues thresholdValues) {
        this.thresholdValues = thresholdValues;
    }

}
