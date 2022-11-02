package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.models.Agreement;
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
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("typeOfAssortmentPlans", typeOfAssortmentPlansService.findOne(id));
        model.addAttribute("header", "Agreements-add new");
        return "typesofassortmentplans/edit";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("typeOfAssortmentPlans") TypeOfAssortmentPlans typeOfAssortmentPlans, @PathVariable("id") Long id){
        typeOfAssortmentPlansService.update(id, typeOfAssortmentPlans);
        return "redirect:/typesofassortmentplans";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        typeOfAssortmentPlansService.delete(id);
        return "redirect:/typesofassortmentplans";
    }

}
