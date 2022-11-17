package ru.bspl.pet.tradingmarket.dto;

public class DemandPositionDTO {

    private Long assortmentPlanId;

    private int demandQNT, stockQNT;

    private double salesSpeed;

    public Long getAssortmentPlanId() {
        return assortmentPlanId;
    }

    public void setAssortmentPlanId(Long assortmentPlanId) {
        this.assortmentPlanId = assortmentPlanId;
    }

    public int getDemandQNT() {
        return demandQNT;
    }

    public void setDemandQNT(int demandQNT) {
        this.demandQNT = demandQNT;
    }

    public int getStockQNT() {
        return stockQNT;
    }

    public void setStockQNT(int stockQNT) {
        this.stockQNT = stockQNT;
    }

    public double getSalesSpeed() {
        return salesSpeed;
    }

    public void setSalesSpeed(double salesSpeed) {
        this.salesSpeed = salesSpeed;
    }
}
