package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@SequenceGenerator(name="COUNTERPARTY_NOMENCLATURE", sequenceName="COUNTERPARTY_NOMENCLATURE_GENERATOR")
public class CounterpartsNomenclature {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COUNTERPARTY_NOMENCLATURE")
    private Long id;

    private String name;

    private int multiplicityOf; // Исправить

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nomenclature_id")
    private Nomenclature nomenclature;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;

    @OneToMany(mappedBy = "counterpartsNomenclature", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PriceList> priceLists;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Set<PriceList> getPriceLists() {
        return priceLists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMultiplicityOf() {
        return multiplicityOf;
    }

    public void setMultiplicityOf(int multiplicityOf) {
        this.multiplicityOf = multiplicityOf;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CounterpartsNomenclature that = (CounterpartsNomenclature) o;
        return multiplicityOf == that.multiplicityOf && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(nomenclature, that.nomenclature) && Objects.equals(counterparty, that.counterparty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, multiplicityOf, nomenclature, counterparty);
    }
}
