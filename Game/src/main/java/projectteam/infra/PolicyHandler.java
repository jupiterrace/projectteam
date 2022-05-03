package projectteam.infra;

import projectteam.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import projectteam.domain.*;
import java.util.Optional;

@Service
public class PolicyHandler{
    @Autowired ReviewRepository reviewRepository;
    @Autowired GameRepository gameRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPurchaseCompleted_CompletePurchase(@Payload PurchaseCompleted purchaseCompleted){

        if(!purchaseCompleted.validate()) return;
        
        ////////////////////////////////////////////////////////////////////
        // 구매 확정 시 -> Room의 status => reserved, lastAction => reserved
        ////////////////////////////////////////////////////////////////////

        System.out.println("##### listener CompletePurchase : " + purchaseCompleted.toJson());

        long gameId = purchaseCompleted.getGameId();

        updateGameStatus(gameId, "purchased", "purchased"); // Status Update
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPurchaseCancelCompleted_CancelPurchase(@Payload PurchaseCancelCompleted purchaseCancelCompleted){

        if(!purchaseCancelCompleted.validate()) return;
        //////////////////////////////////////////////////////////////////////
        // 예약 취소 시 -> Room의 status => available, lastAction => purchasedCancelled
        //////////////////////////////////////////////////////////////////////

        System.out.println("\n\n##### listener CancelPurchase : " + purchaseCancelCompleted.toJson() + "\n\n");

        long gameId = purchaseCancelCompleted.getGameId();

        updateGameStatus(gameId, "available", "purchasedCancelled"); // Status Update
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReviewCreated_UpdateReviewCount(@Payload ReviewCreated reviewCreated){

        if(!reviewCreated.validate()) return;

        ////////////////////////////////////////
        // 리뷰 등록 시 -> Game의 리뷰 카운트 증가
        ////////////////////////////////////////
        System.out.println("##### listener UpdateReviewCount : " + reviewCreated.toJson());

        long gameIdOfReview = reviewCreated.getGameId(); // 등록된 리뷰의 gameID

        updateReviewCount(gameIdOfReview, +1); // 리뷰건수 증가
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReviewDeleted_UpdateReviewCount(@Payload ReviewDeleted reviewDeleted){

        if(!reviewDeleted.validate()) return;
              
        ////////////////////////////////////////
        // 리뷰 삭제 시 -> Game의 리뷰 카운트 감소
        ////////////////////////////////////////
        System.out.println("##### listener UpdateReviewCnt : " + reviewDeleted.toJson());

        long gameIdOfReview = reviewDeleted.getGameId(); // 등록된 리뷰의 gameID

        updateReviewCount(gameIdOfReview, -1); // 리뷰건수 감소
    }

    private void updateReviewCount(long gameId, long num)     {

        //////////////////////////////////////////////
        // gameId 룸 데이터의 ReviewCount를 num 만큼 가감
        //////////////////////////////////////////////

        // Game 테이블에서 gameId의 Data 조회 -> game
        Optional<Game> res = gameRepository.findById(gameId);
        Game game = res.get();

        System.out.println("gameId    : " + game.getGameId());
        System.out.println("reviewCount : " + game.getReviewCount());

        // game 값 수정
        game.setReviewCount(game.getReviewCount() + num); // 리뷰건수 증가/감소
        game.setLastAction("review");  // lastAction 값 셋팅

        System.out.println("Edited reviewCnt : " + game.getReviewCount());
        System.out.println("Edited lastAction : " + game.getLastAction());

        /////////////
        // DB Update
        /////////////
        gameRepository.save(game);
    }

    private void updateGameStatus(long gameId, String status, String lastAction)     {

        //////////////////////////////////////////////
        // gameId 룸 데이터의 Status, lastAction 수정
        //////////////////////////////////////////////

        // Game 테이블에서 gameId의 Data 조회 -> game
        Optional<Game> res = gameRepository.findById(gameId);
        Game game = res.get();

        System.out.println("gameId              : " + game.getGameId());
        System.out.println("Status      : " + game.getStatus());
        System.out.println("lastAction          : " + game.getLastAction());

        // game 값 수정
        game.setStatus(status); // Status 수정 
        game.setLastAction(lastAction);  // lastAction 값 셋팅

        System.out.println("Edited Status     : " + game.getStatus());
        System.out.println("Edited lastAction         : " + game.getLastAction());

        /////////////
        // DB Update
        /////////////
        gameRepository.save(game);
    }
}


