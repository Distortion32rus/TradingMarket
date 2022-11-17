package ru.bspl.pet.tradingmarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.Counterparty;
import ru.bspl.pet.tradingmarket.models.PriceList;
import ru.bspl.pet.tradingmarket.models.PriceListId;
import ru.bspl.pet.tradingmarket.models.PriceZone;
import ru.bspl.pet.tradingmarket.repos.PriceListRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PriceListService {
    private final PriceListRepo priceListRepo;
    @Autowired
    public PriceListService(PriceListRepo priceListRepo) {
        this.priceListRepo = priceListRepo;
    }
    public List<PriceList> findAll(){
        return priceListRepo.findAll();
    }

    public PriceList findOne(PriceListId priceListId){
        Optional<PriceList> priceList = priceListRepo.findById(priceListId);
        return priceList.orElse(null);
    }

    public List<PriceList> findByCounterpartyAndPriceZone(Counterparty counterparty, PriceZone priceZone){
        Optional<List<PriceList>> priceLists = Optional.ofNullable(priceListRepo.findByCounterpartyAndPriceZone(counterparty, priceZone));
        return priceLists.orElse(null);
    }

    @Transactional
    public void saveAll(List<PriceList> priceLists){
        priceListRepo.saveAll(priceLists);
    }

    @Transactional
    public void save(PriceList priceList){
            priceListRepo.save(priceList);
    }

    @Transactional
    public void update(PriceListId priceListId, PriceList priceList){
        priceList.setId(priceListId);
        priceListRepo.save(priceList);
    }

    @Transactional
    public void deleteAll(List<PriceList> priceLists){
        priceListRepo.deleteAll(priceLists);
    }

}
