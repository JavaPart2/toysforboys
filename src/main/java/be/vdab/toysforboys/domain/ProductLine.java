package be.vdab.toysforboys.domain;

import javax.persistence.*;

@Entity
@Table(name = "productlines")
public class ProductLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @Version
    private int version;

    public ProductLine(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected ProductLine() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getVersion() {
        return version;
    }
}
