package ru.bspl.pet.tradingmarket.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.util.List;

@Entity
@SequenceGenerator(name="THRESHOLD_VALUES", sequenceName="THRESHOLD_VALUES_GENERATOR")
public class ThresholdValues {



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="THRESHOLD_VALUES")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "thresholdValues", cascade = CascadeType.ALL)
    private List<ThresholdValuesTable> thresholdValuesTableList;

    @OneToMany(mappedBy = "thresholdValues", cascade = CascadeType.ALL)
    private List<Store> stores;
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
