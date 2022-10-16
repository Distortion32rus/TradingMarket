package ru.bspl.pet.tradingmarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.Agreement;
import ru.bspl.pet.tradingmarket.models.ThresholdValuesTable;
import ru.bspl.pet.tradingmarket.repos.AgreementRepo;
import ru.bspl.pet.tradingmarket.repos.ThresholdValuesTableRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ThresholdValuesTableService {

    private final ThresholdValuesTableRepo thresholdValuesTableRepo;

    @Autowired
    public ThresholdValuesTableService(ThresholdValuesTableRepo thresholdValuesTableRepo) {
        this.thresholdValuesTableRepo = thresholdValuesTableRepo;
    }

    public List<ThresholdValuesTable> findAll(){
        return thresholdValuesTableRepo.findAll();
    }

    public ThresholdValuesTable findOne(Long id){
        Optional<ThresholdValuesTable> thresholdValuesTable = thresholdValuesTableRepo.findById(id);
        return thresholdValuesTable.orElse(null);
    }

    @Transactional
    public void save(ThresholdValuesTable thresholdValuesTable){
        thresholdValuesTableRepo.save(thresholdValuesTable);
    }

    @Transactional
    public void update(Long id, ThresholdValuesTable thresholdValuesTable){
        thresholdValuesTable.setId(id);
        thresholdValuesTableRepo.save(thresholdValuesTable);
    }
}
