package shinydorky.mos.law_generator_backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shinydorky.mos.law_generator_backend.model.LawGroup;

import java.util.List;

@Repository
public interface LawGroupRepository extends CrudRepository<LawGroup, Long> {
    List<LawGroup> findAll();
}
