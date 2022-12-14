package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeOfAssortmentPlans that = (TypeOfAssortmentPlans) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
