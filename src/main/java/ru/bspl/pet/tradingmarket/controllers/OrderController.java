package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bspl.pet.tradingmarket.models.Order;
import ru.bspl.pet.tradingmarket.models.OrderPosition;
import ru.bspl.pet.tradingmarket.services.OrderPositionService;
import ru.bspl.pet.tradingmarket.services.OrderService;

import java.util.stream.IntStream;

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

    /*@GetMapping()
    public String show(Model model){
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("header", "Список заказов");
        return "orders/index";
    }*/

    @GetMapping()
    public String show(Model model,
                       @RequestParam(name="page", required = false, defaultValue = "0") Integer page){
        Page<Order> orderPage = orderService.findAll(page, 15);

        model.addAttribute("orderPage", orderPage);
        model.addAttribute("numbers", IntStream.range(0, orderPage.getTotalPages()).toArray());
        model.addAttribute("header", "Список заказов");
        return "orders/index";
    }

    @GetMapping("/{id}/open")
    public String edit(Model model, @PathVariable("id") Long id,
                       @RequestParam(name="page", required = false, defaultValue = "0") Integer page){

        Order order = orderService.findOne(id);

        Page<OrderPosition> orderPositionPage = orderPositionService.findByOrder(order, page, 15);

        model.addAttribute("orderPositionPage", orderPositionPage);
        model.addAttribute("order", orderService.findOne(id));
        model.addAttribute("numbers", IntStream.range(0, orderPositionPage.getTotalPages()).toArray());
        model.addAttribute("header", "Заказ #"+id);
        return "orders/open";
    }

}
