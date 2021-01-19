package be.vdab.toysforboys.repositories;

import be.vdab.toysforboys.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findOrderById(int id);
    List<Order> findUnshippedOrders();
}
