package ru.bspl.pet.tradingmarket.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.Agreement;
import ru.bspl.pet.tradingmarket.models.Nomenclature;
import ru.bspl.pet.tradingmarket.repos.AgreementRepo;
import ru.bspl.pet.tradingmarket.repos.NomenclatureRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class NomenclatureService {

    private final NomenclatureRepo nomenclatureRepo;

    @Autowired
    public NomenclatureService(NomenclatureRepo nomenclatureRepo) {
        this.nomenclatureRepo = nomenclatureRepo;
    }

    public List<Nomenclature> findAll(){
        return nomenclatureRepo.findAll();
    }

    public Nomenclature findOne(Long id){
        Optional<Nomenclature> nomenclature = nomenclatureRepo.findById(id);
        return nomenclature.orElse(null);
    }

    @Transactional
    public void save(Nomenclature nomenclature){
        nomenclatureRepo.save(nomenclature);
    }

    @Transactional
    public void update(Long id, Nomenclature nomenclature){
        nomenclature.setId(id);
        nomenclatureRepo.save(nomenclature);
    }
}
