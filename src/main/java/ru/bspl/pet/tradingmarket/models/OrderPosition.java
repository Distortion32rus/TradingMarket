package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class OrderPosition {

    @EmbeddedId
    private OrderPositionId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("assortment_plan_id")
    private AssortmentPlan assortmentPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("counterparts_nomenclature_id")
    private CounterpartsNomenclature counterpartsNomenclature;

    private int quantity;

    private Date shelfLife;

    private double counterpartiesPrice;

    public OrderPosition() {
    }

    public OrderPositionId getId() {
        return id;
    }

    public void setId(OrderPositionId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getCounterpartiesPrice() {
        return counterpartiesPrice;
    }

    public void setCounterpartiesPrice(double counterpartiesPrice) {
        this.counterpartiesPrice = counterpartiesPrice;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Date shelfLife) {
        this.shelfLife = shelfLife;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPosition that = (OrderPosition) o;
        return quantity == that.quantity && Double.compare(that.counterpartiesPrice, counterpartiesPrice) == 0 && Objects.equals(id, that.id) && Objects.equals(order, that.order) && Objects.equals(assortmentPlan, that.assortmentPlan) && Objects.equals(counterpartsNomenclature, that.counterpartsNomenclature) && Objects.equals(shelfLife, that.shelfLife);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, assortmentPlan, counterpartsNomenclature, quantity, shelfLife, counterpartiesPrice);
    }
}
