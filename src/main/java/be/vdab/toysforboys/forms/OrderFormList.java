package be.vdab.toysforboys.forms;

import java.util.ArrayList;
import java.util.List;

public class OrderFormList {
    private List<OrderForm> orderFormList = new ArrayList<>();

    public OrderFormList(List<OrderForm> orderFormList) {
        this.orderFormList = orderFormList;
    }

    public OrderFormList() {
    }

    public void addOrderForm(OrderForm orderForm){
        this.orderFormList.add(orderForm);
    }

    public List<OrderForm> getOrderFormList() {
        return orderFormList;
    }

    public void setOrderFormList(List<OrderForm> orderFormList) {
        this.orderFormList = orderFormList;
    }
}
