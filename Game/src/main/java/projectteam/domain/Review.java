package projectteam.domain;

import projectteam.GameApplication;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;


@Entity
@Table(name="Review_table")
public class Review  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    private Long reviewId;
    
    
    private Long gameId;
    
    
    private Long customerId;
    
    
    private String mainText;

    @PostPersist
    public void onPostPersist(){
        ///////////////////////////////
        // Review 테이블 Insert 후 수행
        ///////////////////////////////

        // ReviewCreated Event 발생
        ReviewCreated reviewCreated = new ReviewCreated();
        BeanUtils.copyProperties(this, reviewCreated);
        reviewCreated.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate(){

        ///////////////////////////////
        // Review 테이블 Update 후 수행
        ///////////////////////////////

        // ReviewModified Event 발생
        ReviewModified reviewModified = new ReviewModified();
        BeanUtils.copyProperties(this, reviewModified);
        reviewModified.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove(){

        ////////////////////////////////
        // Review 테이블 Delete 전 수행
        ////////////////////////////////

        // ReviewDeleted Event 발생
        ReviewDeleted reviewDeleted = new ReviewDeleted();
        BeanUtils.copyProperties(this, reviewDeleted);
        reviewDeleted.publishAfterCommit();
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



    public static ReviewRepository repository(){
        ReviewRepository reviewRepository = GameApplication.applicationContext.getBean(ReviewRepository.class);
        return reviewRepository;
    }




}
