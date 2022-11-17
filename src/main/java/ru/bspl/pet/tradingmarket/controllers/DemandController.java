package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bspl.pet.tradingmarket.dto.DemandDTO;
import ru.bspl.pet.tradingmarket.dto.DemandPositionDTO;
import ru.bspl.pet.tradingmarket.models.Demand;
import ru.bspl.pet.tradingmarket.models.DemandId;
import ru.bspl.pet.tradingmarket.models.Store;
import ru.bspl.pet.tradingmarket.services.AssortmentPlanService;
import ru.bspl.pet.tradingmarket.services.DemandService;
import ru.bspl.pet.tradingmarket.services.StoreService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/demand")
public class DemandController {

    private final StoreService storeService;

    private final DemandService demandService;

    private final AssortmentPlanService assortmentPlanService;

    @Autowired
    public DemandController(StoreService storeService, DemandService demandService, AssortmentPlanService assortmentPlanService) {
        this.storeService = storeService;
        this.demandService = demandService;
        this.assortmentPlanService = assortmentPlanService;
    }


    @PostMapping("/api")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid DemandDTO demandDTO , BindingResult bindingResult){

        List<Demand> demands = demandService.findByStore(storeService.findOne(demandDTO.getStoreId()));

        if(!demands.isEmpty())
            demandService.deleteAll(demands);

        demandService.saveAll(convertToDemand(demandDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private List<Demand> convertToDemand(DemandDTO demandDTO) {

        Store store = storeService.findOne(demandDTO.getStoreId());

        List<Demand> demands = new ArrayList<>();

        for (DemandPositionDTO demandPosition: demandDTO.getDemandPositions()) {
            Demand demand = new Demand();
            demand.setDemandQNT(demandPosition.getDemandQNT());
            demand.setSalesSpeed(demandPosition.getSalesSpeed());
            demand.setStockQNT(demandPosition.getStockQNT());
            demand.setId(new DemandId(store,
                    assortmentPlanService.findOne(demandPosition.getAssortmentPlanId())));

            demands.add(demand);
        }

        return demands;
    }


}
