package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.models.CounterpartsNomenclature;
import ru.bspl.pet.tradingmarket.services.CounterpartsNomenclatureService;
import ru.bspl.pet.tradingmarket.services.CounterpartyService;
import ru.bspl.pet.tradingmarket.services.NomenclatureService;

@Controller
@RequestMapping("/counterpartsnomenclatures")
public class CounterpartsNomenclatureController {


    private final CounterpartsNomenclatureService counterpartsNomenclatureService;

    private final NomenclatureService nomenclatureService;

    private final CounterpartyService counterpartyService;

    public CounterpartsNomenclatureController(CounterpartsNomenclatureService counterpartsNomenclatureService, NomenclatureService nomenclatureService, CounterpartyService counterpartyService) {
        this.counterpartsNomenclatureService = counterpartsNomenclatureService;
        this.nomenclatureService = nomenclatureService;
        this.counterpartyService = counterpartyService;
    }

    @GetMapping()
    public String show(Model model){
        model.addAttribute("counterpartsNomenclatures", counterpartsNomenclatureService.findAll());
        model.addAttribute("header", "counterparts nomenclatures list");
        return "counterpartsnomenclatures/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("counterpartsNomenclature", new CounterpartsNomenclature());
        model.addAttribute("nomenclatures", nomenclatureService.findAll());
        model.addAttribute("counterparties", counterpartyService.findAll());
        model.addAttribute("header", "counterparts nomenclatures add new");
        return "counterpartsnomenclatures/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("counterpartsNomenclature") CounterpartsNomenclature counterpartsNomenclature){
        counterpartsNomenclatureService.save(counterpartsNomenclature);
        return "redirect:/counterpartsnomenclatures";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("counterpartsNomenclature", counterpartsNomenclatureService.findOne(id));
        model.addAttribute("nomenclatures", nomenclatureService.findAll());
        model.addAttribute("counterparties", counterpartyService.findAll());
        model.addAttribute("header", "Agreements - edit");  //TODO Испраовить хэдэры
        return "counterpartsnomenclatures/edit";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("counterpartsNomenclature") CounterpartsNomenclature counterpartsNomenclature, @PathVariable("id") Long id){
        counterpartsNomenclatureService.update(id, counterpartsNomenclature);
        return "redirect:/counterpartsnomenclatures";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        counterpartsNomenclatureService.delete(id);
        return "redirect:/counterpartsnomenclatures";
    }




}
