package projectteam.domain;

import projectteam.domain.*;
import projectteam.infra.AbstractEvent;


public class DownloadStarted extends AbstractEvent {

    private Long downloadId;
    private Long customerId;
    private Long gameId;

    public Long getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(Long downloadId) {
        this.downloadId = downloadId;
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
