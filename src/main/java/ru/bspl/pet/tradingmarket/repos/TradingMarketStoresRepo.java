package ru.bspl.pet.tradingmarket.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bspl.pet.tradingmarket.models.TradingMarket;
import ru.bspl.pet.tradingmarket.models.TradingMarketStores;
import ru.bspl.pet.tradingmarket.models.TradingMarketStoresId;

import java.util.List;

@Repository
public interface TradingMarketStoresRepo extends JpaRepository<TradingMarketStores, Long> {
    List<TradingMarketStores> findByTradingMarket(TradingMarket tradingMarket);

    TradingMarketStores findById(TradingMarketStoresId tradingMarketStoresId);

    void deleteById(TradingMarketStoresId tradingMarketStoresId);
}
