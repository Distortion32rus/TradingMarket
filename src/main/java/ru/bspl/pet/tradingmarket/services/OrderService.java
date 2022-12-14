package ru.bspl.pet.tradingmarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.Order;
import ru.bspl.pet.tradingmarket.models.TradingMarket;
import ru.bspl.pet.tradingmarket.repos.OrderRepo;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> findAll(){
        return orderRepo.findAll();
    }

    public List<Order> getByTradingMarket(TradingMarket tradingMarket){
        return orderRepo.getByTradingMarket(tradingMarket);
    }

    public Order findOne(Long id){
        Optional<Order> order = orderRepo.findById(id);
        return order.orElse(null);
    }



    @Transactional
    public void save(Order order){
        orderRepo.save(order);
    }

    @Transactional
    public void update(Long id, Order order){
        order.setId(id);
        orderRepo.save(order);
    }

    @Transactional
    public void delete(Long id){
        orderRepo.deleteById(id);
    }


}
