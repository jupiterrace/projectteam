package projectteam.infra;

import projectteam.domain.*;
import projectteam.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ViewViewHandler {


    @Autowired
    private ViewRepository viewRepository;

    //////////////////////////////////////////
    // 게임이 등록되었을 때 Insert -> View TABLE
    //////////////////////////////////////////
    @StreamListener(KafkaProcessor.INPUT)
    public void whenGameRegistered_then_CREATE_1 (@Payload GameRegistered roomRegistered) {
        try {

            if (!roomRegistered.validate()) return;

            // view 객체 생성
            View view = new View();
            // view 객체에 이벤트의 Value 를 set 함
            view.setGameId(roomRegistered.getGameId());
            view.setReviewCount(roomRegistered.getReviewCount());
            view.setGameStatus(roomRegistered.getStatus());
            // view 레파지 토리에 save
            viewRepository.save(view);
        
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //////////////////////////////////////////////////
    // 게임이 삭제 되었을 때 Delete -> View TABLE
    //////////////////////////////////////////////////
    @StreamListener(KafkaProcessor.INPUT)
    public void whenGameDeleted_then_DELETE_1(@Payload GameDeleted gameDeleted) {
        try {
            if (!gameDeleted.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            viewRepository.deleteById(gameDeleted.getGameId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //////////////////////////////////////////////////
    // 게임이 출시 되었을 때 View TABLE
    //////////////////////////////////////////////////
    @StreamListener(KafkaProcessor.INPUT)
    public void whenRelesedateComed_then_MODIFY_1(@Payload RelesedateComed relesedateComed) {
        try {
            if (!relesedateComed.validate()) return;
            
            Optional<View> viewOptional = viewRepository.findById(relesedateComed.getGameId());
            if( viewOptional.isPresent()) {
                View view = viewOptional.get();
                view.setGameStatus("relesedateComed");
                viewRepository.save(view);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPurchaseCompleted_CompletePurchase(@Payload PurchaseCompleted purchaseCompleted){
        try {
            if (!purchaseCompleted.validate()) return;
            
            Optional<View> viewOptional = viewRepository.findById(purchaseCompleted.getGameId());
            if( viewOptional.isPresent()) {
                View view = viewOptional.get();
                view.setPurchaseCount(view.getPurchaseCount()+1);
                view.setPurchaseStatus("purchased");
                viewRepository.save(view);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPurchaseCancelCompleted_CancelPurchase(@Payload PurchaseCancelCompleted purchaseCancelCompleted){
        try {
            if (!purchaseCancelCompleted.validate()) return;
            
            Optional<View> viewOptional = viewRepository.findById(purchaseCancelCompleted.getGameId());
            if( viewOptional.isPresent()) {
                View view = viewOptional.get();
                view.setPurchaseCount(view.getPurchaseCount()-1);
                view.setPurchaseStatus("purchasedCancelled");
                viewRepository.save(view);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
}

