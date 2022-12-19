package ru.bspl.pet.tradingmarket.controllers;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.models.AssortmentPlan;
import ru.bspl.pet.tradingmarket.models.Counterparty;
import ru.bspl.pet.tradingmarket.services.CounterpartyService;

import java.util.stream.IntStream;

@Controller
@RequestMapping("/counterparties")
public class CounterpartyController {


    private final CounterpartyService counterpartyService;

    @Autowired
    public CounterpartyController(CounterpartyService counterpartyService) {
        this.counterpartyService = counterpartyService;
    }

    /*@GetMapping()
    public String index(Model model){
        model.addAttribute("counterparties", counterpartyService.findAll());
        model.addAttribute("header", "Список поставщиков");
        return "counterparties/index";
    }*/

    @GetMapping()
    public String index(Model model,
                        @RequestParam(name="page", required = false, defaultValue = "0") Integer page){
        Page<Counterparty> counterpartyPage = counterpartyService.findAll(page, 15);

        model.addAttribute("counterpartyPage", counterpartyPage);
        model.addAttribute("numbers", IntStream.range(0, counterpartyPage.getTotalPages()).toArray());
        model.addAttribute("header", "Список поставщиков");
        return "counterparties/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("counterparty", new Counterparty());
        model.addAttribute("header", "Добавление поставщика");
        return "counterparties/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("counterparty") Counterparty counterparty){
        try {
            counterpartyService.save(counterparty);
        }catch(HibernateException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/counterparties";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("counterparty", counterpartyService.findOne(id));
        model.addAttribute("header", "Изменение поставщика");
        return "counterparties/edit";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("counterparty") Counterparty counterparty, @PathVariable("id") Long id){
        counterpartyService.update(id, counterparty);
        return "redirect:/counterparties";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        counterpartyService.delete(id);
        return "redirect:/counterparties";
    }

}
