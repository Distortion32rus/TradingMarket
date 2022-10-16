package ru.bspl.pet.tradingmarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.BusinessUnit;
import ru.bspl.pet.tradingmarket.repos.BusinessUnitRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BusinessUnitService {

    private final BusinessUnitRepo businessUnitRepo;

    @Autowired
    public BusinessUnitService(BusinessUnitRepo businessUnitRepo) {
        this.businessUnitRepo = businessUnitRepo;
    }

    public List<BusinessUnit> findAll(){
        return businessUnitRepo.findAll();
    }

    public BusinessUnit findOne(Long id){
        Optional<BusinessUnit> businessUnit = businessUnitRepo.findById(id);
        return businessUnit.orElse(null);
    }

    @Transactional
    public void save(BusinessUnit businessUnit){
        businessUnitRepo.save(businessUnit);
    }

    @Transactional
    public void update(Long id, BusinessUnit businessUnit){
        businessUnit.setId(id);
        businessUnitRepo.save(businessUnit);
    }
}
