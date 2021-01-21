package be.vdab.toysforboys.forms;

import be.vdab.toysforboys.domain.Order;

public class OrderForm {
    private Order order;
    private boolean ship;

    public OrderForm(Order order, boolean ship) {
        this.order = order;
        this.ship = ship;
    }

    protected OrderForm() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isShip() {
        return ship;
    }

    public void setShip(boolean ship) {
        this.ship = ship;
    }
}
