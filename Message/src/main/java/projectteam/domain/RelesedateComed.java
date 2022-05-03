package projectteam.domain;

import projectteam.domain.*;
import projectteam.infra.AbstractEvent;

import java.util.Date; 

public class RelesedateComed extends AbstractEvent {

    private Long id;
    private Long gameId;
    private String purchaseStatus;
    private Integer version;
    private Integer price;
    private Long customerId;
    private Date releaseDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
    public String getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(String purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
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
