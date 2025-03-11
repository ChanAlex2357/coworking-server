package itu.jca.eval.test.coworking.repository;

import java.sql.Time;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import itu.jca.eval.test.coworking.models.Creneau;

@Repository
public interface CreneauRepository extends JpaRepository<Creneau, String> {
    Creneau findFirstByHeureDebut(Time heureDebut);
    Creneau findFirstByHeureDebutAfter(Time heureDebut);
} 