package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;

@Entity
@IdClass(DemandId.class)
public class Demand {
    @Id
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    @Id
    @ManyToOne
    @JoinColumn(name = "assortment_plan_id")
    private AssortmentPlan assortmentPlan;

    private int demandQNT, stockQNT;

    private double salesSpeed;

    public Demand(Store store, AssortmentPlan assortmentPlan, int demandQNT, int stockQNT, double salesSpeed) {
        this.store = store;
        this.assortmentPlan = assortmentPlan;
        this.demandQNT = demandQNT;
        this.stockQNT = stockQNT;
        this.salesSpeed = salesSpeed;
    }

    public Demand() {
    }

    public int getDemandQNT() {
        return demandQNT;
    }

    public void setDemandQNT(int demandQNT) {
        this.demandQNT = demandQNT;
    }

    public int getStockQNT() {
        return stockQNT;
    }

    public void setStockQNT(int stockQNT) {
        this.stockQNT = stockQNT;
    }

    public double getSalesSpeed() {
        return salesSpeed;
    }

    public void setSalesSpeed(double salesSpeed) {
        this.salesSpeed = salesSpeed;
    }

    public AssortmentPlan getAssortmentPlan() {
        return assortmentPlan;
    }

    public void setAssortmentPlan(AssortmentPlan assortmentPlan) {
        this.assortmentPlan = assortmentPlan;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }


    public void setId(DemandId demandId) {
        this.store = demandId.getStore();
        this.assortmentPlan = demandId.getAssortmentPlan();
    }
}
