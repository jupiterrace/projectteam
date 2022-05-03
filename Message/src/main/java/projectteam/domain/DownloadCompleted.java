package projectteam.domain;

import projectteam.domain.*;
import projectteam.infra.AbstractEvent;


public class DownloadCompleted extends AbstractEvent {

    private Long downlaodId;
    private Long customerId;
    private Long gameId;

    public Long getDownlaodId() {
        return downlaodId;
    }

    public void setDownlaodId(Long downlaodId) {
        this.downlaodId = downlaodId;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
