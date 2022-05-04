package projectteam.domain;

import projectteam.GameApplication;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date; 


@Entity
@Table(name="Game_table")
public class Game  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long gameId;
    private String status;
    private Integer version;
    private Integer price;
    private Date playtime;
    private Long reviewCount;
    private Date releaseDate;

    private String lastAction;
    private Long purchaseCount;

    @PostPersist
    public void onPostPersist(){
        //////////////////////////////
        // Game 테이블 Insert 후 수행
        //////////////////////////////

        // 기본값 셋팅
        lastAction = "register";    // Insert는 항상 register
        reviewCount = 0L;             // 리뷰 건수는 따로 입력이 들어오지 않아도 기본값 0

        GameRegistered gameRegistered = new GameRegistered();
        BeanUtils.copyProperties(this, gameRegistered);
        gameRegistered.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate(){

        /////////////////////////////
        // Game 테이블 Update 후 수행
        /////////////////////////////

        // GameModified Event 발생 혹은 리뷰 이벤트 발생시
        if(lastAction.equals("modify") || lastAction.equals("review")) {
            GameModified gameModified = new GameModified();
            BeanUtils.copyProperties(this, gameModified);
            gameModified.publishAfterCommit();
        }

        // GamePurchased Event 발생
        if(lastAction.equals("purchased")) {
            GamePurchased gamePurchased = new GamePurchased();
            BeanUtils.copyProperties(this, gamePurchased);
            gamePurchased.publishAfterCommit();
        }

        // GamePurchaseCancelled Event 발생
        if(lastAction.equals("purchasedCancelled")) {
            GamePurchaseCancelled gamePurchaseCancelled = new GamePurchaseCancelled();
            BeanUtils.copyProperties(this, gamePurchaseCancelled);
            gamePurchaseCancelled.publishAfterCommit();
        }
        
        // DownloadStarted Event 발생
        if(lastAction.equals("downloadStarted")) {
            DownloadStarted downloadStarted = new DownloadStarted();
            BeanUtils.copyProperties(this, downloadStarted);
            downloadStarted.publishAfterCommit();
        }
        
        // DownloadCancelled Event 발생
        if(lastAction.equals("downloadCancelled")) {
            DownloadCancelled downloadCancelled = new DownloadCancelled();
            BeanUtils.copyProperties(this, downloadCancelled);
            downloadCancelled.publishAfterCommit();
        }

        // DownloadCompleted Event 발생
        if(lastAction.equals("downloadCompleted")) {
            DownloadCompleted downloadCompleted = new DownloadCompleted();
            BeanUtils.copyProperties(this, downloadCompleted);
            downloadCompleted.publishAfterCommit();
        }

        // DownloadCompleted Event 발생
        if(lastAction.equals("relesedateComed")) {
            RelesedateComed relesedateComed = new RelesedateComed();
            BeanUtils.copyProperties(this, relesedateComed);
            relesedateComed.publishAfterCommit();
        }
    }

    @PreRemove
    public void onPreRemove(){

        ////////////////////////////
        // Game 테이블 Delete 전 수행
        ////////////////////////////

        // GameDeleted Event 발생
        GameDeleted gameDeleted = new GameDeleted();
        BeanUtils.copyProperties(this, gameDeleted);
        gameDeleted.publishAfterCommit();
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getPlaytime() {
        return playtime;
    }

    public void setPlaytime(Date playtime) {
        this.playtime = playtime;
    }

    public Long getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Long reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    // 추가
    public String getLastAction() {
        return lastAction;
    }

    public void setLastAction(String lastAction) {
        this.lastAction = lastAction;
    }

    public Long getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(Long purchaseCount) {
        this.purchaseCount = purchaseCount;
    }


    public static GameRepository repository(){
        GameRepository gameRepository = GameApplication.applicationContext.getBean(GameRepository.class);
        return gameRepository;
    }

    public void release(){
        RelesedateComed relesedateComed = new RelesedateComed();
        /*
        Input Event Content
        */
        this.setLastAction("relesedateComed");
        this.setStatus("released");
        BeanUtils.copyProperties(this, relesedateComed);
        relesedateComed.publish();
    }

    public static void updateReviewCount(ReviewCreated reviewCreated){

        Game game = new Game();
        /*
        LOGIC GOES HERE
        */

        repository().save(game);


    }
    public static void updateReviewCount(ReviewDeleted reviewDeleted){

        Game game = new Game();
        /*
        LOGIC GOES HERE
        */
        repository().save(game);


    }

    public static void completePurchase(PurchaseCompleted purchaseCompleted){

        Game game = new Game();
        /*
        LOGIC GOES HERE
        */
        repository().save(game);


    }

    public static void cancelPurchase(PurchaseCancelCompleted purchaseCancelCompleted){

        Game game = new Game();
        /*
        LOGIC GOES HERE
        */
        repository().save(game);


    }

}
