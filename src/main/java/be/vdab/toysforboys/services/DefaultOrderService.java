package be.vdab.toysforboys.services;

import be.vdab.toysforboys.domain.Order;
import be.vdab.toysforboys.domain.OrderDetail;
import be.vdab.toysforboys.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public List<Order> findUnshippedOrders() {
        return repository.findUnshippedOrders();
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
}
