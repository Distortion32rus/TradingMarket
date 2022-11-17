package ru.bspl.pet.tradingmarket.dto;

import java.util.List;

public class DemandDTO {

    private Long storeId;

    private List<DemandPositionDTO> demandPositions;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public List<DemandPositionDTO> getDemandPositions() {
        return demandPositions;
    }

    public void setDemandPositions(List<DemandPositionDTO> demandPositions) {
        this.demandPositions = demandPositions;
    }
}
