package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bspl.pet.tradingmarket.models.CounterpartsNomenclature;
import ru.bspl.pet.tradingmarket.services.CounterpartsNomenclatureService;
import ru.bspl.pet.tradingmarket.services.NomenclatureService;

@Controller
@RequestMapping("/counterpartsnomenclatures")
public class CounterpartsNomenclatureController {


    private final CounterpartsNomenclatureService counterpartsNomenclatureService;

    private final NomenclatureService nomenclatureService;

    public CounterpartsNomenclatureController(CounterpartsNomenclatureService counterpartsNomenclatureService, NomenclatureService nomenclatureService) {
        this.counterpartsNomenclatureService = counterpartsNomenclatureService;
        this.nomenclatureService = nomenclatureService;
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
        model.addAttribute("header", "counterparts nomenclatures add new");
        return "counterpartsnomenclatures/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("counterpartsNomenclature") CounterpartsNomenclature counterpartsNomenclature){
        counterpartsNomenclatureService.save(counterpartsNomenclature);
        return "redirect:/counterpartsnomenclatures";
    }


}
