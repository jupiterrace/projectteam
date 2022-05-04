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
        System.out.println("\n\n##### listener SendPurchaseConfirmMsg : " + purchaseCompleted.toJson() + "\n\n");

        long gameId = purchaseCompleted.getGameId();
        long purchaseId = purchaseCompleted.getPurchaseId();
        long customerId = purchaseCompleted.getCustomerId();
        String msgString = customerId + "화원님이 요청하신 [" + gameId +"] 를 구매하였습니다. (구매번호: " + purchaseId + ").";

        // 메시지 전송
        sendMsg(gameId, msgString);

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPurchaseCancelCompleted_SendPurchaseCancelMsg(@Payload PurchaseCancelCompleted purchaseCancelCompleted){

        if(!purchaseCancelCompleted.validate()) return;

        System.out.println("\n\n##### listener SendPurchaseCancelMsg : " + purchaseCancelCompleted.toJson() + "\n\n");

        long gameId = purchaseCancelCompleted.getGameId();
        long purchaseId = purchaseCancelCompleted.getPurchaseId();
        long customerId = purchaseCancelCompleted.getCustomerId();
        String msgString = customerId + "화원님이 요청하신 [" + gameId +"] 를 구매취소 하였습니다. (구매번호: " + purchaseId + ").";

        // 메시지 전송
        sendMsg(gameId, msgString);
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

        System.out.println("\n\n##### listener SendReleaseDate : " + relesedateComed.toJson() + "\n\n");

        // gameId 추출
        long gameId = relesedateComed.getGameId(); // 취소된 gameId
        String msgString = "[" + gameId +"] 가 출시되었습니다.";

        System.out.println("\n\n##### listener SendReleaseDate : " + gameId + " " + msgString + "\n\n");

        // 메시지 전송
        sendMsg(gameId, msgString);
    }

    private void sendMsg(long gameId, String msgString) {

        //////////////////////////////////////////////
        // gameId 룸에 대해 msgString으로 SMS를 쌓는다
        //////////////////////////////////////////////
        Message msg = new Message();
        msg.setGameId(gameId);
        msg.setContent(msgString);

        // DB Insert
        messageRepository.save(msg);
    }

}


