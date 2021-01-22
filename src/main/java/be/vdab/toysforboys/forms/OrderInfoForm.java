package be.vdab.toysforboys.forms;

import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderInfoForm {
    private int orderid;
    private LocalDate ordered;
    private LocalDate required;
    private String customerName;
    private String customerStreet;
    private String customerCityState;
    private String customerCountry;
    private String comments;
    private List<OrderDetailForm> formList = new ArrayList<>();
    @NumberFormat(pattern = "0.00")
    private BigDecimal totalValue;

    public OrderInfoForm(int orderid, LocalDate ordered, LocalDate required, String customerName,
                         String customerStreet, String customerCityState, String customerCountry,
                         String comments, BigDecimal totalValue) {
        this.orderid = orderid;
        this.ordered = ordered;
        this.required = required;
        this.customerName = customerName;
        this.customerStreet = customerStreet;
        this.customerCityState = customerCityState;
        this.customerCountry = customerCountry;
        this.comments = comments;
        this.formList = new ArrayList<>();
        this.totalValue = totalValue;
    }

    public OrderInfoForm() {
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
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

    public String getCustomerStreet() {
        return customerStreet;
    }

    public void setCustomerStreet(String customerStreet) {
        this.customerStreet = customerStreet;
    }

    public String getCustomerCityState() {
        return customerCityState;
    }

    public void setCustomerCityState(String customerCityState) {
        this.customerCityState = customerCityState;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<OrderDetailForm> getFormList() {
        return formList;
    }

    public void addOrderDetailForm(OrderDetailForm orderDetailForm){
        this.formList.add(orderDetailForm);
    }
}
