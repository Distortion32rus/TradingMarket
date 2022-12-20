package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.models.AssortmentPlan;
import ru.bspl.pet.tradingmarket.models.Organization;
import ru.bspl.pet.tradingmarket.services.OrganizationService;

import java.util.stream.IntStream;

@Controller
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping()
    public String show(Model model,
                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page){
        Page<Organization> organizationPage = organizationService.findAll(page, 15);

        model.addAttribute("organizationPage", organizationPage);
        model.addAttribute("numbers", IntStream.range(0, organizationPage.getTotalPages()).toArray());
        model.addAttribute("header", "Список организаций");
        return "organizations/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("organization", new Organization());
        model.addAttribute("header", "Добавление организации");
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
        model.addAttribute("header", "Изменение организации");
        return "organizations/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("organization") Organization organization, @PathVariable("id") Long id){
        organizationService.update(id, organization);
        return "redirect:/organizations";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        organizationService.delete(id);
        return "redirect:/organizations";
    }
}
