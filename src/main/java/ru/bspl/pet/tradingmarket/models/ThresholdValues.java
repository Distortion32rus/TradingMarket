package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class ThresholdValues {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "thresholdValues", cascade = CascadeType.ALL)
    private List<ThresholdValuesTable> thresholdValuesTableList;

    public ThresholdValues(String name) {
        this.name = name;
    }
    public ThresholdValues() {
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
}
