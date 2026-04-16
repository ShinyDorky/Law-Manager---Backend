package shinydorky.mos.law_generator_backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shinydorky.mos.law_generator_backend.model.LawOption;

import java.util.List;

@Repository
public interface LawOptionRepository extends CrudRepository<LawOption, Long> {
    List<LawOption> findAll();
}
