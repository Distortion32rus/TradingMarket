package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.List;

@Entity
@SequenceGenerator(name="TRADING_MARKET", sequenceName="TRADING_MARKET_GENERATOR")
public class TradingMarket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRADING_MARKET")
    private long id;

    @OneToMany(mappedBy = "tradingMarket", cascade = CascadeType.ALL)
    private List<TradingMarketDistribution> tradingMarketDistributionList;

    private String name;
    private double demandReplenishmentSumm, preOrderSumm;

    public long getId() {
        return id;
    }

    public TradingMarket(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDemandReplenishmentSumm() {
        return demandReplenishmentSumm;
    }

    public void setDemandReplenishmentSumm(double demandReplenishmentSumm) {
        this.demandReplenishmentSumm = demandReplenishmentSumm;
    }

    public double getPreOrderSumm() {
        return preOrderSumm;
    }

    public void setPreOrderSumm(double preOrderSumm) {
        this.preOrderSumm = preOrderSumm;
    }

    public TradingMarket() {
    }
}
