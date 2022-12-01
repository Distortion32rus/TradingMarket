package ru.bspl.pet.tradingmarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.TradingMarket;
import ru.bspl.pet.tradingmarket.models.TradingMarketDistribution;
import ru.bspl.pet.tradingmarket.repos.TradingMarketDistributionRepo;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TradingMarketDistributionService {
    private final TradingMarketDistributionRepo tradingMarketDistributionRepo;

    @Autowired
    public TradingMarketDistributionService(TradingMarketDistributionRepo tradingMarketDistributionRepo) {
        this.tradingMarketDistributionRepo = tradingMarketDistributionRepo;
    }

    public List<TradingMarketDistribution> findByTradingMarket(TradingMarket tradingMarket){
        return tradingMarketDistributionRepo.findByTradingMarket(tradingMarket);
    }

    public void deleteAll(List<TradingMarketDistribution> tradingMarketDistributions){
        tradingMarketDistributionRepo.deleteAll(tradingMarketDistributions);
    }
}
