package ru.bspl.pet.tradingmarket.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.CounterpartsNomenclature;
import ru.bspl.pet.tradingmarket.repos.CounterpartsNomenclatureRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CounterpartsNomenclatureService {

    private final CounterpartsNomenclatureRepo counterpartsNomenclatureRepo;

    @Autowired
    public CounterpartsNomenclatureService(CounterpartsNomenclatureRepo counterpartsNomenclatureRepo) {
        this.counterpartsNomenclatureRepo = counterpartsNomenclatureRepo;
    }

    public List<CounterpartsNomenclature> findAll(){
        return counterpartsNomenclatureRepo.findAll();
    }

    public CounterpartsNomenclature findOne(Long id){
        Optional<CounterpartsNomenclature> counterpartsNomenclature = counterpartsNomenclatureRepo.findById(id);
        return counterpartsNomenclature.orElse(null);
    }

    @Transactional
    public void save(CounterpartsNomenclature counterpartsNomenclature){
            counterpartsNomenclatureRepo.save(counterpartsNomenclature);
    }

    @Transactional
    public void update(Long id, CounterpartsNomenclature counterpartsNomenclature){
        counterpartsNomenclature.setId(id);
        counterpartsNomenclatureRepo.save(counterpartsNomenclature);
    }

    @Transactional
    public void delete(Long id){
        counterpartsNomenclatureRepo.deleteById(id);
    }
}
