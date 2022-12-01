package ru.bspl.pet.tradingmarket.models;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TradingMarketStoresId implements Serializable {

    private static final long serialVersionUID = 7159914746894969820L;
    @ManyToOne(fetch = FetchType.LAZY)
    private TradingMarket tradingMarket;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    public TradingMarketStoresId() {
    }

    public TradingMarketStoresId(TradingMarket tradingMarket, Store store) {
        this.tradingMarket = tradingMarket;
        this.store = store;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradingMarketStoresId that = (TradingMarketStoresId) o;
        return Objects.equals(tradingMarket, that.tradingMarket) && Objects.equals(store, that.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradingMarket, store);
    }
}
