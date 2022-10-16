package ru.bspl.pet.tradingmarket.models;



import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@SequenceGenerator(name="PRICE_ZONE", sequenceName="PRICE_ZONE_GENERATOR")
public class PriceZone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PRICE_ZONE")

    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "priceZone", cascade = CascadeType.ALL)
    private List<Store> stores;

    @OneToMany(mappedBy = "priceZone", cascade = CascadeType.ALL)
    private List<PriceList> priceLists;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PriceZone(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public PriceZone(){
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceZone priceZone = (PriceZone) o;
        return Objects.equals(id, priceZone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
