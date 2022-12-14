package ru.bspl.pet.tradingmarket.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bspl.pet.tradingmarket.models.TradingMarket;

@Repository
public interface TradingMarketRepo extends JpaRepository<TradingMarket, Long> {
    /*@Procedure(name = "TradingMarket.tradingMarketCalc")
    Integer tradingMarketCalc(@Param("tmid") Long model);*/

    @Query(value = "SELECT func_trading_market_calc(:tmid);", nativeQuery = true)
    Integer tradingMarketCalc(@Param("tmid") Long tmid);

    @Query(value = "SELECT func_create_order(:tmid, :storeid);", nativeQuery = true)
    Integer createOrders(@Param("tmid") Long tmid, @Param("storeid") Long storeid);
}
