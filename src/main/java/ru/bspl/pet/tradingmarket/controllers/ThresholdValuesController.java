package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.models.Agreement;
import ru.bspl.pet.tradingmarket.models.ThresholdValues;
import ru.bspl.pet.tradingmarket.services.ThresholdValuesService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/thresholdvalues")
public class ThresholdValuesController {
    
    private final ThresholdValuesService thresholdValuesService;

    @Autowired
    public ThresholdValuesController(ThresholdValuesService thresholdValuesService) {
        this.thresholdValuesService = thresholdValuesService;
    }
    @GetMapping()
    public String show(Model model){
        model.addAttribute("thresholdvalues", thresholdValuesService.findAll());
        model.addAttribute("header", "threshold values  list");
        return "thresholdvalues/index";
    }
    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("thresholdvalue",new ThresholdValues());
        model.addAttribute("header", "threshold values add new");
        return "thresholdvalues/new";
    }
    @PostMapping()
    public String add(@ModelAttribute("thresholdvalue") ThresholdValues thresholdValues){
        thresholdValuesService.save(thresholdValues);
        return "redirect:/thresholdvalues";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("thresholdValue", thresholdValuesService.findOne(id));
        model.addAttribute("header", "Agreements-add new");
        return "thresholdvalues/edit";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("thresholdvalue") ThresholdValues thresholdValues, @PathVariable("id") Long id){
        thresholdValuesService.update(id, thresholdValues);
        return "redirect:/thresholdvalues";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        thresholdValuesService.delete(id);
        return "redirect:/thresholdvalues";
    }

}
