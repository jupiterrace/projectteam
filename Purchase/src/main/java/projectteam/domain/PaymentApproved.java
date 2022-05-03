package projectteam.domain;

import projectteam.domain.*;
import projectteam.infra.AbstractEvent;


public class PaymentApproved extends AbstractEvent {

    private Long payId;
    private Long purchaseId;
    private Integer status;

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
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
