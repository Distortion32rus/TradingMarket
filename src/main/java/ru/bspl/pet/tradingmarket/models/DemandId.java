package ru.bspl.pet.tradingmarket.models;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DemandId implements Serializable {

    @ManyToOne( fetch = FetchType.LAZY)
    private Store store;
    @ManyToOne( fetch = FetchType.LAZY)
    private AssortmentPlan assortmentPlan;

    public DemandId() {
    }

    public DemandId(Store store, AssortmentPlan assortmentPlan) {
        this.store = store;
        this.assortmentPlan = assortmentPlan;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public AssortmentPlan getAssortmentPlan() {
        return assortmentPlan;
    }

    public void setAssortmentPlan(AssortmentPlan assortmentPlan) {
        this.assortmentPlan = assortmentPlan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemandId demandId = (DemandId) o;
        return Objects.equals(store, demandId.store) && Objects.equals(assortmentPlan, demandId.assortmentPlan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(store, assortmentPlan);
    }
}
