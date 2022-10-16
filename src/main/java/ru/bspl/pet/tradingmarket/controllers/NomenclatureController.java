package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bspl.pet.tradingmarket.models.Nomenclature;
import ru.bspl.pet.tradingmarket.services.AssortmentPlanService;
import ru.bspl.pet.tradingmarket.services.NomenclatureService;

@Controller
@RequestMapping("/nomenclatures")
public class NomenclatureController {


    private final NomenclatureService nomenclatureService;
    private final AssortmentPlanService assortmentPlanService;

    @Autowired
    public NomenclatureController(NomenclatureService nomenclatureService, AssortmentPlanService assortmentPlanService) {
        this.nomenclatureService = nomenclatureService;
        this.assortmentPlanService = assortmentPlanService;
    }

    @GetMapping()
    public String show(Model model){
        model.addAttribute("nomenclatures", nomenclatureService.findAll());
        model.addAttribute("header", "nomenclatures  list");
        return "nomenclatures/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("nomenclature", new Nomenclature());
        model.addAttribute("assortmentPlans", assortmentPlanService.findAll());
        model.addAttribute("header", "nomenclatures  add new");
        return "nomenclatures/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("nomenclature") Nomenclature nomenclature){
        nomenclatureService.save(nomenclature);
        return "redirect:/nomenclatures";
    }
}
