package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class TraidingMarket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "traidingMarket", cascade = CascadeType.ALL)
    private List<TraidingMarketDistribution> traidingMarketDistributionList;

    private String name;
    private double demandReplenishmentSumm, preOrderSumm;

    public long getId() {
        return id;
    }

    public TraidingMarket(String name) {
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

    public TraidingMarket() {
    }
}
