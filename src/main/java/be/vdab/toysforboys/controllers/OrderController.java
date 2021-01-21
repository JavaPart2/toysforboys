package be.vdab.toysforboys.controllers;

import be.vdab.toysforboys.domain.Order;
import be.vdab.toysforboys.forms.OrderForm;
import be.vdab.toysforboys.forms.OrderFormList;
import be.vdab.toysforboys.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("orderforms", orderService.findUnshippedOrders());
        modelAndView.addObject("failedOrders", null);
        return modelAndView;
    }

    @GetMapping("order/{id}")
    public ModelAndView orderDetail(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("order");
        orderService.findById(id).ifPresent(order -> {
            modelAndView.addObject("order", order);
            modelAndView.addObject("totalvalue", order.calculateTotalValue());
        });
        return modelAndView;
    }

    @PostMapping("orders/ship")
    public ModelAndView shipOrder(OrderFormList orderFormList){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("failedOrders",
                orderService.shipOrders(orderFormList));
        modelAndView.addObject("orderforms", orderService.findUnshippedOrders());
        return modelAndView;
    }
}
