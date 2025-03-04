package itu.jca.eval.test.coworking.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import itu.jca.eval.test.coworking.models.Paiement;
import itu.jca.eval.test.coworking.models.Reservation;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, String> {
    List<Paiement> findByReservation(Reservation reservation);
} 