package projectteam.domain;

import projectteam.domain.PurchaseCancelCompleted;
import projectteam.domain.PurchaseRequested;
import projectteam.domain.CancelRequested;
import projectteam.domain.PurchaseCompleted;
import projectteam.PurchaseApplication;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;


@Entity
@Table(name="Purchase_table")
public class Purchase  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long purcahseId;
    private Long gameId;
    private Integer price;
    private Long customerId;
    private String status;
    private Long payId;

    @PostPersist
    public void onPostPersist(){
        projectteam.external.Payment payment = new projectteam.external.Payment();
        payment.setPurchaseId(this.getPurcahseId());
        payment.setGameId(this.getGameId());
        payment.setStatus("paid");
        PurchaseApplication.applicationContext.getBean(projectteam.external.PaymentService.class)
            .appovePayment(payment);
        
        /*
        PurchaseRequested purchaseRequested = new PurchaseRequested();
        BeanUtils.copyProperties(this, purchaseRequested);
        purchaseRequested.publishAfterCommit();
        */
    }

    @PostUpdate
    public void onPostUpdate(){
        if(this.getStatus().equals("reqCancel")) {
            CancelRequested cancelRequested = new CancelRequested();
            BeanUtils.copyProperties(this, cancelRequested);
            cancelRequested.publishAfterCommit();
        }
                
        if(this.getStatus().equals("purchased")) {
            PurchaseCompleted purchaseCompleted = new PurchaseCompleted();
            BeanUtils.copyProperties(this, purchaseCompleted);
            purchaseCompleted.publishAfterCommit(); 
        }

        if(this.getStatus().equals("purchasedCancelled")) {
            PurchaseCancelCompleted purchaseCancelCompleted = new PurchaseCancelCompleted();
            BeanUtils.copyProperties(this, purchaseCancelCompleted);
            purchaseCancelCompleted.publishAfterCommit();
        }
    }

    public Long getPurcahseId() {
        return purcahseId;
    }

    public void setPurcahseId(Long purcahseId) {
        this.purcahseId = purcahseId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // 추가
    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }



    public static PurchaseRepository repository(){
        PurchaseRepository purchaseRepository = PurchaseApplication.applicationContext.getBean(PurchaseRepository.class);
        return purchaseRepository;
    }


    public static void confirmPurchased(PaymentApproved paymentApproved){

        Purchase purchase = new Purchase();
        /*
        LOGIC GOES HERE
        */
        repository().save(purchase);


    }

    public static void confirmCancel(PaymentCancelled paymentCancelled){

        Purchase purchase = new Purchase();
        /*
        LOGIC GOES HERE
        */
        repository().save(purchase);


    }

}
