package ru.bspl.pet.tradingmarket.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bspl.pet.tradingmarket.models.BusinessUnit;
import ru.bspl.pet.tradingmarket.services.BusinessUnitService;

@Controller
@RequestMapping("/businessunits")
public class BusinessUnitsController {


    private final BusinessUnitService businessUnitService;

    @Autowired
    public BusinessUnitsController(BusinessUnitService businessUnitService) {
        this.businessUnitService = businessUnitService;
    }

    @GetMapping
    public String show(Model model){
        Iterable<BusinessUnit> businessUnits = businessUnitService.findAll();
        model.addAttribute("businessUnits", businessUnits);
        model.addAttribute("header", "businessUnits list");
        return "businessunits/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute(new BusinessUnit());
        model.addAttribute("header", "businessUnits add new");
        return "businessunits/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("businessUnit") BusinessUnit businessUnit){
        businessUnitService.save(businessUnit);
        return "redirect:/businessunits";
    }
}
