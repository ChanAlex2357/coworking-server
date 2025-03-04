package itu.jca.eval.test.coworking.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import itu.jca.eval.test.coworking.models.ReservationDetails;
import itu.jca.eval.test.coworking.models.Reservation;

@Repository
public interface ReservationDetailsRepository extends JpaRepository<ReservationDetails, String> {
    List<ReservationDetails> findByReservation(Reservation reservation);
} 