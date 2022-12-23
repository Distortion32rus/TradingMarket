package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.models.Store;
import ru.bspl.pet.tradingmarket.services.*;

import java.util.stream.IntStream;

@Controller
@RequestMapping("/stores")
public class StoresController {


    private final StoreService storeService;

    private final BusinessUnitService businessUnitService;

    private final PriceZoneService priceZoneService;

    private final OrganizationService organizationService;
    private final ThresholdValuesService thresholdValuesService;

    @Autowired
    public StoresController(StoreService storeService, BusinessUnitService businessUnitService, PriceZoneService priceZoneService, OrganizationService organizationService, ThresholdValuesService thresholdValuesService) {
        this.storeService = storeService;
        this.businessUnitService = businessUnitService;
        this.priceZoneService = priceZoneService;
        this.organizationService = organizationService;
        this.thresholdValuesService = thresholdValuesService;
    }

    @GetMapping()
    public String show(Model model,
                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page){
        Page<Store> storePage = storeService.findAll(page, 15);

        model.addAttribute("storePage", storePage);
        model.addAttribute("numbers", IntStream.range(0, storePage.getTotalPages()).toArray());
        model.addAttribute("header", "Список складов");
        return "stores/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("store", new Store());
        model.addAttribute("priseZones", priceZoneService.findAll());
        model.addAttribute("businessUnits", businessUnitService.findAll());
        model.addAttribute("organizations", organizationService.findAll());
        model.addAttribute("thresholdValues", thresholdValuesService.findAll());
        model.addAttribute("header", "Добавление склада");
        return "stores/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("store") Store store){
        storeService.save(store);
        return "redirect:/stores";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("priseZones", priceZoneService.findAll());
        model.addAttribute("businessUnits", businessUnitService.findAll());
        model.addAttribute("organizations", organizationService.findAll());
        model.addAttribute("thresholdValues", thresholdValuesService.findAll());
        model.addAttribute("store", storeService.findOne(id));
        model.addAttribute("header", "Изменение склада");
        return "stores/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("store") Store store, @PathVariable("id") Long id){
        storeService.update(id, store);
        return "redirect:/stores";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        storeService.delete(id);
        return "redirect:/stores";
    }


}
