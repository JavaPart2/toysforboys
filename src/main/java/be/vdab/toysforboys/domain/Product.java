package be.vdab.toysforboys.domain;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String scale;
    private String description;
    private int quantityInStock;
    private int quantityInOrder;
    @NumberFormat(pattern = "0.00")
    private BigDecimal buyPrice;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "productlineId")
    private ProductLine productLine;
    @Version
    private int version;

    public Product(String name, String scale, String description, int quantityInStock,
                   int quantityInOrder, BigDecimal buyPrice, ProductLine productLine) {
        this.name = name;
        this.scale = scale;
        this.description = description;
        this.quantityInStock = quantityInStock;
        this.quantityInOrder = quantityInOrder;
        this.buyPrice = buyPrice;
        this.productLine = productLine;
    }

    protected Product() {
    }

    public boolean checkQuantity(int quantityInOrder){
        if ((quantityInOrder > this.quantityInOrder) ||
                (quantityInOrder > this.quantityInStock)){
            return false;
        }
        return true;
    }

    public void updateQuantity(int quantityOrder){
        this.quantityInStock = this.quantityInStock - quantityOrder;
        this.quantityInOrder = this.quantityInOrder - quantityOrder;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScale() {
        return scale;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public int getQuantityInOrder() {
        return quantityInOrder;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public ProductLine getProductLine() {
        return productLine;
    }

    public int getVersion() {
        return version;
    }
}
