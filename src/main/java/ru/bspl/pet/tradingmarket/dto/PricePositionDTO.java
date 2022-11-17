package ru.bspl.pet.tradingmarket.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

public class PricePositionDTO {

    @NotEmpty(message = "Code should not be empty.")
    private Long counterpartsNomenclatureId;
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
