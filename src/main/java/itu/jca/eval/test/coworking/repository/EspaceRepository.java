package itu.jca.eval.test.coworking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import itu.jca.eval.test.coworking.models.Espace;

@Repository
public interface EspaceRepository extends JpaRepository<Espace, String> {
    // Méthodes personnalisées si nécessaire
} 