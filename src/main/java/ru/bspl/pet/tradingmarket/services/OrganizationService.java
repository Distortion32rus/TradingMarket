package ru.bspl.pet.tradingmarket.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.bspl.pet.tradingmarket.models.Organization;
import ru.bspl.pet.tradingmarket.repos.OrganizationRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrganizationService {

    private final OrganizationRepo organizationRepo;

    @Autowired
    public OrganizationService(OrganizationRepo organizationRepo) {
        this.organizationRepo = organizationRepo;
    }

    public List<Organization> findAll(){
        return organizationRepo.findAll();
    }

    public Organization findOne(Long id){
        Optional<Organization> organization = organizationRepo.findById(id);
        return organization.orElse(null);
    }

    @Transactional
    public void save(Organization organization){
        organizationRepo.save(organization);
    }

    @Transactional
    public void update(Long id, Organization organization){
        organization.setId(id);
        organizationRepo.save(organization);
    }
}
