package ru.bspl.pet.tradingmarket.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.dto.PriceListDTO;
import ru.bspl.pet.tradingmarket.dto.PricePositionDTO;
import ru.bspl.pet.tradingmarket.models.Counterparty;
import ru.bspl.pet.tradingmarket.models.PriceList;
import ru.bspl.pet.tradingmarket.models.PriceListId;
import ru.bspl.pet.tradingmarket.models.PriceZone;
import ru.bspl.pet.tradingmarket.services.CounterpartsNomenclatureService;
import ru.bspl.pet.tradingmarket.services.CounterpartyService;
import ru.bspl.pet.tradingmarket.services.PriceListService;
import ru.bspl.pet.tradingmarket.services.PriceZoneService;
import ru.bspl.pet.tradingmarket.utils.PriseListNotCreatedException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/pricelists")
public class PriceListController {

    private final PriceListService priceListService;
    private final CounterpartyService counterpartyService;
    private final CounterpartsNomenclatureService counterpartsNomenclatureService;
    private final PriceZoneService priceZoneService;

    @Autowired
    public PriceListController(PriceListService priceListService, CounterpartyService counterpartyService, CounterpartsNomenclatureService counterpartsNomenclatureService, PriceZoneService priceZoneService) {
        this.priceListService = priceListService;
        this.counterpartyService = counterpartyService;
        this.counterpartsNomenclatureService = counterpartsNomenclatureService;
        this.priceZoneService = priceZoneService;
    }

    @PostMapping("/api")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PriceListDTO priceListDTO , BindingResult bindingResult){

        List<PriceList> priceLists = priceListService.findByCounterpartyAndPriceZone(counterpartyService.findOne(priceListDTO.getCounterpartyId()), priceZoneService.findOne(priceListDTO.getPriceZoneId()));

        if(!priceLists.isEmpty())
            priceListService.deleteAll(priceLists);

        priceListService.saveAll(convertToPriceList(priceListDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private List<PriceList> convertToPriceList(PriceListDTO priceListDTO) {

        PriceZone priceZone = priceZoneService.findOne(priceListDTO.getPriceZoneId());
        Counterparty counterparty = counterpartyService.findOne(priceListDTO.getCounterpartyId());

        List<PriceList> priceLists = new ArrayList<>();

        for (PricePositionDTO pricePosition: priceListDTO.getPricePositions()) {
            PriceList priceList = new PriceList();
            priceList.setCounterpartysPrice(pricePosition.getCounterpartysPrice());
            priceList.setMultiplicityOf(pricePosition.getMultiplicityOf());
            priceList.setShelfLife(pricePosition.getShelfLife());
            priceList.setCounterpartysStock(pricePosition.getCounterpartysStock());
            priceList.setId(new PriceListId(priceZone, counterparty,
                    counterpartsNomenclatureService.findOne(pricePosition.getCounterpartsNomenclatureId())));

            priceLists.add(priceList);
        }

        return priceLists;
    }

}
