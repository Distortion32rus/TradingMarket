package ru.bspl.pet.tradingmarket.services;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.TradingMarket;
import ru.bspl.pet.tradingmarket.repos.TradingMarketRepo;

import java.util.List;
import java.util.Optional;

@Service
public class TradingMarketService {

    private final TradingMarketRepo tradingMarketRepo;

    @Autowired
    public TradingMarketService(TradingMarketRepo tradingMarketRepo) {
        this.tradingMarketRepo = tradingMarketRepo;
    }

    public List<TradingMarket> findAll(){
        return tradingMarketRepo.findAll();
    }

    public TradingMarket findOne(Long id){
        Optional<TradingMarket> tradingMarket = tradingMarketRepo.findById(id);
        return tradingMarket.orElse(null);
    }

    @Transactional
    public void save(TradingMarket tradingMarket){
        try {
            tradingMarketRepo.save(tradingMarket);
        }catch (ConversionNotSupportedException e){
            e.printStackTrace();

        }
    }
    @Transactional
    public void update(Long id, TradingMarket tradingMarket){
        tradingMarket.setId(id);
        tradingMarketRepo.save(tradingMarket);
    }

    @Transactional
    public void delete(Long id){
        tradingMarketRepo.deleteById(id);
    }

    @Transactional
    public Integer tradingMarketCalc(Long id){
        return  tradingMarketRepo.tradingMarketCalc(id);
    }
}
