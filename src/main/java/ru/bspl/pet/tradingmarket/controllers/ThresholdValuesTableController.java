package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bspl.pet.tradingmarket.models.ThresholdValuesTable;
import ru.bspl.pet.tradingmarket.services.ThresholdValuesService;
import ru.bspl.pet.tradingmarket.services.ThresholdValuesTableService;

@Controller
@RequestMapping("/thresholdvaluestable")
public class ThresholdValuesTableController {


    private final ThresholdValuesTableService thresholdValuesTableService;
    private final ThresholdValuesService thresholdValuesService;

    @Autowired
    public ThresholdValuesTableController(ThresholdValuesTableService thresholdValuesTableService, ThresholdValuesService thresholdValuesService) {
        this.thresholdValuesTableService = thresholdValuesTableService;
        this.thresholdValuesService = thresholdValuesService;
    }

    @GetMapping()
    public String show(Model model){
        model.addAttribute("thresholdvaluestable", thresholdValuesTableService.findAll());
        model.addAttribute("header", "threshold values tables list");
        return "thresholdvaluestable/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("thresholdvaluestable",new ThresholdValuesTable());
        model.addAttribute("thresholdValues", thresholdValuesService.findAll());
        model.addAttribute("header", "threshold values tables add new");
        return "thresholdvaluestable/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("thresholdvaluestable") ThresholdValuesTable thresholdValuesTable){
        thresholdValuesTableService.save(thresholdValuesTable);
        return "redirect:/thresholdvaluestable";
    }

}
