package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bspl.pet.tradingmarket.models.Organization;
import ru.bspl.pet.tradingmarket.services.OrganizationService;

@Controller
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping()
    public String show(Model model){
        model.addAttribute("organizations", organizationService.findAll());
        model.addAttribute("header", "organizations  list");
        return "organizations/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("organization", new Organization());
        model.addAttribute("header", "organizations  add new");
        return "organizations/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("organization") Organization organization){
        organizationService.save(organization);
        return "redirect:/organizations";
    }
}
