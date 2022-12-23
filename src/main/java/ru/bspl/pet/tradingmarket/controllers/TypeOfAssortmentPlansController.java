package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.models.Agreement;
import ru.bspl.pet.tradingmarket.models.TypeOfAssortmentPlans;
import ru.bspl.pet.tradingmarket.services.TypeOfAssortmentPlansService;

import java.util.stream.IntStream;

@Controller
@RequestMapping("/typesofassortmentplans")
public class TypeOfAssortmentPlansController {


    private final TypeOfAssortmentPlansService typeOfAssortmentPlansService;

    @Autowired
    public TypeOfAssortmentPlansController(TypeOfAssortmentPlansService typeOfAssortmentPlansService) {
        this.typeOfAssortmentPlansService = typeOfAssortmentPlansService;
    }

    @GetMapping()
    public String show(Model model,
                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page){
        Page<TypeOfAssortmentPlans> typeOfAssortmentPlansPage = typeOfAssortmentPlansService.findAll(page, 15);

        model.addAttribute("typeOfAssortmentPlansPage", typeOfAssortmentPlansPage);
        model.addAttribute("numbers", IntStream.range(0, typeOfAssortmentPlansPage.getTotalPages()).toArray());
        model.addAttribute("header", "Список типов ассортиментных планов");
        return "typesofassortmentplans/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("typeOfAssortmentPlans", new TypeOfAssortmentPlans());
        model.addAttribute("header", "Добавление типа ассортиментного плана");
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
        model.addAttribute("header", "Изменение типа ассортиментного плана");
        return "typesofassortmentplans/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("typeOfAssortmentPlans") TypeOfAssortmentPlans typeOfAssortmentPlans, @PathVariable("id") Long id){
        typeOfAssortmentPlansService.update(id, typeOfAssortmentPlans);
        return "redirect:/typesofassortmentplans";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        typeOfAssortmentPlansService.delete(id);
        return "redirect:/typesofassortmentplans";
    }

}
