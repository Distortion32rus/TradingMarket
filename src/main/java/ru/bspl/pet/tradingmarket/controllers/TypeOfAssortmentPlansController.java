package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bspl.pet.tradingmarket.models.TypeOfAssortmentPlans;
import ru.bspl.pet.tradingmarket.services.TypeOfAssortmentPlansService;

@Controller
@RequestMapping("/typesofassortmentplans")
public class TypeOfAssortmentPlansController {


    private final TypeOfAssortmentPlansService typeOfAssortmentPlansService;

    @Autowired
    public TypeOfAssortmentPlansController(TypeOfAssortmentPlansService typeOfAssortmentPlansService) {
        this.typeOfAssortmentPlansService = typeOfAssortmentPlansService;
    }

    @GetMapping("")
    public String show(Model model){
        model.addAttribute("typesofassortmentplans", typeOfAssortmentPlansService.findAll());
        model.addAttribute("header", "types of assortment plans list");
        return "typesofassortmentplans/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("typeOfAssortmentPlans", new TypeOfAssortmentPlans());
        model.addAttribute("header", "types of assortment plans add new");
        return "typesofassortmentplans/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("typeOfAssortmentPlans") TypeOfAssortmentPlans typeOfAssortmentPlans){
        typeOfAssortmentPlansService.save(typeOfAssortmentPlans);
        return "redirect:/typesofassortmentplans";

    }

}
