package projectteam.domain;

import projectteam.domain.*;
import projectteam.infra.AbstractEvent;


public class PurchaseRequested extends AbstractEvent {

    private Long purchaseId;
    private Long gameId;
    private Integer price;
    private Long customerId;
    private Long payId;

    public PurchaseRequested(){
        super();
    }

    
    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }
    
    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
    
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    // 추가
    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }
}
