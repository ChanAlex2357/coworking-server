package itu.jca.eval.test.coworking.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import itu.jca.eval.test.coworking.models.Reservation;
import itu.jca.eval.test.coworking.models.Utilisateur;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    List<Reservation> findByClient(Utilisateur client);
} 