package ru.bspl.pet.tradingmarket.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.ThresholdValues;
import ru.bspl.pet.tradingmarket.repos.ThresholdValuesRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ThresholdValuesService {

    private final ThresholdValuesRepo thresholdValuesRepo;

    @Autowired
    public ThresholdValuesService(ThresholdValuesRepo thresholdValuesRepo) {
        this.thresholdValuesRepo = thresholdValuesRepo;
    }

    public List<ThresholdValues> findAll(){
        return thresholdValuesRepo.findAll();
    }

    public Page<ThresholdValues> findAll(int page, int size){
        return thresholdValuesRepo.findAll(PageRequest.of(page, size));
    }

    public ThresholdValues findOne(Long id){
        Optional<ThresholdValues> thresholdValues = thresholdValuesRepo.findById(id);
        return thresholdValues.orElse(null);
    }

    @Transactional
    public void save(ThresholdValues thresholdValues){
        thresholdValuesRepo.save(thresholdValues);
    }

    @Transactional
    public void update(Long id, ThresholdValues thresholdValues){
        thresholdValues.setId(id);
        thresholdValuesRepo.save(thresholdValues);
    }

    @Transactional
    public void delete(Long id){
        thresholdValuesRepo.deleteById(id);
    }
}
