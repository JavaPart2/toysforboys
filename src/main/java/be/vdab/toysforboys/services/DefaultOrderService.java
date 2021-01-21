package be.vdab.toysforboys.services;

import be.vdab.toysforboys.domain.Order;
import be.vdab.toysforboys.domain.OrderDetail;
import be.vdab.toysforboys.forms.OrderForm;
import be.vdab.toysforboys.forms.OrderFormList;
import be.vdab.toysforboys.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class DefaultOrderService implements OrderService {
    private final OrderRepository repository;

    public DefaultOrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> findById(int id) {
        return repository.findOrderById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderFormList findUnshippedOrders() {
        OrderFormList orderForms = new OrderFormList();

        for (Order order: repository.findUnshippedOrders()) {
            OrderForm orderForm = new OrderForm(order,false);
            orderForms.addOrderForm(orderForm);
        }
        return orderForms;
    }

    @Override
    public boolean setOrderAsShipped(int id) {
        Boolean orderCanBeShipped = true;
        Order orderToBeShipped = repository.findOrderById(id).get();
        Set<OrderDetail> orderDetailsOrderToBeShipped = new LinkedHashSet<>();

        orderDetailsOrderToBeShipped.addAll(orderToBeShipped.getOrderDetails());

        // Check Stock for every orderdetail
        for (OrderDetail orderDetail : orderDetailsOrderToBeShipped) {
            if (!orderDetail.checkProductStock()){
                orderCanBeShipped = false;
                break;
            }
        }

        // If Stock is ok for every orderdetail then update stock and return true else false
        if (orderCanBeShipped){
            for (OrderDetail orderDetail : orderDetailsOrderToBeShipped) {
                orderDetail.getProduct().updateQuantity(orderDetail.getQuantityOrdered());
            }
            orderToBeShipped.shipOrder();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Order> shipOrders(OrderFormList orderForms) {
        List<Order> failedOrders = new ArrayList<>();

        for (OrderForm orderForm : orderForms.getFormList()) {
            if (orderForm.isShip()){
                if (!setOrderAsShipped(orderForm.getOrder().getId())){
                    failedOrders.add(orderForm.getOrder());
                }
            }
        }
        return failedOrders;
    }
}
