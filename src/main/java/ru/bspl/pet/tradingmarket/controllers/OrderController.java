package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bspl.pet.tradingmarket.models.Order;
import ru.bspl.pet.tradingmarket.services.OrderPositionService;
import ru.bspl.pet.tradingmarket.services.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderPositionService orderPositionService;

    @Autowired
    public OrderController(OrderService orderService, OrderPositionService orderPositionService) {
        this.orderService = orderService;
        this.orderPositionService = orderPositionService;
    }

    @GetMapping()
    public String show(Model model){
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("header", "Orders markets list");
        return "orders/index";
    }

    @GetMapping("/{id}/open")
    public String edit(Model model, @PathVariable("id") Long id){
        Order order = orderService.findOne(id);
        model.addAttribute("orderPositions", orderPositionService.findByOrder(order));
        model.addAttribute("header", "Заказ #"+id);
        return "orders/open";
    }

}
