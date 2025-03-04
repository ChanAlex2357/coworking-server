package itu.jca.eval.test.coworking.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import itu.jca.eval.test.coworking.models.ReservationOption;
import itu.jca.eval.test.coworking.models.Reservation;

@Repository
public interface ReservationOptionRepository extends JpaRepository<ReservationOption, String> {
    List<ReservationOption> findByReservation(Reservation reservation);
} 