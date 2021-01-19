package be.vdab.toysforboys.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDate shippedDate;
    private String comments;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;
    private OrderStatus status;
    @Version
    private int version;

    public Order(LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate, String comments,
                 Customer customer, OrderStatus status) {
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.comments = comments;
        this.customer = customer;
        this.status = status;
    }

    protected Order() {
    }

    public int getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public LocalDate getShippedDate() {
        return shippedDate;
    }

    public String getComments() {
        return comments;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public int getVersion() {
        return version;
    }
}
