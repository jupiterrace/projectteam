package projectteam.domain;

import projectteam.domain.PaymentApproved;
import projectteam.domain.PaymentCancelled;
import projectteam.PaymentApplication;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;


@Entity
@Table(name="Payment_table")
public class Payment  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    private Long payId;
    
    
    private Long purchaseId;
    
    
    private Long gameId;
    
    
    private Integer status;

    @PostPersist
    public void onPostPersist(){
        PaymentApproved paymentApproved = new PaymentApproved();
        BeanUtils.copyProperties(this, paymentApproved);
        paymentApproved.publishAfterCommit();

        PaymentCancelled paymentCancelled = new PaymentCancelled();
        BeanUtils.copyProperties(this, paymentCancelled);
        paymentCancelled.publishAfterCommit();

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

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



    public static PaymentRepository repository(){
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(PaymentRepository.class);
        return paymentRepository;
    }


    public static void cancelPayment(CancelRequested cancelRequested){

        Payment payment = new Payment();
        /*
        LOGIC GOES HERE
        */
        repository().save(payment);


    }


}
