package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class TypeOfAssortmentPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "typeOfAssortmentPlans")
    private List<AssortmentPlan> assortmentPlans;

    public TypeOfAssortmentPlans(String name) {
        this.name = name;
    }

    public TypeOfAssortmentPlans() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
