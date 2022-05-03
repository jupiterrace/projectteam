package projectteam.infra;
import projectteam.domain.*;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

@Component
public class GameHateoasProcessor implements RepresentationModelProcessor<EntityModel<Game>>  {

    @Override
    public EntityModel<Game> process(EntityModel<Game> model) {
        
        return model;
    }
    
}

