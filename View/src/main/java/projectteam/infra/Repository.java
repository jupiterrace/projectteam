package projectteam.infra;

import projectteam.domain.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Repository extends CrudRepository<View, Long> {


}