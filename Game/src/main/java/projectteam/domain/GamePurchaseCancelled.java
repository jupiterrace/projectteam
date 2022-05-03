package projectteam.domain;

import projectteam.domain.*;
import projectteam.infra.AbstractEvent;


public class GamePurchaseCancelled extends AbstractEvent {

    private Long id;

    public GamePurchaseCancelled(){
        super();
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
