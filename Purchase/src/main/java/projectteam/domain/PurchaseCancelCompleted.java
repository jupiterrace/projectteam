package projectteam.domain;

import projectteam.domain.*;
import projectteam.infra.AbstractEvent;


public class PurchaseCancelCompleted extends AbstractEvent {

    private Long purchaseId;
    private Long gameId;
    private Long customerId;

    public PurchaseCancelCompleted(){
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
    
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
