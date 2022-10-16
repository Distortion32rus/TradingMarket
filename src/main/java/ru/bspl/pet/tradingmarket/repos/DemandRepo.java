package ru.bspl.pet.tradingmarket.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bspl.pet.tradingmarket.models.Demand;
import ru.bspl.pet.tradingmarket.models.DemandId;

public interface DemandRepo extends JpaRepository<Demand, DemandId> {
}
