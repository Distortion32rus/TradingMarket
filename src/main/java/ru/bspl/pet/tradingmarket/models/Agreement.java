package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;

@Entity
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int defermentOfPayment, minFirstOrderAmoung, minSecondaryOrderAmoung;

    @ManyToOne
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    public Agreement(int defermentOfPayment, int minFirstOrderAmoung, int minSecondaryOrderAmoung, Counterparty counterparty, Organization organization) {
        this.defermentOfPayment = defermentOfPayment;
        this.minFirstOrderAmoung = minFirstOrderAmoung;
        this.minSecondaryOrderAmoung = minSecondaryOrderAmoung;
        this.counterparty = counterparty;
        this.organization = organization;
    }
    public Agreement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDefermentOfPayment() {
        return defermentOfPayment;
    }

    public void setDefermentOfPayment(int defermentOfPayment) {
        this.defermentOfPayment = defermentOfPayment;
    }

    public int getMinFirstOrderAmoung() {
        return minFirstOrderAmoung;
    }

    public void setMinFirstOrderAmoung(int minFirstOrderAmoung) {
        this.minFirstOrderAmoung = minFirstOrderAmoung;
    }

    public int getMinSecondaryOrderAmoung() {
        return minSecondaryOrderAmoung;
    }

    public void setMinSecondaryOrderAmoung(int minSecondaryOrderAmoung) {
        this.minSecondaryOrderAmoung = minSecondaryOrderAmoung;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
    }



}
