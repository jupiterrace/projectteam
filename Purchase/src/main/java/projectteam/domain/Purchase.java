package projectteam.domain;

import projectteam.domain.PurchaseCancelCompleted;
import projectteam.domain.PurchaseRequested;
import projectteam.domain.CancelRequested;
import projectteam.domain.PurchaseCompleted;
import projectteam.PurchaseApplication;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;


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

    @PostPersist
    public void onPostPersist(){
        PurchaseCancelCompleted purchaseCancelCompleted = new PurchaseCancelCompleted();
        BeanUtils.copyProperties(this, purchaseCancelCompleted);
        purchaseCancelCompleted.publishAfterCommit();

        PurchaseRequested purchaseRequested = new PurchaseRequested();
        BeanUtils.copyProperties(this, purchaseRequested);
        purchaseRequested.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        projectteam.external.Payment payment = new projectteam.external.Payment();
        // mappings goes here
        PurchaseApplication.applicationContext.getBean(projectteam.external.PaymentService.class)
            .appovePayment(payment);

        CancelRequested cancelRequested = new CancelRequested();
        BeanUtils.copyProperties(this, cancelRequested);
        cancelRequested.publishAfterCommit();

    }
    @PreRemove
    public void onPreRemove(){
        PurchaseCompleted purchaseCompleted = new PurchaseCompleted();
        BeanUtils.copyProperties(this, purchaseCompleted);
        purchaseCompleted.publishAfterCommit();

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
