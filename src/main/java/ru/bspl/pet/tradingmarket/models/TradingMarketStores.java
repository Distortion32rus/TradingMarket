package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class TradingMarketStores {

    @EmbeddedId
    private TradingMarketStoresId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("trading_market_id")
    private TradingMarket tradingMarket;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("store_id")
    private Store store;

    public TradingMarketStores() {
    }

    public TradingMarketStores(TradingMarketStoresId id, TradingMarket tradingMarket, Store store) {
        this.id = id;
        this.tradingMarket = tradingMarket;
        this.store = store;
    }

    public TradingMarketStores(TradingMarket tradingMarket) {
        this.tradingMarket = tradingMarket;
    }

    public TradingMarketStoresId getId() {
        return id;
    }

    public void setId(TradingMarketStoresId id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradingMarketStores that = (TradingMarketStores) o;
        return Objects.equals(id, that.id) && Objects.equals(tradingMarket, that.tradingMarket) && Objects.equals(store, that.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tradingMarket, store);
    }
}
