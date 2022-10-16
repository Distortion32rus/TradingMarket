package ru.bspl.pet.tradingmarket.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.dto.PriceListDTO;
import ru.bspl.pet.tradingmarket.models.PriceList;
import ru.bspl.pet.tradingmarket.models.PriceListId;
import ru.bspl.pet.tradingmarket.services.CounterpartsNomenclatureService;
import ru.bspl.pet.tradingmarket.services.CounterpartyService;
import ru.bspl.pet.tradingmarket.services.PriceListService;
import ru.bspl.pet.tradingmarket.services.PriceZoneService;
import ru.bspl.pet.tradingmarket.utils.PriseListNotCreatedException;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;


@Controller
@RequestMapping("/pricelist")
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


    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PriceListDTO priceListDTO , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errorList = bindingResult.getFieldErrors();

            for(FieldError error : errorList){
                errorMessage.append(error.getField()).append("-").append(error.getDefaultMessage()).append("; ");
            }

            throw new PriseListNotCreatedException(errorMessage.toString());
        }

        PriceList pl = convertToPriceList(priceListDTO);
        priceListService.save(pl);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private PriceList convertToPriceList(PriceListDTO priceListDTO) {
        PriceList priceList = new PriceList();

        priceList.setPriceZone(priceZoneService.findOne(priceListDTO.getPriceZoneId()));
        priceList.setCounterparty(counterpartyService.findOne(priceListDTO.getCounterpartyId()));
        priceList.setCounterpartsNomenclature(counterpartsNomenclatureService.findOne(priceListDTO.getCounterpartsNomenclatureId()));
        priceList.setCounterpartysPrice(priceListDTO.getCounterpartysPrice());
        priceList.setMultiplicityOf(priceListDTO.getMultiplicityOf());
        priceList.setShelfLife(new Timestamp(priceListDTO.getShelfLife().getTime()));
        priceList.setCounterpartysStock(priceListDTO.getCounterpartysStock());
        priceList.setId(new PriceListId(priceList.getPriceZone(), priceList.getCounterparty(),
                priceList.getCounterpartsNomenclature()));

        return priceList;
    }

    @GetMapping()
    public List<PriceList> getPrices(){
        return priceListService.findAll();
    }

   /* @ExceptionHandler
    private ResponseEntity<>
*/

}
