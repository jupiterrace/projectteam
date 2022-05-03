package projectteam.domain;

import projectteam.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="games", path="games")
public interface GameRepository extends PagingAndSortingRepository<Game, Long>{


}
