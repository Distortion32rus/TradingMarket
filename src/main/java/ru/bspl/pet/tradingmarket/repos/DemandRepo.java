package ru.bspl.pet.tradingmarket.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bspl.pet.tradingmarket.models.*;

import java.util.List;

public interface DemandRepo extends JpaRepository<Demand, DemandId> {
    List<Demand> findByStore(Store store);

}
