package be.vdab.toysforboys.services;

import be.vdab.toysforboys.domain.Order;
import be.vdab.toysforboys.domain.OrderDetail;
import be.vdab.toysforboys.forms.OrderDetailForm;
import be.vdab.toysforboys.forms.OrderForm;
import be.vdab.toysforboys.forms.OrderFormList;
import be.vdab.toysforboys.forms.OrderInfoForm;
import be.vdab.toysforboys.repositories.OrderRepository;
import be.vdab.toysforboys.sessions.CheckedOrders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    public OrderInfoForm findById(int id) {
        OrderInfoForm orderInfoForm = new OrderInfoForm();
        repository.findOrderById(id).ifPresent(order -> {
            orderInfoForm.setOrderid(order.getId());
            orderInfoForm.setOrdered(order.getOrderDate());
            orderInfoForm.setRequired(order.getRequiredDate());
            orderInfoForm.setCustomerName(order.getCustomer().getName());
            orderInfoForm.setCustomerStreet(order.getCustomer().getStreetAndNumber());
            orderInfoForm.setCustomerCityState(order.getCustomer().getPostalCode() + " " +
                    order.getCustomer().getCity() + " " + order.getCustomer().getState());
            orderInfoForm.setCustomerCountry(order.getCustomer().getCountry().getName());
            orderInfoForm.setComments(order.getComments());
            orderInfoForm.setTotalValue(BigDecimal.ZERO);
            orderInfoForm.setStatus(order.getStatus());
            orderInfoForm.setShippedDate(order.getShippedDate());
            for (OrderDetail orderDetail: order.getOrderDetails()) {
                OrderDetailForm orderDetailForm = new OrderDetailForm();
                orderDetailForm.setProductname(orderDetail.getProduct().getName());
                orderDetailForm.setPriceEach(orderDetail.getPriceEach());
                orderDetailForm.setQuantity(orderDetail.getQuantityOrdered());
                orderDetailForm.setValue(orderDetail.calculateDetailValue());
                orderDetailForm.setDeliverable(orderDetail.checkProductStock());
                orderInfoForm.addOrderDetailForm(orderDetailForm);
                orderInfoForm.setTotalValue(orderInfoForm.getTotalValue().add(orderDetail.calculateDetailValue()));
            }
        });
        return orderInfoForm;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderFormList findUnshippedOrders(CheckedOrders checkedOrders) {
        OrderFormList orderForms = new OrderFormList();

        for (Order order: repository.findUnshippedOrders()) {
            OrderForm orderForm = new OrderForm();
            orderForm.setOrderId(order.getId());
            orderForm.setOrdered(order.getOrderDate());
            orderForm.setRequired(order.getRequiredDate());
            orderForm.setCustomerName(order.getCustomer().getName());
            orderForm.setComments(order.getComments());
            orderForm.setStatus(order.getStatus());
            orderForm.setShip(checkedOrders.isOrderChecked(order.getId()));
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
    @Transactional
    public List<Order> shipOrders(CheckedOrders checkedOrders) {
        OrderFormList orderForms = findUnshippedOrders(checkedOrders);

        List<Order> failedOrders = new ArrayList<>();

        for (OrderForm orderForm : orderForms.getFormList()) {
            if (orderForm.isShip()){
                if (!setOrderAsShipped(orderForm.getOrderId())){
                    failedOrders.add(repository.findOrderById(orderForm.getOrderId()).get());
                }
            }
        }
        return failedOrders;
    }
}
