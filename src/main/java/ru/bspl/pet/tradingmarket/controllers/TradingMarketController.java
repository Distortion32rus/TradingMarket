package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.models.*;
import ru.bspl.pet.tradingmarket.services.StoreService;
import ru.bspl.pet.tradingmarket.services.TradingMarketDistributionService;
import ru.bspl.pet.tradingmarket.services.TradingMarketService;
import ru.bspl.pet.tradingmarket.services.TradingMarketStoresService;

@Controller
@RequestMapping("/tradingmarkets")
public class TradingMarketController {

    private final TradingMarketService tradingMarketService;
    private final TradingMarketDistributionService tradingMarketDistributionService;
    private final TradingMarketStoresService tradingMarketStoresService;
    private final StoreService storeService;

    @Autowired
    public TradingMarketController(TradingMarketService tradingMarketService, TradingMarketDistributionService tradingMarketDistributionService, TradingMarketStoresService tradingMarketStoresService, StoreService storeService) {
        this.tradingMarketService = tradingMarketService;
        this.tradingMarketDistributionService = tradingMarketDistributionService;
        this.tradingMarketStoresService = tradingMarketStoresService;
        this.storeService = storeService;
    }

    @GetMapping()
    public String show(Model model){
        model.addAttribute("tradingMarkets", tradingMarketService.findAll());
        model.addAttribute("header", "Trading markets list");
        return "tradingmarkets/index";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("tradingMarket", new TradingMarket());
        model.addAttribute("header", "Trading markets  add new");
        return "tradingmarkets/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("tradingMarket") TradingMarket tradingMarket){
        tradingMarketService.save(tradingMarket);
        return "redirect:/tradingmarkets";
    }

    @GetMapping("/{id}/open")
    public String edit(Model model, @PathVariable("id") Long id){
        TradingMarket tm = tradingMarketService.findOne(id);
        model.addAttribute("tradingMarket", tm);
        model.addAttribute("tradingMarketStores", tradingMarketStoresService.findByTradingMarket(tm));
        model.addAttribute("tradingMarketDistributions", tradingMarketDistributionService.findByTradingMarket(tm));
        model.addAttribute("header", "Trading markets edit");
        return "tradingmarkets/open";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("tradingMarket") TradingMarket tradingMarket, @PathVariable("id") Long id){
        tradingMarketService.update(id, tradingMarket);
        return "redirect:/tradingmarkets";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        TradingMarket tm = tradingMarketService.findOne(id);
        tradingMarketStoresService.deleteAll(tradingMarketStoresService.findByTradingMarket(tm));
        tradingMarketDistributionService.deleteAll(tradingMarketDistributionService.findByTradingMarket(tm));
        tradingMarketService.delete(id);
        return "redirect:/tradingmarkets";
    }

    @GetMapping("/{id}/newstore")
    public String addFormNewStore(Model model, @PathVariable("id") Long id){
        TradingMarket tm = tradingMarketService.findOne(id);
        //model.addAttribute("tradingMarketStore",new TradingMarketStores(new TradingMarketStoresId(tm, null), tm, null));
        model.addAttribute("tradingMarket",  tm);
        model.addAttribute("store",  new Store());
        model.addAttribute("stores", storeService.findAll());
        model.addAttribute("header", "threshold values add new row");
        return "tradingmarkets/newstore";
    }

    @PostMapping("/{id}/newstore")
    public String addStore(@PathVariable("id") Long id, @ModelAttribute("storeId") Store store){
        TradingMarket tm = tradingMarketService.findOne(id);
        store = storeService.findOne(store.getId());
        TradingMarketStores tradingMarketStores = new TradingMarketStores();
        tradingMarketStores.setId(new TradingMarketStoresId(tm,store));
        tradingMarketStores.setTradingMarket(tm);
        tradingMarketStores.setStore(store);
        tradingMarketStoresService.save(tradingMarketStores);
        return "redirect:/tradingmarkets/"+id+"/open";
    }

    @GetMapping("/{id}/{storeid}/edit")
    public String editStore(Model model, @PathVariable("id") Long id, @PathVariable("storeid") Long storeId){
        TradingMarketStoresId tradingMarketStoresId = new TradingMarketStoresId(tradingMarketService.findOne(id),storeService.findOne(storeId));
        TradingMarketStores tradingMarketStore = tradingMarketStoresService.findById(tradingMarketStoresId);
        model.addAttribute("tradingMarketStore", tradingMarketStore);
        model.addAttribute("store", tradingMarketStore.getStore());
        model.addAttribute("stores", storeService.findAll());
        model.addAttribute("header", "Agreements-add new");
        tradingMarketStoresService.deleteById(tradingMarketStoresId);
        return "tradingmarkets/storeedit";
    }

    @PostMapping("/{id}/{storeid}/edit")
    public String updateRow(@ModelAttribute("store") Store store,
                            @PathVariable("id") Long id, @PathVariable("storeid") Long storeId){
        TradingMarket tm = tradingMarketService.findOne(id);
        store = storeService.findOne(store.getId());
        TradingMarketStores tradingMarketStores = new TradingMarketStores();
        tradingMarketStores.setId(new TradingMarketStoresId(tm,store));
        tradingMarketStores.setTradingMarket(tm);
        tradingMarketStores.setStore(store);
        tradingMarketStoresService.save(tradingMarketStores);
        return "redirect:/tradingmarkets/"+id+"/open";
    }

    @GetMapping("/{id}/{storeid}/delete")
    public String deleteRow(@PathVariable("id") Long id, @PathVariable("storeid") Long storeId){
        tradingMarketStoresService.deleteById(new TradingMarketStoresId(tradingMarketService.findOne(id),storeService.findOne(storeId)));
        return "redirect:/tradingmarkets/"+id+"/open";
    }

    @GetMapping("/{id}/calc")
    public String traidingMarketCalc(@PathVariable("id") Long id){
        tradingMarketService.tradingMarketCalc(id);
        return "redirect:/tradingmarkets/"+id+"/open";
    }


}
