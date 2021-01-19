package be.vdab.toysforboys.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

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
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Version
    private int version;
    @ElementCollection
    @CollectionTable(name = "orderdetails", joinColumns = @JoinColumn(name = "orderId"))
    @OrderBy("product")
    private Set<OrderDetail> orderDetails;

    public Order(LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate, String comments,
                 Customer customer, OrderStatus status) {
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.comments = comments;
        this.customer = customer;
        this.status = status;
        this.orderDetails = new LinkedHashSet<>();
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

    public Set<OrderDetail> getOrderDetails() {
        return Collections.unmodifiableSet(orderDetails);
    }
}
