package be.vdab.toysforboys.controllers;

import be.vdab.toysforboys.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        modelAndView.addObject("orders", orderService.findUnshippedOrders());
        return modelAndView;
    }

    @GetMapping("order/{id}")
    public ModelAndView orderDetail(@PathVariable int orderid){
        ModelAndView modelAndView = new ModelAndView("order");
        orderService.findById(orderid).ifPresent(order -> {
            modelAndView.addObject("order", order);
        });
        return modelAndView;
    }
}
