package shinydorky.mos.law_generator_backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shinydorky.mos.law_generator_backend.model.LawType;

import java.util.List;

@Repository
public interface LawTypeRepository extends CrudRepository<LawType, Long> {
    List<LawType> findAll();
}
