package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.models.CounterpartsNomenclature;
import ru.bspl.pet.tradingmarket.services.CounterpartsNomenclatureService;
import ru.bspl.pet.tradingmarket.services.CounterpartyService;
import ru.bspl.pet.tradingmarket.services.NomenclatureService;

import java.util.stream.IntStream;

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
    public String show(Model model,
                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page){
        Page<CounterpartsNomenclature> counterpartsNomenclaturePage = counterpartsNomenclatureService.findAll(page, 15);
        model.addAttribute("counterpartsNomenclaturePage", counterpartsNomenclaturePage);
        model.addAttribute("numbers", IntStream.range(0, counterpartsNomenclaturePage.getTotalPages()).toArray());
        model.addAttribute("header", "Список номенклатур поставщика");
        return "counterpartsnomenclatures/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("counterpartsNomenclature", new CounterpartsNomenclature());
        model.addAttribute("nomenclatures", nomenclatureService.findAll());
        model.addAttribute("counterparties", counterpartyService.findAll());
        model.addAttribute("header", "Добавление номенклатуры поставщика");
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
        model.addAttribute("header", "Изменение номенклатуры поставщика");
        return "counterpartsnomenclatures/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("counterpartsNomenclature") CounterpartsNomenclature counterpartsNomenclature, @PathVariable("id") Long id){
        counterpartsNomenclatureService.update(id, counterpartsNomenclature);
        return "redirect:/counterpartsnomenclatures";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        counterpartsNomenclatureService.delete(id);
        return "redirect:/counterpartsnomenclatures";
    }




}
