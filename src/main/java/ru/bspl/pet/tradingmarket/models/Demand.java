package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Demand {

    @EmbeddedId
    private DemandId id;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("assortment_plan_id")
    private AssortmentPlan assortmentPlan;

    private int demandQNT, stockQNT;

    private double salesSpeed;

    public Demand(DemandId id, int demandQNT, int stockQNT, double salesSpeed) {
        this.id = id;
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

    public DemandId getId() {
        return id;
    }

    public void setId(DemandId demandId) {
        this.id = demandId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demand demand = (Demand) o;
        return demandQNT == demand.demandQNT && stockQNT == demand.stockQNT && Double.compare(demand.salesSpeed, salesSpeed) == 0 && Objects.equals(id, demand.id) && Objects.equals(store, demand.store) && Objects.equals(assortmentPlan, demand.assortmentPlan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, store, assortmentPlan, demandQNT, stockQNT, salesSpeed);
    }
}
