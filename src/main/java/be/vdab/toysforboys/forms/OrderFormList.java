package be.vdab.toysforboys.forms;

import java.util.ArrayList;
import java.util.List;

public class OrderFormList {
    private List<OrderForm> formList = new ArrayList<>();

    public OrderFormList(List<OrderForm> formList) {
        this.formList = formList;
    }

    public OrderFormList() {
    }

    public void addOrderForm(OrderForm orderForm){
        this.formList.add(orderForm);
    }

    public List<OrderForm> getFormList() {
        return formList;
    }

    public void setFormList(List<OrderForm> formList) {
        this.formList = formList;
    }
}
