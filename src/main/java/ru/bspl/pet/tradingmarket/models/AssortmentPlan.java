package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@SequenceGenerator(name="ASSORTMENT_PLAN", sequenceName="ASSORTMENT_PLAN_GENERATOR")
public class AssortmentPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ASSORTMENT_PLAN")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_of_assortment_plans_id")
    private TypeOfAssortmentPlans typeOfAssortmentPlans;

    @OneToMany(mappedBy = "assortmentPlan", cascade = CascadeType.ALL)
    private List<Nomenclature> nomenclatures;

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

    public TypeOfAssortmentPlans getTypeOfAssortmentPlans() {
        return typeOfAssortmentPlans;
    }

    public void setTypeOfAssortmentPlans(TypeOfAssortmentPlans typeOfAssortimentPlans) {
        this.typeOfAssortmentPlans = typeOfAssortimentPlans;
    }

    public AssortmentPlan(String name, TypeOfAssortmentPlans typeOfAssortimentPlans) {
        this.name = name;
        this.typeOfAssortmentPlans = typeOfAssortimentPlans;
    }
    public AssortmentPlan() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssortmentPlan that = (AssortmentPlan) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(typeOfAssortmentPlans, that.typeOfAssortmentPlans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, typeOfAssortmentPlans);
    }
}
