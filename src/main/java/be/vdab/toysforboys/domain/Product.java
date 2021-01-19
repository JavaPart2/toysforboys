package be.vdab.toysforboys.domain;

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
    private BigDecimal buyPrice;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "productlineId")
    private ProductLine productLine;
    @Version
    private int version;
/*
    @ElementCollection
    @CollectionTable(name = "orderdetails", joinColumns = @JoinColumn(name = "productId"))
    private Set<OrderDetail> orderDetails;
*/

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
