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


@Service
public class PolicyHandler{
    @Autowired PurchaseRepository purchaseRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCancelled_ConfirmCancel(@Payload PaymentCancelled paymentCancelled){

        if(!paymentCancelled.validate()) return;
        PaymentCancelled event = paymentCancelled;
        System.out.println("\n\n##### listener ConfirmCancel : " + paymentCancelled.toJson() + "\n\n");


        

        // Sample Logic //
        Purchase.confirmCancel(event);
        

        

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_ConfirmPurchased(@Payload PaymentApproved paymentApproved){

        if(!paymentApproved.validate()) return;
        PaymentApproved event = paymentApproved;
        System.out.println("\n\n##### listener ConfirmPurchased : " + paymentApproved.toJson() + "\n\n");


        

        // Sample Logic //
        Purchase.confirmPurchased(event);
        

        

    }


}


