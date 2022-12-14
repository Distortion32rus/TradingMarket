package ru.bspl.pet.tradingmarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.*;
import ru.bspl.pet.tradingmarket.repos.TradingMarketRepo;
import ru.bspl.pet.tradingmarket.utils.OrdersIsCreatedException;

import java.util.*;

@Service
public class TradingMarketService {

    private final TradingMarketRepo tradingMarketRepo;
    private final OrderService orderService;
    private final TradingMarketStoresService tradingMarketStoresService;

    @Autowired
    public TradingMarketService(TradingMarketRepo tradingMarketRepo, OrderService orderService, TradingMarketStoresService tradingMarketStoresService) {
        this.tradingMarketRepo = tradingMarketRepo;
        this.orderService = orderService;
        this.tradingMarketStoresService = tradingMarketStoresService;
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
        tradingMarketRepo.save(tradingMarket);
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
    public Integer tradingMarketCalc(Long id) throws OrdersIsCreatedException {

        if(!orderService.getByTradingMarket(findOne(id)).isEmpty())
            throw new OrdersIsCreatedException("Orders is already created!");

        return tradingMarketRepo.tradingMarketCalc(id);
    }


    @Transactional
    public void createOrders(Long id) throws OrdersIsCreatedException {

        TradingMarket tradingMarket = findOne(id);

        if(!orderService.getByTradingMarket(tradingMarket).isEmpty())
            throw new OrdersIsCreatedException("Orders is already created!");

        List<Thread> threads = new ArrayList<>();

        for (TradingMarketStores tradingMarketStore : tradingMarketStoresService.findByTradingMarket(tradingMarket)) {

            Runnable createOrderByStore = () -> {
                tradingMarketRepo.createOrders(id, tradingMarketStore.getStore().getId());
            };

            Thread thread = new Thread(createOrderByStore);
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
