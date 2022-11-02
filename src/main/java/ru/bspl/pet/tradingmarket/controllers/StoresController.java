package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.models.Agreement;
import ru.bspl.pet.tradingmarket.models.Store;
import ru.bspl.pet.tradingmarket.services.BusinessUnitService;
import ru.bspl.pet.tradingmarket.services.OrganizationService;
import ru.bspl.pet.tradingmarket.services.PriceZoneService;
import ru.bspl.pet.tradingmarket.services.StoreService;

@Controller
@RequestMapping("/stores")
public class StoresController {


    private final StoreService storeService;

    private final BusinessUnitService businessUnitService;

    private final PriceZoneService priceZoneService;

    private final OrganizationService organizationService;

    @Autowired
    public StoresController(StoreService storeService, BusinessUnitService businessUnitService, PriceZoneService priceZoneService, OrganizationService organizationService) {
        this.storeService = storeService;
        this.businessUnitService = businessUnitService;
        this.priceZoneService = priceZoneService;
        this.organizationService = organizationService;
    }

    @GetMapping()
    public String show(Model model){
        model.addAttribute("stores", storeService.findAll());
        model.addAttribute("header", "stores  list");
        return "stores/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("store", new Store());
        model.addAttribute("priseZones", priceZoneService.findAll());
        model.addAttribute("businessUnits", businessUnitService.findAll());
        model.addAttribute("organizations", organizationService.findAll());
        model.addAttribute("header", "stores  add new");
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
        model.addAttribute("store", storeService.findOne(id));
        model.addAttribute("header", "Agreements-add new");
        return "stores/edit";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("store") Store store, @PathVariable("id") Long id){
        storeService.update(id, store);
        return "redirect:/stores";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        storeService.delete(id);
        return "redirect:/stores";
    }


}
