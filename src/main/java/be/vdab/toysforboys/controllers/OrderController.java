package be.vdab.toysforboys.controllers;

import be.vdab.toysforboys.forms.OrderFormList;
import be.vdab.toysforboys.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
    public ModelAndView shipOrder(@Valid OrderFormList orderForms, Errors errors){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("failedOrders",
                orderService.shipOrders(orderForms));
        modelAndView.addObject("orderforms", orderService.findUnshippedOrders());
        return modelAndView;
    }

    @InitBinder("orderForms")
    void initBinder(DataBinder binder){
        binder.initDirectFieldAccess();
    }
}
