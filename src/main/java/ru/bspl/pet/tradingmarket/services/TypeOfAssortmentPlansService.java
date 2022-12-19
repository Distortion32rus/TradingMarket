package ru.bspl.pet.tradingmarket.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.TypeOfAssortmentPlans;
import ru.bspl.pet.tradingmarket.repos.TypeOfAssortmentPlansRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TypeOfAssortmentPlansService {

    private final TypeOfAssortmentPlansRepo typeOfAssortmentPlansRepo;

    @Autowired
    public TypeOfAssortmentPlansService(TypeOfAssortmentPlansRepo typeOfAssortmentPlansRepo) {
        this.typeOfAssortmentPlansRepo = typeOfAssortmentPlansRepo;
    }

    public List<TypeOfAssortmentPlans> findAll(){
        return typeOfAssortmentPlansRepo.findAll();
    }

    public Page<TypeOfAssortmentPlans> findAll(int page, int size){
        return typeOfAssortmentPlansRepo.findAll(PageRequest.of(page, size));
    }

    public TypeOfAssortmentPlans findOne(Long id){
        Optional<TypeOfAssortmentPlans> typeOfAssortmentPlans = typeOfAssortmentPlansRepo.findById(id);
        return typeOfAssortmentPlans.orElse(null);
    }

    @Transactional
    public void save(TypeOfAssortmentPlans typeOfAssortmentPlans){
        typeOfAssortmentPlansRepo.save(typeOfAssortmentPlans);
    }

    @Transactional
    public void update(Long id, TypeOfAssortmentPlans typeOfAssortmentPlans){
        typeOfAssortmentPlans.setId(id);
        typeOfAssortmentPlansRepo.save(typeOfAssortmentPlans);
    }

    @Transactional
    public void delete(Long id){
        typeOfAssortmentPlansRepo.deleteById(id);
    }
}
