package ru.bspl.pet.tradingmarket.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bspl.pet.tradingmarket.models.Demand;
import ru.bspl.pet.tradingmarket.models.Store;
import ru.bspl.pet.tradingmarket.models.ThresholdValues;
import ru.bspl.pet.tradingmarket.models.ThresholdValuesTable;

import java.util.List;

@Repository
public interface ThresholdValuesTableRepo extends JpaRepository<ThresholdValuesTable, Long> {

    List<ThresholdValuesTable> findByThresholdValues(ThresholdValues thresholdValues);
}
