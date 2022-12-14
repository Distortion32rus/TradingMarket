package ru.bspl.pet.tradingmarket.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class PriceList {
    @EmbeddedId
    private PriceListId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("price_zone_id")
    private PriceZone priceZone;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("counterparty_id")
    private Counterparty counterparty;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("counterparts_nomenclature_id")
    private CounterpartsNomenclature counterpartsNomenclature;

    private double counterpartysPrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date shelfLife;
    private int multiplicityOf;
    private int counterpartysStock;

    public PriceList() {
    }

    public PriceList(PriceListId id, double counterpartysPrice, Date shelfLife, int multiplicityOf, int counterpartysStock) {
        this.id = id;
        this.counterpartysPrice = counterpartysPrice;
        this.shelfLife = shelfLife;
        this.multiplicityOf = multiplicityOf;
        this.counterpartysStock = counterpartysStock;
    }

    public PriceListId getId() {
        return id;
    }

    public void setId(PriceListId id) {
        this.id = id;
    }

    public double getCounterpartysPrice() {
        return counterpartysPrice;
    }

    public void setCounterpartysPrice(double counterpartysPrice) {
        this.counterpartysPrice = counterpartysPrice;
    }

    public Date getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Date shelfLife) {
        this.shelfLife = shelfLife;
    }

    public int getMultiplicityOf() {
        return multiplicityOf;
    }

    public void setMultiplicityOf(int multiplicityOf) {
        this.multiplicityOf = multiplicityOf;
    }

    public int getCounterpartysStock() {
        return counterpartysStock;
    }

    public void setCounterpartysStock(int counterpartysStock) {
        this.counterpartysStock = counterpartysStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceList priceList = (PriceList) o;
        return Double.compare(priceList.counterpartysPrice, counterpartysPrice) == 0 && multiplicityOf == priceList.multiplicityOf && counterpartysStock == priceList.counterpartysStock && Objects.equals(id, priceList.id) && Objects.equals(priceZone, priceList.priceZone) && Objects.equals(counterparty, priceList.counterparty) && Objects.equals(counterpartsNomenclature, priceList.counterpartsNomenclature) && Objects.equals(shelfLife, priceList.shelfLife);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, priceZone, counterparty, counterpartsNomenclature, counterpartysPrice, shelfLife, multiplicityOf, counterpartysStock);
    }
}
