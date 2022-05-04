package projectteam.domain;

import projectteam.domain.*;
import projectteam.infra.AbstractEvent;


public class PaymentApproved extends AbstractEvent {

    private Long payId;
    private Long purchaseId;
    private String status;
    private Long gameId;

    public PaymentApproved(){
        super();
    }

    
    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }
    
    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
