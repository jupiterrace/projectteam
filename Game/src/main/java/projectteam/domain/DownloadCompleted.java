package projectteam.domain;

import projectteam.domain.*;
import projectteam.infra.AbstractEvent;


public class DownloadCompleted extends AbstractEvent {

    private Long downloadId;
    private Long customerId;
    private Long gameId;
    private String status;

    public DownloadCompleted(){
        super();
    }

    
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
