package ru.bspl.pet.tradingmarket.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;

public class PriceListDTO {
    @NotEmpty(message = "Price zone should not be empty.")
    private Long priceZoneId;
    @NotEmpty(message = "Counterparty should not be empty.")
    private Long counterpartyId;

    ArrayList<PricePositionDTO> pricePositions;

    public ArrayList<PricePositionDTO> getPricePositions() {
        return pricePositions;
    }

    public void setPricePositions(ArrayList<PricePositionDTO> pricePositionsDTO) {
        this.pricePositions = pricePositionsDTO;
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

}
