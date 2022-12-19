package ru.bspl.pet.tradingmarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.Agreement;
import ru.bspl.pet.tradingmarket.repos.AgreementRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AgreementService {


    private final AgreementRepo agreementRepo;

    @Autowired
    public AgreementService(AgreementRepo agreementRepo) {
        this.agreementRepo = agreementRepo;
    }

    public List<Agreement> findAll(){
        return agreementRepo.findAll();
    }

    public Page<Agreement> findAll(int page, int size){
        return agreementRepo.findAll(PageRequest.of(page, size));
    }

   public Agreement findOne(Long id){
       Optional<Agreement> agreement = agreementRepo.findById(id);
        return agreement.orElse(null);
   }

    @Transactional
    public void save(Agreement agreement){
        agreementRepo.save(agreement);
    }

    @Transactional
    public void update(Long id, Agreement agreement){
        agreement.setId(id);
        agreementRepo.save(agreement);
    }

    @Transactional
    public void delete(Long id){
        agreementRepo.deleteById(id);
    }
}
