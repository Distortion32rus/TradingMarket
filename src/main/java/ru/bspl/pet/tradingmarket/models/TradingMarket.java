package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.List;



@Entity
/*@NamedStoredProcedureQuery(name = "TradingMarket.tradingMarketCalc",
        procedureName = "trading_market_calc", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "tmid", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "count_out", type = Integer.class)})*/
@SequenceGenerator(name="TRADING_MARKET", sequenceName="TRADING_MARKET_GENERATOR")
public class TradingMarket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRADING_MARKET")
    private long id;

    @OneToMany(mappedBy = "tradingMarket", cascade = CascadeType.ALL)
    private List<TradingMarketDistribution> tradingMarketDistributionList;

    private String name;

    public TradingMarket() {
    }

    public TradingMarket(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<TradingMarketDistribution> getTradingMarketDistributionList() {
        return tradingMarketDistributionList;
    }

    public void setTradingMarketDistributionList(List<TradingMarketDistribution> tradingMarketDistributionList) {
        this.tradingMarketDistributionList = tradingMarketDistributionList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
