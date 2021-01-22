package be.vdab.toysforboys.services;

import be.vdab.toysforboys.domain.Order;
import be.vdab.toysforboys.forms.OrderForm;
import be.vdab.toysforboys.forms.OrderFormList;
import be.vdab.toysforboys.forms.OrderInfoForm;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OrderService {
    OrderInfoForm findById(int id);
    OrderFormList findUnshippedOrders();
    boolean setOrderAsShipped(int id);
    List<Order> shipOrders(OrderFormList orderFormList);
}
