package be.vdab.toysforboys.domain;

import javax.persistence.*;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Version
    private int version;

    public Country(String name) {
        this.name = name;
    }

    protected Country() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVersion() {
        return version;
    }
}
