package ru.bspl.pet.tradingmarket.services;


import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.Counterparty;
import ru.bspl.pet.tradingmarket.models.Store;
import ru.bspl.pet.tradingmarket.repos.CounterpartyRepo;
import ru.bspl.pet.tradingmarket.repos.StoresRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StoreService {

    private final StoresRepo storesRepo;

    @Autowired
    public StoreService(StoresRepo storesRepo) {
        this.storesRepo = storesRepo;
    }

    public List<Store> findAll(){
        return storesRepo.findAll();
    }

    public Store findOne(Long id){
        Optional<Store> store = storesRepo.findById(id);
        return store.orElse(null);
    }

    @Transactional
    public void save(Store store){
        try {
            storesRepo.save(store);
        }catch (ConversionNotSupportedException e){
            e.printStackTrace();

        }
    }

    @Transactional
    public void update(Long id, Store store){
        store.setId(id);
        storesRepo.save(store);
    }

    @Transactional
    public void delete(Long id){
        storesRepo.deleteById(id);
    }

}
