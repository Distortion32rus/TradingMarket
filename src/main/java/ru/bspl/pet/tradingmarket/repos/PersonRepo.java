package ru.bspl.pet.tradingmarket.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bspl.pet.tradingmarket.models.Person;

import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {

    Optional<Person> findByUsername(String username);

}
