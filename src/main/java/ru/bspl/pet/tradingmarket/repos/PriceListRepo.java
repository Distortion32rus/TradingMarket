package ru.bspl.pet.tradingmarket.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bspl.pet.tradingmarket.models.PriceList;
import ru.bspl.pet.tradingmarket.models.PriceListId;

public interface PriceListRepo extends JpaRepository<PriceList, PriceListId> {
}
