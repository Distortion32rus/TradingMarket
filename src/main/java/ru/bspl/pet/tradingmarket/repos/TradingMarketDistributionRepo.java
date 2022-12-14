package ru.bspl.pet.tradingmarket.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bspl.pet.tradingmarket.models.Store;
import ru.bspl.pet.tradingmarket.models.TradingMarket;
import ru.bspl.pet.tradingmarket.models.TradingMarketDistribution;
import ru.bspl.pet.tradingmarket.models.TradingMarketDistributionId;

import java.util.List;

@Repository
public interface TradingMarketDistributionRepo extends JpaRepository<TradingMarketDistribution, TradingMarketDistributionId> {
    List<TradingMarketDistribution> findByTradingMarket(TradingMarket tradingMarket);

    List<TradingMarketDistribution> findByTradingMarketAndStoreAndInPriceControlAndNomenComparitionControlAndShelfLifeControlAndMultiplicityControlAndMinSupplBalanceControlAndMaxStockControlAndBestPriceControl(TradingMarket tradingMarket,
                                                                                                                                                                                                                  Store store, Boolean inPriceControl,
                                                                                                                                                                                                                  Boolean nomenComparitionControl, Boolean shelfLifeControl,
                                                                                                                                                                                                                  Boolean multiplicityControl, Boolean minSupplBalanceControl,
                                                                                                                                                                                                                  Boolean maxStockControl, Boolean bestPriceControl);

}
