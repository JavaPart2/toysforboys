package be.vdab.toysforboys.services;

import be.vdab.toysforboys.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> findById(int id);
    List<Order> findUnshippedOrders();
    boolean setOrderAsShipped(int id);
}
