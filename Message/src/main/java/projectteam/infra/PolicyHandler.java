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
    @Autowired MessageRepository messageRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPurchaseCompleted_SendPurchaseConfirmMsg(@Payload PurchaseCompleted purchaseCompleted){

        if(!purchaseCompleted.validate()) return;
        PurchaseCompleted event = purchaseCompleted;
        System.out.println("\n\n##### listener SendPurchaseConfirmMsg : " + purchaseCompleted.toJson() + "\n\n");


        

        // Sample Logic //
        Message.sendPurchaseConfirmMsg(event);
        

        

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPurchaseCancelCompleted_SendPurchaseCancelMsg(@Payload PurchaseCancelCompleted purchaseCancelCompleted){

        if(!purchaseCancelCompleted.validate()) return;
        PurchaseCancelCompleted event = purchaseCancelCompleted;
        System.out.println("\n\n##### listener SendPurchaseCancelMsg : " + purchaseCancelCompleted.toJson() + "\n\n");


        

        // Sample Logic //
        Message.sendPurchaseCancelMsg(event);
        

        

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDownloadStarted_SendDownloadStart(@Payload DownloadStarted downloadStarted){

        if(!downloadStarted.validate()) return;
        DownloadStarted event = downloadStarted;
        System.out.println("\n\n##### listener SendDownloadStart : " + downloadStarted.toJson() + "\n\n");


        

        // Sample Logic //
        Message.sendDownloadStart(event);
        

        

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDownloadCancelled_SendDownloadCancel(@Payload DownloadCancelled downloadCancelled){

        if(!downloadCancelled.validate()) return;
        DownloadCancelled event = downloadCancelled;
        System.out.println("\n\n##### listener SendDownloadCancel : " + downloadCancelled.toJson() + "\n\n");


        

        // Sample Logic //
        Message.sendDownloadCancel(event);
        

        

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDownloadCompleted_SendDownloadComplete(@Payload DownloadCompleted downloadCompleted){

        if(!downloadCompleted.validate()) return;
        DownloadCompleted event = downloadCompleted;
        System.out.println("\n\n##### listener SendDownloadComplete : " + downloadCompleted.toJson() + "\n\n");


        

        // Sample Logic //
        Message.sendDownloadComplete(event);
        

        

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRelesedateComed_SendReleaseDate(@Payload RelesedateComed relesedateComed){

        if(!relesedateComed.validate()) return;
        RelesedateComed event = relesedateComed;
        System.out.println("\n\n##### listener SendReleaseDate : " + relesedateComed.toJson() + "\n\n");


        

        // Sample Logic //
        Message.sendReleaseDate(event);
        

        

    }


}


