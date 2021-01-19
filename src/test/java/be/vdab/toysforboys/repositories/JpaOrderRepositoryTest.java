package be.vdab.toysforboys.repositories;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(JpaOrderRepository.class)
public class JpaOrderRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JpaOrderRepository repository;
    private final EntityManager manager;

    public JpaOrderRepositoryTest(JpaOrderRepository repository, EntityManager manager) {
        this.repository = repository;
        this.manager = manager;
    }

    @Test
    void findOrders(){
        assertThat(repository.findUnshippedOrders()).hasSize(226);
        repository.findUnshippedOrders().stream().forEach(order -> {
            System.out.println("orderid: " + order.getId());
            System.out.println("orderstatus: " + order.getStatus());
            System.out.print("customerid: " + order.getCustomer().getId());
            System.out.println(" ; customername: " + order.getCustomer().getName());
        });
    }

    @Test
    void findOrderById(){
        var tstOrder = repository.findOrderById(21).get();
        assertThat(tstOrder.getId()).isEqualTo(21);
        System.out.println("customer vn order 21: " + tstOrder.getCustomer().getName());
        tstOrder.getOrderDetails().stream().forEach(orderDetail -> {
            System.out.println("orderdetail quantity: " + orderDetail.getQuantityOrdered());
            System.out.println("orderdetail productname: " + orderDetail.getProduct().getName());
        });
    }
}
