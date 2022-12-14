package ru.bspl.pet.tradingmarket.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@SequenceGenerator(name="AGREEMENT", sequenceName="AGREEMENT_GENERATOR")
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="AGREEMENT")
    private Long id;

    private int defermentOfPayment, minFirstOrderAmoung, minSecondaryOrderAmoung , defermentRate;

    @ManyToOne
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    public Agreement(int defermentOfPayment, int minFirstOrderAmoung, int minSecondaryOrderAmoung, Counterparty counterparty, Organization organization, int defermentRate) {
        this.defermentOfPayment = defermentOfPayment;
        this.minFirstOrderAmoung = minFirstOrderAmoung;
        this.minSecondaryOrderAmoung = minSecondaryOrderAmoung;
        this.counterparty = counterparty;
        this.organization = organization;
        this.defermentRate = defermentRate;
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

    public int getDefermentRate() {
        return defermentRate;
    }

    public void setDefermentRate(int defermentRate) {
        this.defermentRate = defermentRate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agreement agreement = (Agreement) o;
        return defermentOfPayment == agreement.defermentOfPayment && minFirstOrderAmoung == agreement.minFirstOrderAmoung && minSecondaryOrderAmoung == agreement.minSecondaryOrderAmoung && defermentRate == agreement.defermentRate && Objects.equals(id, agreement.id) && Objects.equals(counterparty, agreement.counterparty) && Objects.equals(organization, agreement.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, defermentOfPayment, minFirstOrderAmoung, minSecondaryOrderAmoung, defermentRate, counterparty, organization);
    }
}
