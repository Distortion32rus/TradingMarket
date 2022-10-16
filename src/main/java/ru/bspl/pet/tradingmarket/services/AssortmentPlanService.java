package ru.bspl.pet.tradingmarket.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.AssortmentPlan;
import ru.bspl.pet.tradingmarket.repos.AssortmentPlanRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AssortmentPlanService {

    private final AssortmentPlanRepo assortmentPlanRepo;

    @Autowired
    public AssortmentPlanService(AssortmentPlanRepo assortmentPlanRepo) {
        this.assortmentPlanRepo = assortmentPlanRepo;
    }

    public List<AssortmentPlan> findAll(){
        return assortmentPlanRepo.findAll();
    }

    public AssortmentPlan findOne(Long id){
        Optional<AssortmentPlan> agreement = assortmentPlanRepo.findById(id);
        return agreement.orElse(null);
    }

    @Transactional
    public void save(AssortmentPlan assortmentPlan){
        assortmentPlanRepo.save(assortmentPlan);
    }

    @Transactional
    public void update(Long id, AssortmentPlan assortmentPlan){
        assortmentPlan.setId(id);
        assortmentPlanRepo.save(assortmentPlan);
    }
}
