package be.vdab.toysforboys.controllers;

import be.vdab.toysforboys.domain.Order;
import be.vdab.toysforboys.forms.OrderFormList;
import be.vdab.toysforboys.services.OrderService;
import be.vdab.toysforboys.sessions.CheckedOrders;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class OrderController {
    private final CheckedOrders checkedOrders;
    private final OrderService orderService;

    public OrderController(CheckedOrders checkedOrders, OrderService orderService) {
        this.checkedOrders = checkedOrders;
        this.orderService = orderService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject(orderService.findUnshippedOrders(checkedOrders));
        return modelAndView;
    }

    @GetMapping("order/check/{id}")
    public ModelAndView checkOrder(@PathVariable int id){
        checkedOrders.voegToe(id);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject(orderService.findUnshippedOrders(checkedOrders));
        return modelAndView;
    }

    @GetMapping("order/uncheck/{id}")
    public ModelAndView uncheckOrder(@PathVariable int id){
        checkedOrders.verwijder(id);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject(orderService.findUnshippedOrders(checkedOrders));
        return modelAndView;
    }

    @GetMapping("order/{id}")
    public ModelAndView orderDetail(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("order");
        modelAndView.addObject("orderinfo",orderService.findById(id));
        return modelAndView;
    }

    @PostMapping("orders/ship")
    public ModelAndView shipOrder(){
        ModelAndView modelAndView = new ModelAndView("index");
        List<Order> failedOrderList = orderService.shipOrders(checkedOrders);
        modelAndView.addObject("failedOrders", failedOrderList);
        if (failedOrderList.size() > 0){
            modelAndView.addObject("fOrders", failedOrderList.size());
        }
        modelAndView.addObject(orderService.findUnshippedOrders(checkedOrders));
        return modelAndView;
    }

/*
    @InitBinder("orderforms")
    void initBinder(DataBinder binder){
        binder.initDirectFieldAccess();
    }
*/
}
