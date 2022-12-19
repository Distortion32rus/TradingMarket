package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import ru.bspl.pet.tradingmarket.models.Agreement;
import ru.bspl.pet.tradingmarket.models.PriceZone;
import ru.bspl.pet.tradingmarket.services.PriceZoneService;

import java.util.stream.IntStream;

@Controller
@RequestMapping("/pricezones")
public class PriceZonesController {

    private final PriceZoneService priceZoneService;

    @Autowired
    public PriceZonesController(PriceZoneService priceZoneService) {
        this.priceZoneService = priceZoneService;
    }

    /*@GetMapping()
    public String show(Model model){
        model.addAttribute("priceZones", priceZoneService.findAll());
        model.addAttribute("header", "Список ценовых зон");
        return "pricezones/index";
    }*/

    @GetMapping()
    public String show(Model model,
                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page){
        Page<PriceZone> priceZonePage = priceZoneService.findAll(page, 15);

        model.addAttribute("priceZonePage", priceZonePage);
        model.addAttribute("numbers", IntStream.range(0, priceZonePage.getTotalPages()).toArray());
        model.addAttribute("header", "Список ценовых зон");
        return "pricezones/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("priceZone", new PriceZone());
        model.addAttribute("header", "Добавление ценовой зоны");
        return "pricezones/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("priceZone") PriceZone priceZone){
        priceZoneService.save(priceZone);
        return "redirect:/pricezones";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("priceZone", priceZoneService.findOne(id));
        model.addAttribute("header", "Изменение ценовой зоны");
        return "pricezones/edit";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("priceZone") PriceZone priceZone, @PathVariable("id") Long id){
        priceZoneService.update(id, priceZone);
        return "redirect:/pricezones";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        priceZoneService.delete(id);
        return "redirect:/pricezones";
    }
}
