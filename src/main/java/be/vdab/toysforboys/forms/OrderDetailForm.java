package be.vdab.toysforboys.forms;

import be.vdab.toysforboys.domain.Order;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

public class OrderDetailForm {
    private String productname;
    @NumberFormat(pattern = "0.00")
    private BigDecimal priceEach;
    private int quantity;
    @NumberFormat(pattern = "0.00")
    private BigDecimal value;
    private boolean deliverable;

    public OrderDetailForm(String productname, BigDecimal priceEach, int quantity, BigDecimal value, boolean deliverable) {
        this.productname = productname;
        this.priceEach = priceEach;
        this.quantity = quantity;
        this.value = value;
        this.deliverable = deliverable;
    }

    public OrderDetailForm() {
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setPriceEach(BigDecimal priceEach) {
        this.priceEach = priceEach;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setDeliverable(boolean deliverable) {
        this.deliverable = deliverable;
    }

    public String getProductname() {
        return productname;
    }

    public BigDecimal getPriceEach() {
        return priceEach;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getValue() {
        return value;
    }

    public boolean isDeliverable() {
        return deliverable;
    }
}
