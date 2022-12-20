package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.models.ThresholdCategories;
import ru.bspl.pet.tradingmarket.models.ThresholdValues;
import ru.bspl.pet.tradingmarket.models.ThresholdValuesTable;
import ru.bspl.pet.tradingmarket.services.ThresholdValuesService;
import ru.bspl.pet.tradingmarket.services.ThresholdValuesTableService;

import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/thresholdvalues")
public class ThresholdValuesController {
    
    private final ThresholdValuesService thresholdValuesService;
    private final ThresholdValuesTableService thresholdValuesTableService;

    @Autowired
    public ThresholdValuesController(ThresholdValuesService thresholdValuesService, ThresholdValuesTableService thresholdValuesTableService) {
        this.thresholdValuesService = thresholdValuesService;
        this.thresholdValuesTableService = thresholdValuesTableService;
    }

    @GetMapping()
    public String show(Model model,
                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page){
        Page<ThresholdValues> thresholdValuesPage = thresholdValuesService.findAll(page, 15);

        model.addAttribute("thresholdValuesPage", thresholdValuesPage);
        model.addAttribute("numbers", IntStream.range(0, thresholdValuesPage.getTotalPages()).toArray());
        model.addAttribute("header", "Список ведомостей ограничений");
        return "thresholdvalues/index";
    }

    @GetMapping("/new")
    public String addFormNew(Model model){
        model.addAttribute("thresholdValue",new ThresholdValues());
        model.addAttribute("header", "Добавление ведомости");
        return "thresholdvalues/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("thresholdvalue") ThresholdValues thresholdValues){
        thresholdValuesService.save(thresholdValues);
        return "redirect:/thresholdvalues";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id,
                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page){
        ThresholdValues thresholdValue = thresholdValuesService.findOne(id);

        Page<ThresholdValuesTable> thresholdValuesTablePage = thresholdValuesTableService.findByThresholdValueAll(thresholdValue, page, 15);

        model.addAttribute("thresholdValue", thresholdValue);
        model.addAttribute("thresholdValuesTablePage", thresholdValuesTablePage);
        model.addAttribute("numbers", IntStream.range(0, thresholdValuesTablePage.getTotalPages()).toArray());
        model.addAttribute("header", "Изменение ведомости");
        return "thresholdvalues/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("thresholdvalue") ThresholdValues thresholdValues,
                         @PathVariable("id") Long id){
        thresholdValuesService.update(id, thresholdValues);
        return "redirect:/thresholdvalues";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        thresholdValuesTableService.deleteAll(thresholdValuesTableService.findByThresholdValueAll(thresholdValuesService.findOne(id)));
        thresholdValuesService.delete(id);
        return "redirect:/thresholdvalues";
    }

    @GetMapping("/{id}/newrow")
    public String addFormNewRow(Model model, @PathVariable("id") Long id){
        model.addAttribute("thresholdValuesTable",new ThresholdValuesTable(thresholdValuesService.findOne(id)));
        model.addAttribute("thresholdCategories", ThresholdCategories.values());
        model.addAttribute("header", "Добавление ограничения");
        return "thresholdvalues/newrow";
    }

    @PostMapping("/{id}/newrow")
    public String addRow(@PathVariable("id") Long id, @ModelAttribute("thresholdValuesTable") ThresholdValuesTable thresholdValuesTable){
        thresholdValuesTable.setThresholdValues(thresholdValuesService.findOne(id));
        thresholdValuesTableService.save(thresholdValuesTable);
        return "redirect:/thresholdvalues/"+id+"/edit";
    }

    @GetMapping("/{id}/{rowid}/edit")
    public String editRow(Model model, @PathVariable("id") Long id, @PathVariable("rowid") Long rowid){
        ThresholdValuesTable thresholdValuesTable = thresholdValuesTableService.findOne(rowid);
        model.addAttribute("thresholdValuesTable", thresholdValuesTable);
        model.addAttribute("thresholdCategories", ThresholdCategories.values());
        model.addAttribute("header", "Изменение ограничения");
        return "thresholdvalues/rowedit";
    }

    @PatchMapping("/{id}/{rowid}/edit")
    public String updateRow(@ModelAttribute("thresholdvalue") ThresholdValuesTable thresholdValuesTable,
                         @PathVariable("id") Long id, @PathVariable("rowid") Long rowid){
        thresholdValuesTable.setId(rowid);
        thresholdValuesTable.setThresholdValues(thresholdValuesService.findOne(id));
        thresholdValuesTableService.update(thresholdValuesTable);
        return "redirect:/thresholdvalues/"+id+"/edit";
    }

    @DeleteMapping("/{id}/{rowid}")
    public String deleteRow(@PathVariable("id") Long id, @PathVariable("rowid") Long rowid){
        thresholdValuesTableService.delete(rowid);
        return "redirect:/thresholdvalues/"+id+"/edit";
    }

}
