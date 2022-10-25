package ru.bspl.pet.tradingmarket.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.bspl.pet.tradingmarket.models.Person;
import ru.bspl.pet.tradingmarket.services.PersonService;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person)target;

        try {
            personService.loadUserByUsername(person.getUsername());
        }catch(UsernameNotFoundException ignored){
            return;
        }

        errors.rejectValue("username", "Username is already exist.");

    }
}
