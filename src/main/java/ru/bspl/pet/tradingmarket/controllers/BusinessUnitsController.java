package ru.bspl.pet.tradingmarket.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.models.AssortmentPlan;
import ru.bspl.pet.tradingmarket.models.BusinessUnit;
import ru.bspl.pet.tradingmarket.services.BusinessUnitService;

import java.util.stream.IntStream;

@Controller
@RequestMapping("/businessunits")
public class BusinessUnitsController {

    private final BusinessUnitService businessUnitService;

    @Autowired
    public BusinessUnitsController(BusinessUnitService businessUnitService) {
        this.businessUnitService = businessUnitService;
    }

    @GetMapping
    public String show(Model model,
                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page){

        Page<BusinessUnit> businessUnitPage = businessUnitService.findAll(page, 15);

        model.addAttribute("businessUnitPage", businessUnitPage);
        model.addAttribute("numbers", IntStream.range(0, businessUnitPage.getTotalPages()).toArray());
        model.addAttribute("header", "Список бизнес единиц");
        return "businessunits/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute(new BusinessUnit());
        model.addAttribute("header", "Добавление бизнес единицы");
        return "businessunits/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("businessUnit") BusinessUnit businessUnit){
        businessUnitService.save(businessUnit);
        return "redirect:/businessunits";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("businessUnit", businessUnitService.findOne(id));
        model.addAttribute("header", "Изменение бизнес единицы");
        return "businessunits/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("businessUnit") BusinessUnit businessUnit, @PathVariable("id") Long id){
        businessUnitService.update(id, businessUnit);
        return "redirect:/businessunits";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        businessUnitService.delete(id);
        return "redirect:/businessunits";
    }
}
