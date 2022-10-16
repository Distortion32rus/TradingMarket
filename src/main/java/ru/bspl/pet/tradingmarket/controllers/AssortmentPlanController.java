package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bspl.pet.tradingmarket.models.AssortmentPlan;
import ru.bspl.pet.tradingmarket.services.AssortmentPlanService;
import ru.bspl.pet.tradingmarket.services.TypeOfAssortmentPlansService;

@Controller
@RequestMapping("/assortmentplans")
public class AssortmentPlanController {


    private final AssortmentPlanService assortmentPlanService;
    private final TypeOfAssortmentPlansService typeOfAssortmentPlansService;

    @Autowired
    public AssortmentPlanController(AssortmentPlanService assortmentPlanService, TypeOfAssortmentPlansService typeOfAssortmentPlansService) {
        this.assortmentPlanService = assortmentPlanService;
        this.typeOfAssortmentPlansService = typeOfAssortmentPlansService;
    }

    @GetMapping()
    public String show(Model model){
        model.addAttribute("assortmentPlans", assortmentPlanService.findAll());
        model.addAttribute("header", "assortment plans list");
        return "assortmentplans/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("assortmentPlan", new AssortmentPlan());
        model.addAttribute("typesOfAssortmentPlans", typeOfAssortmentPlansService.findAll());
        model.addAttribute("header", "assortment plans-add new");
        return "assortmentplans/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("assortmentPlan") AssortmentPlan assortmentPlan){
        assortmentPlanService.save(assortmentPlan);
        return "redirect:/assortmentplans";
    }
}
