package ru.bspl.pet.tradingmarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.Demand;
import ru.bspl.pet.tradingmarket.models.DemandId;
import ru.bspl.pet.tradingmarket.models.Store;
import ru.bspl.pet.tradingmarket.repos.DemandRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DemandService {
    private final DemandRepo demandRepo;

    @Autowired
    public DemandService(DemandRepo demandRepo) {
        this.demandRepo = demandRepo;
    }

    public List<Demand> findAll(){
        return demandRepo.findAll();
    }

    public Demand findOne(DemandId demandId){
        Optional<Demand> demand = demandRepo.findById(demandId);
        return demand.orElse(null);
    }

    public List<Demand> findByStore(Store store){
        Optional<List<Demand>> demands = Optional.ofNullable(demandRepo.findByStore(store));
        return demands.orElse(null);
    }

    @Transactional
    public void save(Demand demand){
        demandRepo.save(demand);
    }

    @Transactional
    public void saveAll(List<Demand> demands){
        demandRepo.saveAll(demands);
    }

    @Transactional
    public void update(DemandId demandId, Demand demand){
        demand.setId(demandId);
        demandRepo.save(demand);
    }

    @Transactional
    public void deleteAll(List<Demand> demands){
        demandRepo.deleteAll(demands);
    }
}
