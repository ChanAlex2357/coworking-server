package itu.jca.eval.test.coworking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import itu.jca.eval.test.coworking.dto.models.EspaceCreneauDto;
import itu.jca.eval.test.coworking.models.Espace;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EspaceRepository extends JpaRepository<Espace, String> {
    Optional<Espace> findByNom(String nom);

    // Méthodes personnalisées si nécessaire
    @Query(value = """
        SELECT c.*, 
               rdc.dateReservation,
               rdc.espace as espace,
               rdc.etat as etat
        FROM creneau c
        LEFT JOIN (
            SELECT * FROM reservation_details_cpl r
            WHERE r.dateReservation = ?1 AND r.espace = ?2
        ) rdc ON rdc.idcreneau = c.id
        """, nativeQuery = true)
    List<EspaceCreneauDto> findCreneaux(Date datereservation , String espace);
} 