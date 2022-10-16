package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "counterpartsNomenclature", cascade = CascadeType.ALL)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CounterpartsNomenclature that = (CounterpartsNomenclature) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}