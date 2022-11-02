package ru.bspl.pet.tradingmarket.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.Counterparty;
import ru.bspl.pet.tradingmarket.repos.CounterpartyRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CounterpartyService {

    private final CounterpartyRepo counterpartyRepo;

    @Autowired
    public CounterpartyService(CounterpartyRepo counterpartyRepo) {
        this.counterpartyRepo = counterpartyRepo;
    }

    public List<Counterparty> findAll(){
        return counterpartyRepo.findAll();
    }

    public Counterparty findOne(Long id){
        Optional<Counterparty> counterparty = counterpartyRepo.findById(id);
        return counterparty.orElse(null);
    }

    @Transactional
    public void save(Counterparty counterparty){
        counterpartyRepo.save(counterparty);
    }

    @Transactional
    public void update(Long id, Counterparty counterparty){
        counterparty.setId(id);
        counterpartyRepo.save(counterparty);
    }

    @Transactional
    public void delete(Long id){
        counterpartyRepo.deleteById(id);
    }

}
