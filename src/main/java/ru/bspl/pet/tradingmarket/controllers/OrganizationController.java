package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.models.AssortmentPlan;
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
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("organization", organizationService.findOne(id));
        model.addAttribute("header", "Agreements - edit");  //TODO Испраовить хэдэры
        return "organizations/edit";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("organization") Organization organization, @PathVariable("id") Long id){
        organizationService.update(id, organization);
        return "redirect:/organizations";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        organizationService.delete(id);
        return "redirect:/organizations";
    }
}
