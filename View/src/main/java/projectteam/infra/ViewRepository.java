package projectteam.infra;

import projectteam.domain.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ViewRepository extends CrudRepository<View, Long> {


}