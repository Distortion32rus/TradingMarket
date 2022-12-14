package ru.bspl.pet.tradingmarket.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bspl.pet.tradingmarket.models.Order;
import ru.bspl.pet.tradingmarket.models.TradingMarket;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> getByTradingMarket(TradingMarket tradingMarket);
}
