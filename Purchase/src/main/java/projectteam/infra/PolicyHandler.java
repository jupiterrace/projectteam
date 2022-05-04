package projectteam.infra;

import javax.naming.NameParser;

import projectteam.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import projectteam.domain.*;
import java.util.Optional;


@Service
public class PolicyHandler{
    @Autowired PurchaseRepository purchaseRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCancelled_ConfirmCancel(@Payload PaymentCancelled paymentCancelled){

        if(!paymentCancelled.validate()) return;

        long purchaseId = paymentCancelled.getPurchaseId(); // 결제 완료된 purchaseId
        long payId = paymentCancelled.getPayId(); // 결제된 payId -> 나중에 취소할때 쓰임

        updatePurchaseStatus(purchaseId, "purchasedCancelled", payId ); // Status Update
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_ConfirmPurchased(@Payload PaymentApproved paymentApproved){

        if(!paymentApproved.validate()) return;
        
        long purchaseId = paymentApproved.getPurchaseId(); // 결제 완료된 rsvpurchaseIdId
        long payId = paymentApproved.getPayId(); // 결제된 payId -> 나중에 취소할때 쓰임

        updatePurchaseStatus(purchaseId, "purchased", payId); // Status Update
    }

    private void updatePurchaseStatus(long rsvId, String status, long payId)     {

        //////////////////////////////////////////////
        // roomId 룸 데이터의 status, lastAction 수정
        //////////////////////////////////////////////

        // Room 테이블에서 roomId의 Data 조회 -> room
        Optional<Purchase> res = purchaseRepository.findById(rsvId);
        Purchase purchase = res.get();

        // room 값 수정
        purchase.setStatus(status); // status 수정 
        purchase.setPayId(payId); // payId 수정

        System.out.println("Edited status     : " + purchase.getStatus());
        System.out.println("Edited payId     : " + purchase.getPayId());

        /////////////
        // DB Update
        /////////////
        purchaseRepository.save(purchase);

    }

}