package ru.bspl.pet.tradingmarket.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bspl.pet.tradingmarket.models.Counterparty;
import ru.bspl.pet.tradingmarket.models.PriceList;
import ru.bspl.pet.tradingmarket.models.PriceListId;
import ru.bspl.pet.tradingmarket.models.PriceZone;

import java.util.List;

public interface PriceListRepo extends JpaRepository<PriceList, PriceListId> {
    List<PriceList> findByCounterpartyAndPriceZone(Counterparty counterparty, PriceZone priceZone);
}


