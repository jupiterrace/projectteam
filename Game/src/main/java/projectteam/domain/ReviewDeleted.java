package projectteam.domain;

import projectteam.domain.*;
import projectteam.infra.AbstractEvent;


public class ReviewDeleted extends AbstractEvent {

    private Long reviewId;
    private Long gameId;
    private Long customerId;
    private String mainText;

    public ReviewDeleted(){
        super();
    }

    
    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }
    
    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
    
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }
}
