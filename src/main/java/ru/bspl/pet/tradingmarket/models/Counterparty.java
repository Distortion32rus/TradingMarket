package ru.bspl.pet.tradingmarket.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@SequenceGenerator(name="COUNTERPARTY", sequenceName="COUNTERPARTY_GENERATOR")
public class Counterparty {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COUNTERPARTY")
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "counterparty", cascade = CascadeType.ALL)
    private List<Agreement> agreements;


    @OneToMany(mappedBy = "counterparty", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PriceList> priceLists;


    public Set<PriceList> getPriceLists() {
        return priceLists;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Counterparty(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Counterparty() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Counterparty that = (Counterparty) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
