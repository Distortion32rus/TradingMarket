package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class AssortmentPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_of_assortment_plans_id")
    private TypeOfAssortmentPlans typeOfAssortmentPlans;
}
