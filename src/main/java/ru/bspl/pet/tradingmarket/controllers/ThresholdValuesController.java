package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
