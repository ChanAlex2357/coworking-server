package itu.jca.eval.test.coworking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.jca.eval.test.coworking.models.ReservationDetails;
import itu.jca.eval.test.coworking.models.Reservation;
import itu.jca.eval.test.coworking.repository.ReservationDetailsRepository;

@Service
public class ReservationDetailsService {
    
    @Autowired
    private ReservationDetailsRepository reservationDetailsRepository;

    public List<ReservationDetails> findAll() {
        return reservationDetailsRepository.findAll();
    }

    public Optional<ReservationDetails> findById(String id) {
        return reservationDetailsRepository.findById(id);
    }

    public List<ReservationDetails> findByReservation(Reservation reservation) {
        return reservationDetailsRepository.findByReservation(reservation);
    }

    public ReservationDetails save(ReservationDetails reservationDetails) {
        return reservationDetailsRepository.save(reservationDetails);
    }

    public void deleteById(String id) {
        reservationDetailsRepository.deleteById(id);
    }

    public ReservationDetails update(String id, ReservationDetails reservationDetails) {
        if (reservationDetailsRepository.existsById(id)) {
            reservationDetails.setId(id);
            return reservationDetailsRepository.save(reservationDetails);
        }
        throw new RuntimeException("Détail de réservation non trouvé avec l'id: " + id);
    }
} 