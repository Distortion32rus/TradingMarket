package ru.bspl.pet.tradingmarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.*;
import ru.bspl.pet.tradingmarket.repos.TradingMarketStoresRepo;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class TradingMarketStoresService {

    private final TradingMarketStoresRepo tradingMarketStoresRepo;

    @Autowired
    public TradingMarketStoresService(TradingMarketStoresRepo tradingMarketStoresRepo) {
        this.tradingMarketStoresRepo = tradingMarketStoresRepo;
    }

    public List<TradingMarketStores> findByTradingMarket(TradingMarket tradingMarket){
        return tradingMarketStoresRepo.findByTradingMarket(tradingMarket);
    }

    public TradingMarketStores findById(TradingMarketStoresId tradingMarketStoresId){
        return tradingMarketStoresRepo.findById(tradingMarketStoresId);
    }

    @Transactional
    public void saveAll(List<TradingMarketStores> tradingMarketStores){
        tradingMarketStoresRepo.saveAll(tradingMarketStores);
    }

    @Transactional
    public void save(TradingMarketStores tradingMarketStores){
        tradingMarketStoresRepo.save(tradingMarketStores);
    }

    @Transactional
    public void update(TradingMarketStoresId tradingMarketStoresId, TradingMarketStores tradingMarketStores){
        tradingMarketStores.setId(tradingMarketStoresId);
        tradingMarketStoresRepo.save(tradingMarketStores);
    }

    @Transactional
    public void deleteAll(List<TradingMarketStores> tradingMarketStores){
        tradingMarketStoresRepo.deleteAll(tradingMarketStores);
    }

    @Transactional
    public void deleteById(TradingMarketStoresId tradingMarketStoresId){
        tradingMarketStoresRepo.deleteById(tradingMarketStoresId);
    }
}
