package ru.bspl.pet.tradingmarket.dto;

import ru.bspl.pet.tradingmarket.models.CounterpartsNomenclature;
import ru.bspl.pet.tradingmarket.models.Counterparty;
import ru.bspl.pet.tradingmarket.models.PriceZone;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

public class PriceListDTO {

    @NotEmpty(message = "Code should not be empty.")
    private Long counterpartsNomenclatureId;
    @NotEmpty(message = "Price zone should not be empty.")
    private Long priceZoneId;
    @NotEmpty(message = "Counterparty should not be empty.")
    private Long counterpartyId;
    @NotEmpty(message = "Price should not be empty.")
    private double counterpartysPrice;
    @NotEmpty(message = "Date of expiration should not be empty.")
    private Date shelfLife;
    @NotEmpty(message = "Multiplicity should not be empty.")
    @Size(min = 1, message = "Multiplicity should be more than 0")
    private int multiplicityOf;
    @Size(min = 1, message = "Stock should be more than 0")
    @NotEmpty(message = "Stock should not be empty.")
    private int counterpartysStock;


    public Long getCounterpartsNomenclatureId() {
        return counterpartsNomenclatureId;
    }

    public void setCounterpartsNomenclatureId(Long counterpartsNomenclatureId) {
        this.counterpartsNomenclatureId = counterpartsNomenclatureId;
    }

    public Long getPriceZoneId() {
        return priceZoneId;
    }

    public void setPriceZoneId(Long priceZoneId) {
        this.priceZoneId = priceZoneId;
    }

    public Long getCounterpartyId() {
        return counterpartyId;
    }

    public void setCounterpartyId(Long counterpartyId) {
        this.counterpartyId = counterpartyId;
    }

    public double getCounterpartysPrice() {
        return counterpartysPrice;
    }

    public void setCounterpartysPrice(double counterpartysPrice) {
        this.counterpartysPrice = counterpartysPrice;
    }

    public Date getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Date shelfLife) {
        this.shelfLife = shelfLife;
    }

    public int getMultiplicityOf() {
        return multiplicityOf;
    }

    public void setMultiplicityOf(int multiplicityOf) {
        this.multiplicityOf = multiplicityOf;
    }

    public int getCounterpartysStock() {
        return counterpartysStock;
    }

    public void setCounterpartysStock(int counterpartysStock) {
        this.counterpartysStock = counterpartysStock;
    }


}
