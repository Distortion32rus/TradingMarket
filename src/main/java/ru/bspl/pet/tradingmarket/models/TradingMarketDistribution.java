package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TradingMarketDistribution {

    @EmbeddedId
    private TradingMarketDistributionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("trading_market_id")
    private TradingMarket tradingMarket;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("assortment_plan_id")
    private AssortmentPlan assortmentPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("counterparty_id")
    private Counterparty counterparty;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("counterparts_nomenclature_id")
    private CounterpartsNomenclature counterpartsNomenclature;

    private int demandQNT, roundDemandQNT, onStockQNT, multiplicityOf, counterpartiesStock;
    private double counterpartiesPrice, convertedPrice, bestPrice, salesSpeed;
    private Date  shelfLife;
    private boolean inPriceControl, nomenComparitionControl, shelfLifeControl, multiplicityControl, minSupplBalanceControl, maxStockControl, bestPriceControl;

    public TradingMarketDistribution() {
    }

    public TradingMarketDistribution(TradingMarketDistributionId id, int demandQNT, int roundDemandQNT, int onStockQNT, int multiplicityOf, int counterpartiesStock, double counterpartiesPrice, double convertedPrice, double bestPrice, double salesSpeed, Date shelfLife, boolean inPriceControl, boolean nomenComparitionControl, boolean shelfLifeControl, boolean multiplicityControl, boolean minSupplBalanceControl, boolean maxStockControl, boolean bestPriceControl) {
        this.id = id;
        this.demandQNT = demandQNT;
        this.roundDemandQNT = roundDemandQNT;
        this.onStockQNT = onStockQNT;
        this.multiplicityOf = multiplicityOf;
        this.counterpartiesStock = counterpartiesStock;
        this.counterpartiesPrice = counterpartiesPrice;
        this.convertedPrice = convertedPrice;
        this.bestPrice = bestPrice;
        this.salesSpeed = salesSpeed;
        this.shelfLife = shelfLife;
        this.inPriceControl = inPriceControl;
        this.nomenComparitionControl = nomenComparitionControl;
        this.shelfLifeControl = shelfLifeControl;
        this.multiplicityControl = multiplicityControl;
        this.minSupplBalanceControl = minSupplBalanceControl;
        this.maxStockControl = maxStockControl;
        this.bestPriceControl = bestPriceControl;
    }

    public TradingMarketDistributionId getId() {
        return id;
    }

    public void setId(TradingMarketDistributionId id) {
        this.id = id;
    }

    public TradingMarket getTradingMarket() {
        return tradingMarket;
    }

    public void setTradingMarket(TradingMarket tradingMarket) {
        this.tradingMarket = tradingMarket;
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

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
    }

    public CounterpartsNomenclature getCounterpartsNomenclature() {
        return counterpartsNomenclature;
    }

    public void setCounterpartsNomenclature(CounterpartsNomenclature counterpartsNomenclature) {
        this.counterpartsNomenclature = counterpartsNomenclature;
    }

    public int getDemandQNT() {
        return demandQNT;
    }

    public void setDemandQNT(int demandQNT) {
        this.demandQNT = demandQNT;
    }

    public int getRoundDemandQNT() {
        return roundDemandQNT;
    }

    public void setRoundDemandQNT(int roundDemandQNT) {
        this.roundDemandQNT = roundDemandQNT;
    }

    public int getOnStockQNT() {
        return onStockQNT;
    }

    public void setOnStockQNT(int onStockQNT) {
        this.onStockQNT = onStockQNT;
    }

    public int getMultiplicityOf() {
        return multiplicityOf;
    }

    public void setMultiplicityOf(int multiplicityOf) {
        this.multiplicityOf = multiplicityOf;
    }

    public int getCounterpartiesStock() {
        return counterpartiesStock;
    }

    public void setCounterpartiesStock(int counterpartiesStock) {
        this.counterpartiesStock = counterpartiesStock;
    }

    public double getCounterpartiesPrice() {
        return counterpartiesPrice;
    }

    public void setCounterpartiesPrice(double counterpartiesPrice) {
        this.counterpartiesPrice = counterpartiesPrice;
    }

    public double getConvertedPrice() {
        return convertedPrice;
    }

    public void setConvertedPrice(double convertedPrice) {
        this.convertedPrice = convertedPrice;
    }

    public double getBestPrice() {
        return bestPrice;
    }

    public void setBestPrice(double bestPrice) {
        this.bestPrice = bestPrice;
    }

    public double getSalesSpeed() {
        return salesSpeed;
    }

    public void setSalesSpeed(double salesSpeed) {
        this.salesSpeed = salesSpeed;
    }

    public Date getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Date shelfLife) {
        this.shelfLife = shelfLife;
    }

    public boolean isInPriceControl() {
        return inPriceControl;
    }

    public void setInPriceControl(boolean inPriceControl) {
        this.inPriceControl = inPriceControl;
    }

    public boolean isNomenComparitionControl() {
        return nomenComparitionControl;
    }

    public void setNomenComparitionControl(boolean nomenComparitionControl) {
        this.nomenComparitionControl = nomenComparitionControl;
    }

    public boolean isShelfLifeControl() {
        return shelfLifeControl;
    }

    public void setShelfLifeControl(boolean shelfLifeControl) {
        this.shelfLifeControl = shelfLifeControl;
    }

    public boolean isMultiplicityControl() {
        return multiplicityControl;
    }

    public void setMultiplicityControl(boolean multiplicityControl) {
        this.multiplicityControl = multiplicityControl;
    }

    public boolean isMinSupplBalanceControl() {
        return minSupplBalanceControl;
    }

    public void setMinSupplBalanceControl(boolean minSupplBalanceControl) {
        this.minSupplBalanceControl = minSupplBalanceControl;
    }

    public boolean isMaxStockControl() {
        return maxStockControl;
    }

    public void setMaxStockControl(boolean maxStockControl) {
        this.maxStockControl = maxStockControl;
    }

    public boolean isBestPriceControl() {
        return bestPriceControl;
    }

    public void setBestPriceControl(boolean bestPriceControl) {
        this.bestPriceControl = bestPriceControl;
    }
}
