package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TraidingMarketDistribution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "traiding_market_id")
    private TraidingMarket traidingMarket;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;

    @ManyToOne
    @JoinColumn(name = "assortment_plan_id")
    private AssortmentPlan assortmentPlan;

    @ManyToOne
    @JoinColumn(name = "nomenclature_id")
    private Nomenclature nomenclature;

    private int demandQNT, orderQNT, onStockQNT, onTransferQNT, multiplicityOf, counterpartysStock;
    private double counterpartysPrice, quotedPrice, avgPrice;
    private Date  shelfLife;
    private boolean bestPriceCheck, shelfLifeCheck, counterpartysStockCheck, multiplicityOfCheck, maxStockCheck, toOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDemandQNT() {
        return demandQNT;
    }

    public void setDemandQNT(int demandQNT) {
        this.demandQNT = demandQNT;
    }

    public int getOrderQNT() {
        return orderQNT;
    }

    public void setOrderQNT(int orderQNT) {
        this.orderQNT = orderQNT;
    }

    public int getOnStockQNT() {
        return onStockQNT;
    }

    public void setOnStockQNT(int onStockQNT) {
        this.onStockQNT = onStockQNT;
    }

    public int getOnTransferQNT() {
        return onTransferQNT;
    }

    public void setOnTransferQNT(int onTransferQNT) {
        this.onTransferQNT = onTransferQNT;
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

    public double getCounterpartysPrice() {
        return counterpartysPrice;
    }

    public void setCounterpartysPrice(double counterpartysPrice) {
        this.counterpartysPrice = counterpartysPrice;
    }

    public double getQuotedPrice() {
        return quotedPrice;
    }

    public void setQuotedPrice(double quotedPrice) {
        this.quotedPrice = quotedPrice;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Date getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Date shelfLife) {
        this.shelfLife = shelfLife;
    }

    public boolean isBestPriceCheck() {
        return bestPriceCheck;
    }

    public void setBestPriceCheck(boolean bestPriceCheck) {
        this.bestPriceCheck = bestPriceCheck;
    }

    public boolean isShelfLifeCheck() {
        return shelfLifeCheck;
    }

    public void setShelfLifeCheck(boolean shelfLifeCheck) {
        this.shelfLifeCheck = shelfLifeCheck;
    }

    public boolean isCounterpartysStockCheck() {
        return counterpartysStockCheck;
    }

    public void setCounterpartysStockCheck(boolean counterpartysStockCheck) {
        this.counterpartysStockCheck = counterpartysStockCheck;
    }

    public boolean isMultiplicityOfCheck() {
        return multiplicityOfCheck;
    }

    public void setMultiplicityOfCheck(boolean multiplicityOfCheck) {
        this.multiplicityOfCheck = multiplicityOfCheck;
    }

    public boolean isMaxStockCheck() {
        return maxStockCheck;
    }

    public void setMaxStockCheck(boolean maxStockCheck) {
        this.maxStockCheck = maxStockCheck;
    }

    public boolean isToOrder() {
        return toOrder;
    }

    public void setToOrder(boolean toOrder) {
        this.toOrder = toOrder;
    }

    public AssortmentPlan getAssortmentPlan() {
        return assortmentPlan;
    }

    public void setAssortmentPlan(AssortmentPlan assortmentPlan) {
        this.assortmentPlan = assortmentPlan;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public TraidingMarket getTraidingMarket() {
        return traidingMarket;
    }

    public void setTraidingMarket(TraidingMarket traidingMarket) {
        this.traidingMarket = traidingMarket;
    }
}
