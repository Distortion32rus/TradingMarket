package ru.bspl.pet.tradingmarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bspl.pet.tradingmarket.models.Person;
import ru.bspl.pet.tradingmarket.repos.PersonRepo;

@Service
public class RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final PersonRepo personRepo;

    @Autowired
    public RegistrationService(PasswordEncoder passwordEncoder, PersonRepo personRepo) {
        this.passwordEncoder = passwordEncoder;
        this.personRepo = personRepo;
    }

    @Transactional
    public void save(Person person){
        person.setRole("ROLE_USER");
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepo.save(person);
    }
}
