package ru.bspl.pet.tradingmarket.models;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TradingMarketDistributionId implements Serializable {

    @ManyToOne
    private TradingMarket tradingMarket;

    @ManyToOne
    private Store store;

    @ManyToOne
    private AssortmentPlan assortmentPlan;

    @ManyToOne
    private Counterparty counterparty;

    @ManyToOne
    private CounterpartsNomenclature counterpartsNomenclature;

    public TradingMarketDistributionId() {
    }

    public TradingMarketDistributionId(TradingMarket tradingMarket, Store store, AssortmentPlan assortmentPlan, Counterparty counterparty, CounterpartsNomenclature counterpartsNomenclature) {
        this.tradingMarket = tradingMarket;
        this.store = store;
        this.assortmentPlan = assortmentPlan;
        this.counterparty = counterparty;
        this.counterpartsNomenclature = counterpartsNomenclature;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradingMarketDistributionId that = (TradingMarketDistributionId) o;
        return Objects.equals(tradingMarket, that.tradingMarket) && Objects.equals(store, that.store) && Objects.equals(assortmentPlan, that.assortmentPlan) && Objects.equals(counterparty, that.counterparty) && Objects.equals(counterpartsNomenclature, that.counterpartsNomenclature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradingMarket, store, assortmentPlan, counterparty, counterpartsNomenclature);
    }
}
