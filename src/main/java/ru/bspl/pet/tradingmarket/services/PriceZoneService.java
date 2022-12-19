package ru.bspl.pet.tradingmarket.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.CounterpartsNomenclature;
import ru.bspl.pet.tradingmarket.models.PriceZone;
import ru.bspl.pet.tradingmarket.repos.CounterpartsNomenclatureRepo;
import ru.bspl.pet.tradingmarket.repos.PriceZoneRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PriceZoneService {

    private final PriceZoneRepo priceZoneRepo;

    @Autowired
    public PriceZoneService(PriceZoneRepo priceZoneRepo) {
        this.priceZoneRepo = priceZoneRepo;
    }

    public List<PriceZone> findAll(){
        return priceZoneRepo.findAll();
    }

    public Page<PriceZone> findAll(int page, int size){
        return priceZoneRepo.findAll(PageRequest.of(page, size));
    }

    public PriceZone findOne(Long id){
        Optional<PriceZone> counterpartsNomenclature = priceZoneRepo.findById(id);
        return counterpartsNomenclature.orElse(null);
    }

    @Transactional
    public void save(PriceZone priceZone){
        priceZoneRepo.save(priceZone);
    }

    @Transactional
    public void update(Long id, PriceZone priceZone){
        priceZone.setId(id);
        priceZoneRepo.save(priceZone);
    }
    @Transactional
    public void delete(Long id){
        priceZoneRepo.deleteById(id);
    }
}
