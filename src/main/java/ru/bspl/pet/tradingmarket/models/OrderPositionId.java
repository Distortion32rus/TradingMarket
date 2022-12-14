package ru.bspl.pet.tradingmarket.models;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderPositionId implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private AssortmentPlan assortmentPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    private CounterpartsNomenclature counterpartsNomenclature;

    public OrderPositionId() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public AssortmentPlan getAssortmentPlan() {
        return assortmentPlan;
    }

    public void setAssortmentPlan(AssortmentPlan assortmentPlan) {
        this.assortmentPlan = assortmentPlan;
    }

    public CounterpartsNomenclature getCounterpartsNomenclature() {
        return counterpartsNomenclature;
    }

    public void setCounterpartsNomenclature(CounterpartsNomenclature counterpartsNomenclature) {
        this.counterpartsNomenclature = counterpartsNomenclature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPositionId that = (OrderPositionId) o;
        return Objects.equals(order, that.order) && Objects.equals(assortmentPlan, that.assortmentPlan) && Objects.equals(counterpartsNomenclature, that.counterpartsNomenclature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, assortmentPlan, counterpartsNomenclature);
    }
}
