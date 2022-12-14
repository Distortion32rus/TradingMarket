package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "orders")
@SequenceGenerator(name = "ORDER", sequenceName = "ORDER_GENERATOR")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trading_market_id")
    private TradingMarket tradingMarket;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;

    private Date dateOfCreation;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public TradingMarket getTradingMarket() {
        return tradingMarket;
    }

    public void setTradingMarket(TradingMarket tradingMarket) {
        this.tradingMarket = tradingMarket;
    }

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(store, order.store) && Objects.equals(tradingMarket, order.tradingMarket) && Objects.equals(counterparty, order.counterparty) && Objects.equals(dateOfCreation, order.dateOfCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, store, tradingMarket, counterparty, dateOfCreation);
    }
}
