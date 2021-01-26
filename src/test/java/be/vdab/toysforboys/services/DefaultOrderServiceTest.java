package be.vdab.toysforboys.services;

import be.vdab.toysforboys.domain.OrderStatus;
import be.vdab.toysforboys.forms.OrderFormList;
import be.vdab.toysforboys.forms.OrderInfoForm;
import be.vdab.toysforboys.sessions.CheckedOrders;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(DefaultOrderService.class)
@ComponentScan(value = "be.vdab.toysforboys.repositories",
        resourcePattern = "JpaOrderRepository.class")
public class DefaultOrderServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final EntityManager manager;
    private final OrderService service;

    public DefaultOrderServiceTest(EntityManager manager, OrderService service) {
        this.manager = manager;
        this.service = service;
    }

    @Test
    void shipOrder(){
        assertThat(service.setOrderAsShipped(3)).isTrue();
        assertThat(service.setOrderAsShipped(8)).isFalse();
    }

    @Test
    void shipOrdersWithFailedOrder(){
        CheckedOrders checkedOrders = new CheckedOrders();
        OrderFormList testOrderList = service.findUnshippedOrders(checkedOrders);
        // Set order 8 shipped
        checkedOrders.voegToe(8);
//        testOrderList.getFormList().get(6).setShip(true);
        assertThat(service.shipOrders(checkedOrders).get(0).getId()).isEqualTo(8);
    }

    @Test
    void shipOrdersWithNoFailedOrder(){
        CheckedOrders checkedOrders = new CheckedOrders();
        OrderFormList testOrderList = service.findUnshippedOrders(checkedOrders);
        // Set order 3 shipped
        checkedOrders.voegToe(3);
//        testOrderList.getFormList().get(2).setShip(true);
        service.shipOrders(checkedOrders);
        // Check order data
        assertThat(service.findById(3).getStatus()).isEqualTo(OrderStatus.SHIPPED);
        assertThat(service.findById(3).getShippedDate()).isToday();
    }
}
