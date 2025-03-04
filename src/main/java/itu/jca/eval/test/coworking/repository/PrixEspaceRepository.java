package itu.jca.eval.test.coworking.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import itu.jca.eval.test.coworking.models.PrixEspace;
import itu.jca.eval.test.coworking.models.Espace;

@Repository
public interface PrixEspaceRepository extends JpaRepository<PrixEspace, String> {
    List<PrixEspace> findByEspace(Espace espace);
    PrixEspace findFirstByEspaceOrderByDatePrixDesc(Espace espace);
} 