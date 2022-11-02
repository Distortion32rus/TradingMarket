package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.List;

@Entity
@SequenceGenerator(name="NOMENCLATURE", sequenceName="NOMENCLATURE_GENERATOR")
public class Nomenclature {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="NOMENCLATURE")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assortment_Plan_id")
    private AssortmentPlan assortmentPlan;

    @OneToMany(mappedBy = "nomenclature")
    private List<CounterpartsNomenclature> counterpartsNomenclatures;

    public Nomenclature(String name, AssortmentPlan assortmentPlan) {
        this.name = name;
        this.assortmentPlan = assortmentPlan;
    }

    public Nomenclature() {
    }

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

    public AssortmentPlan getAssortmentPlan() {
        return assortmentPlan;
    }

    public void setAssortmentPlan(AssortmentPlan assortmentPlan) {
        this.assortmentPlan = assortmentPlan;
    }



}
