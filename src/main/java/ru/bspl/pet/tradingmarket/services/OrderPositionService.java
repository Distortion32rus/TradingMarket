package ru.bspl.pet.tradingmarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.*;
import ru.bspl.pet.tradingmarket.repos.OrderPositionRepo;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderPositionService {

    private final OrderPositionRepo orderPositionRepo;

    @Autowired
    public OrderPositionService(OrderPositionRepo orderPositionRepo) {
        this.orderPositionRepo = orderPositionRepo;
    }

    /*public List<OrderPosition> findByOrder(Order order){
        return orderPositionRepo.findByOrder(order);
    }*/

    public Page<OrderPosition> findByOrder(Order order, int page, int size){
        return orderPositionRepo.findByOrder(order, PageRequest.of(page, size, Sort.by("assortmentPlan").ascending()));
    }

   /* public List<OrderPosition> findByOrderAndStore(Order order, Store store){
        return orderPositionRepo.findByOrderAndStore(order, store);
    }*/

    @Transactional
    public void deleteAll(List<OrderPosition> orderPositions){
        orderPositionRepo.deleteAll(orderPositions);
    }

    @Transactional
    public void save(OrderPosition orderPosition){
        orderPositionRepo.save(orderPosition);
    }

    @Transactional
    public void update(OrderPosition orderPosition){
        orderPositionRepo.save(orderPosition);
    }

}
