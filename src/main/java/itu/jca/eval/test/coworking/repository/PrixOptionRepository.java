package itu.jca.eval.test.coworking.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import itu.jca.eval.test.coworking.models.PrixOption;
import itu.jca.eval.test.coworking.models.Option;

@Repository
public interface PrixOptionRepository extends JpaRepository<PrixOption, String> {
    List<PrixOption> findByOption(Option option);
    PrixOption findFirstByOptionOrderByDatePrixDesc(Option option);
} 