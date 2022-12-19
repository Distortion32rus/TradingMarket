package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.models.Nomenclature;
import ru.bspl.pet.tradingmarket.services.AssortmentPlanService;
import ru.bspl.pet.tradingmarket.services.NomenclatureService;

import java.util.stream.IntStream;

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

    /*@GetMapping()
    public String show(Model model){
        model.addAttribute("nomenclatures", nomenclatureService.findAll());
        model.addAttribute("header", "Список номенклатуры");
        return "nomenclatures/index";
    }*/

    @GetMapping("")
    public String show(Model model,
                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page){
        Page<Nomenclature> nomenclaturePage = nomenclatureService.findAll(page, 15);

        model.addAttribute("nomenclaturePage", nomenclaturePage);
        model.addAttribute("numbers", IntStream.range(0, nomenclaturePage.getTotalPages()).toArray());
        model.addAttribute("header", "Список номенклатуры");
        return "nomenclatures/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("nomenclature", new Nomenclature());
        model.addAttribute("assortmentPlans", assortmentPlanService.findAll());
        model.addAttribute("header", "Добавление номенклатуры");
        return "nomenclatures/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("nomenclature") Nomenclature nomenclature){
        nomenclatureService.save(nomenclature);
        return "redirect:/nomenclatures";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("nomenclature", nomenclatureService.findOne(id));
        model.addAttribute("assortmentPlans", assortmentPlanService.findAll());
        model.addAttribute("header", "Изменение номенклатуры");
        return "nomenclatures/edit";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("nomenclature") Nomenclature nomenclature, @PathVariable("id") Long id){
        nomenclatureService.update(id, nomenclature);
        return "redirect:/nomenclatures";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        nomenclatureService.delete(id);
        return "redirect:/nomenclatures";
    }

}
