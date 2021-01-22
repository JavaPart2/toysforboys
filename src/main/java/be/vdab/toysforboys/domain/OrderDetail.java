package be.vdab.toysforboys.domain;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
@Access(AccessType.FIELD)
public class OrderDetail {
    private int quantityOrdered;
    @NumberFormat(pattern = "0.00")
    private BigDecimal priceEach;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productid")
    private Product product;

    public OrderDetail(int quantityOrdered, BigDecimal priceEach, Product product) {
        this.quantityOrdered = quantityOrdered;
        this.priceEach = priceEach;
    }

    protected OrderDetail() {
    }

    public boolean checkProductStock(){
        return getProduct().checkQuantity(this.quantityOrdered);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetail)) return false;
        OrderDetail that = (OrderDetail) o;
        return quantityOrdered == that.quantityOrdered &&
                Objects.equals(priceEach, that.priceEach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantityOrdered, priceEach);
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getPriceEach() {
        return priceEach;
    }

    public BigDecimal calculateDetailValue(){
        BigDecimal detailValue = BigDecimal.ZERO;

        detailValue = detailValue.add(BigDecimal.valueOf(this.quantityOrdered).multiply(this.priceEach));
        return detailValue;
    }
}
