package projectteam.domain;

import projectteam.domain.*;
import projectteam.infra.AbstractEvent;


public class GamePurchased extends AbstractEvent {

    private Long id;

    public GamePurchased(){
        super();
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
