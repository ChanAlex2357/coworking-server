package itu.jca.eval.test.coworking.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import itu.jca.eval.test.coworking.models.OptionPrix;
import itu.jca.eval.test.coworking.models.Option;

@Repository
public interface OptionPrixRepository extends JpaRepository<OptionPrix, String> {
    List<OptionPrix> findByOption(Option option);
    OptionPrix findFirstByOptionOrderByDatePrixDesc(Option option);
} 