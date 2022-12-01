package ru.bspl.pet.tradingmarket.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bspl.pet.tradingmarket.models.*;

import java.util.List;

@Repository
public interface TradingMarketDistributionRepo extends JpaRepository<TradingMarketDistribution, TradingMarketDistributionId> {
    List<TradingMarketDistribution> findByTradingMarket(TradingMarket tradingMarket);
}
