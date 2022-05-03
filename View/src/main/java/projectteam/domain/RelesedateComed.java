package projectteam.domain;

import projectteam.domain.*;
import projectteam.infra.AbstractEvent;
import java.util.Date; 


public class RelesedateComed extends AbstractEvent {
    private Long gameId;
    private Date releaseDate;
    private Long customerId;

    public RelesedateComed(){
        super();
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
    
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
