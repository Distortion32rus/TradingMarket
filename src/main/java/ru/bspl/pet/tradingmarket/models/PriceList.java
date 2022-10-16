package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(PriceListId.class)
public class PriceList {


    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_zone_id")
    private PriceZone priceZone;
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "counterparts_nomenclature_id")
    private CounterpartsNomenclature counterpartsNomenclature;
    private double counterpartysPrice;
    private Date shelfLife;
    private int multiplicityOf;
    private int counterpartysStock;

    public PriceList(CounterpartsNomenclature counterpartsNomenclature, Counterparty counterparty, double counterpartysPrice, Date shelfLife, int multiplicityOf, int counterpartysStock, PriceZone priceZone) {
        this.counterpartsNomenclature = counterpartsNomenclature;
        this.counterparty = counterparty;
        this.counterpartysPrice = counterpartysPrice;
        this.shelfLife = shelfLife;
        this.multiplicityOf = multiplicityOf;
        this.counterpartysStock = counterpartysStock;
        this.priceZone = priceZone;
    }

    public PriceList() {

    }

    public CounterpartsNomenclature getCounterpartsNomenclature() {
        return counterpartsNomenclature;
    }

    public void setCounterpartsNomenclature(CounterpartsNomenclature counterpartsNomenclature) {
        this.counterpartsNomenclature = counterpartsNomenclature;
    }
    public PriceZone getPriceZone() {
        return priceZone;
    }
    public void setPriceZone(PriceZone priceZone) {
        this.priceZone = priceZone;
    }
    public Counterparty getCounterparty() {
        return counterparty;
    }
    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
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

    public void setId(PriceListId priceListId) {
        this.counterparty = priceListId.getCounterparty();
        this.priceZone = priceListId.getPriceZone();
        this.counterpartsNomenclature = priceListId.getCounterpartsNomenclature();
    }
}
