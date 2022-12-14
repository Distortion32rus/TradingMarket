package ru.bspl.pet.tradingmarket.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bspl.pet.tradingmarket.models.Order;
import ru.bspl.pet.tradingmarket.models.OrderPosition;
import ru.bspl.pet.tradingmarket.models.OrderPositionId;
import ru.bspl.pet.tradingmarket.models.Store;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderPositionRepo extends JpaRepository<OrderPosition, OrderPositionId> {
    List<OrderPosition> findByOrder(Order order);

   // Optional<OrderPosition> findById(OrderPositionId orderPositionId);

    //List<OrderPosition> findByOrderAndStore(Order order, Store store);

}
