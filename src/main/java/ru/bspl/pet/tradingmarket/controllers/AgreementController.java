package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.models.Agreement;
import ru.bspl.pet.tradingmarket.services.AgreementService;
import ru.bspl.pet.tradingmarket.services.CounterpartyService;
import ru.bspl.pet.tradingmarket.services.OrganizationService;

@Controller
@RequestMapping("/agreements")
public class AgreementController {

    private final AgreementService agreementService;
    private final OrganizationService organizationService;
    private final CounterpartyService counterpartyService;

    @Autowired
    public AgreementController(AgreementService agreementService, OrganizationService organizationService, CounterpartyService counterpartyService) {
        this.agreementService = agreementService;
        this.organizationService = organizationService;
        this.counterpartyService = counterpartyService;
    }

    @GetMapping()
    public String show(Model model) {
        model.addAttribute("agreements", agreementService.findAll());
        model.addAttribute("header", "Agreements list");
        return "agreements/index";
    }

    @GetMapping("/new")
    public String addForm(Model model) {
        model.addAttribute("organizations", organizationService.findAll());
        model.addAttribute("counterparties", counterpartyService.findAll());
        model.addAttribute("agreement", new Agreement());
        model.addAttribute("header", "Agreements-add new");
        return "agreements/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("agreement") Agreement agreement) {
        agreementService.save(agreement);
        return "redirect:/agreements";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("organizations", organizationService.findAll());
        model.addAttribute("counterparties", counterpartyService.findAll());
        model.addAttribute("agreement", agreementService.findOne(id));
        model.addAttribute("header", "Agreements-add new");
        return "agreements/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("agreement") Agreement agreement, @PathVariable("id") Long id) {
        agreementService.update(id, agreement);
        return "redirect:/agreements";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        agreementService.delete(id);
        return "redirect:/agreements";
    }

}
