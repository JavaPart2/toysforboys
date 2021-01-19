package be.vdab.toysforboys.repositories;

import be.vdab.toysforboys.domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaOrderRepository implements OrderRepository{
    private final EntityManager manager;

    public JpaOrderRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Optional<Order> findOrderById(int id) {
        return Optional.ofNullable(manager.find(Order.class, id, LockModeType.OPTIMISTIC));
    }

    @Override
    public List<Order> findUnshippedOrders() {
        return manager.createNamedQuery("Order.findAll", Order.class)
                .getResultList();
    }
}
