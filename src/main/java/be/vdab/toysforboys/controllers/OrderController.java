package be.vdab.toysforboys.controllers;

import be.vdab.toysforboys.domain.Order;
import be.vdab.toysforboys.forms.OrderForm;
import be.vdab.toysforboys.forms.OrderFormList;
import be.vdab.toysforboys.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

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
        modelAndView.addObject(orderService.findUnshippedOrders());
        return modelAndView;
    }

    @GetMapping("order/{id}")
    public ModelAndView orderDetail(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("order");
        modelAndView.addObject("orderinfo",orderService.findById(id));
        return modelAndView;
    }

    @GetMapping("orders/ship")
    public ModelAndView shipOrder(@Valid OrderFormList orderForms, Errors errors){
        ModelAndView modelAndView = new ModelAndView("index");
        if (!errors.hasErrors()){
            List<Order> failedOrderList = orderService.shipOrders(orderForms);
            modelAndView.addObject("failedOrders", failedOrderList);
            if (failedOrderList.size() > 0){
                modelAndView.addObject("fOrders", failedOrderList.size());
            }
        }
        modelAndView.addObject(orderService.findUnshippedOrders());
        return modelAndView;
    }

/*
    @InitBinder("orderforms")
    void initBinder(DataBinder binder){
        binder.initDirectFieldAccess();
    }
*/
}
