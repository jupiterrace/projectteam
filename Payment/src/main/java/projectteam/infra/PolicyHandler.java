package projectteam.infra;

import javax.naming.NameParser;

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
    @Autowired PaymentRepository paymentRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCancelRequested_CancelPayment(@Payload CancelRequested cancelRequested){

        if(!cancelRequested.validate()) return;
        CancelRequested event = cancelRequested;
        System.out.println("\n\n##### listener CancelPayment : " + cancelRequested.toJson() + "\n\n");


        

        // Sample Logic //
        Payment.cancelPayment(event);
        

        

    }


}


