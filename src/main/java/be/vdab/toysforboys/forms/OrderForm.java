package be.vdab.toysforboys.forms;

import be.vdab.toysforboys.domain.Order;
import be.vdab.toysforboys.domain.OrderStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class OrderForm {
    private int orderId;
    @DateTimeFormat(style = "S-")
    private LocalDate ordered;
    @DateTimeFormat(style = "S-")
    private LocalDate required;
    private String customerName;
    private String comments;
    private OrderStatus status;
    private boolean ship;

    public OrderForm() {
    }

    public OrderForm(int orderId, LocalDate ordered, LocalDate required, String customerName, String comments,
                     OrderStatus status, boolean ship) {
        this.orderId = orderId;
        this.ordered = ordered;
        this.required = required;
        this.customerName = customerName;
        this.comments = comments;
        this.status = status;
        this.ship = ship;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrdered() {
        return ordered;
    }

    public void setOrdered(LocalDate ordered) {
        this.ordered = ordered;
    }

    public LocalDate getRequired() {
        return required;
    }

    public void setRequired(LocalDate required) {
        this.required = required;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public boolean isShip() {
        return ship;
    }

    public void setShip(boolean ship) {
        this.ship = ship;
    }
}
