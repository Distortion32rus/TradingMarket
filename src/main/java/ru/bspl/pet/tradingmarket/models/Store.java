package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@SequenceGenerator(name="STORE", sequenceName="STORE_GENERATOR")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="STORE")
    private Long id;

    private String name;
    private String description;
    private int priority;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_zone_id")
    private PriceZone priceZone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_unit_id")
    private BusinessUnit businessUnit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "thresholdValues_id")
    private ThresholdValues thresholdValues;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public PriceZone getPriceZone() {
        return priceZone;
    }

    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }

    public void setPriceZone(PriceZone priceZone) {
        this.priceZone = priceZone;
    }

    public ThresholdValues getThresholdValues() {
        return thresholdValues;
    }

    public void setThresholdValues(ThresholdValues thresholdValues) {
        this.thresholdValues = thresholdValues;
    }

    public Store(String name, String description, int priority, PriceZone priceZone, BusinessUnit businessUnit, Organization organization, ThresholdValues thresholdValues) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.priceZone = priceZone;
        this.businessUnit = businessUnit;
        this.organization = organization;
        this.thresholdValues = thresholdValues;
    }

    public Store() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return priority == store.priority && Objects.equals(id, store.id) && Objects.equals(name, store.name) && Objects.equals(description, store.description) && Objects.equals(priceZone, store.priceZone) && Objects.equals(businessUnit, store.businessUnit) && Objects.equals(organization, store.organization) && Objects.equals(thresholdValues, store.thresholdValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, priority, priceZone, businessUnit, organization, thresholdValues);
    }
}
