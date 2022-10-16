package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    public Store(String name, String description, int priority, PriceZone priceZone, BusinessUnit businessUnit, Organization organization) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.priceZone = priceZone;
        this.businessUnit = businessUnit;
        this.organization = organization;
    }

    public Store() {
    }

}
