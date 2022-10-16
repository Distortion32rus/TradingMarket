package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import ru.bspl.pet.tradingmarket.models.PriceZone;
import ru.bspl.pet.tradingmarket.services.PriceZoneService;

@Controller
@RequestMapping("/pricezones")
public class PriceZonesController {

    private final PriceZoneService priceZoneService;

    @Autowired
    public PriceZonesController(PriceZoneService priceZoneService) {
        this.priceZoneService = priceZoneService;
    }

    @GetMapping()
    public String show(Model model){
        model.addAttribute("priceZones", priceZoneService.findAll());
        model.addAttribute("header", "Pricezones list");
        return "pricezones/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("priceZone", new PriceZone());
        model.addAttribute("header", "Pricezones-add new");
        return "pricezones/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("priceZone") PriceZone priceZone){
        priceZoneService.save(priceZone);
        return "redirect:/pricezones";
    }
}
