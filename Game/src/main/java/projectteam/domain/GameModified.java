package projectteam.domain;

import projectteam.domain.*;
import projectteam.infra.AbstractEvent;
import java.util.Date; 

public class GameModified extends AbstractEvent {

    private Long gameId;
    private String status;
    private Integer version;
    private Integer price;
    private Date playtime;
    private Long reviewCount;
    private Date releaseDate;

    private String lastAction;

    public GameModified(){
        super();
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
}
