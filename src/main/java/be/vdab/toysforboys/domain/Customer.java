package be.vdab.toysforboys.domain;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String streetAndNumber;
    private String city;
    private String state;
    private String postalCode;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId")
    private Country country;
    @Version
    private int version;

    public Customer(String name, String streetAndNumber, String city, String state, String postalCode, Country country) {
        this.name = name;
        this.streetAndNumber = streetAndNumber;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    protected Customer() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        if (this.state == null){
            return " ";
        } else {
            return state;
        }
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Country getCountry() {
        return country;
    }

    public int getVersion() {
        return version;
    }
}
