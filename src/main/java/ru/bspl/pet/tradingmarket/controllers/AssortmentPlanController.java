package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.models.Agreement;
import ru.bspl.pet.tradingmarket.models.AssortmentPlan;
import ru.bspl.pet.tradingmarket.services.AssortmentPlanService;
import ru.bspl.pet.tradingmarket.services.TypeOfAssortmentPlansService;

import java.util.stream.IntStream;

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
    public String show(Model model,
                       @RequestParam(name="page", required = false, defaultValue = "0") Integer page){
        Page<AssortmentPlan> assortmentPlanPage = assortmentPlanService.findAll(page, 15);

        model.addAttribute("assortmentPlanPage", assortmentPlanPage);
        model.addAttribute("numbers", IntStream.range(0, assortmentPlanPage.getTotalPages()).toArray());
        model.addAttribute("header", "Список ассортиментных планов");
        return "assortmentplans/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("assortmentPlan", new AssortmentPlan());
        model.addAttribute("typesOfAssortmentPlans", typeOfAssortmentPlansService.findAll());
        model.addAttribute("header", "Добавление ассортиментного плана");
        return "assortmentplans/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("assortmentPlan") AssortmentPlan assortmentPlan){
        assortmentPlanService.save(assortmentPlan);
        return "redirect:/assortmentplans";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("assortmentPlan", assortmentPlanService.findOne(id));
        model.addAttribute("typesOfAssortmentPlans", typeOfAssortmentPlansService.findAll());
        model.addAttribute("header", "Изменение ассортиментного плана");
        return "assortmentplans/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("assortmentPlan") AssortmentPlan assortmentPlan, @PathVariable("id") Long id){
        assortmentPlanService.update(id, assortmentPlan);
        return "redirect:/assortmentplans";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        assortmentPlanService.delete(id);
        return "redirect:/assortmentplans";
    }
}
