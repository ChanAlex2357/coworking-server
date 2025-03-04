package itu.jca.eval.test.coworking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.jca.eval.test.coworking.models.ReservationOption;
import itu.jca.eval.test.coworking.models.Reservation;
import itu.jca.eval.test.coworking.repository.ReservationOptionRepository;

@Service
public class ReservationOptionService {
    
    @Autowired
    private ReservationOptionRepository reservationOptionRepository;

    public List<ReservationOption> findAll() {
        return reservationOptionRepository.findAll();
    }

    public Optional<ReservationOption> findById(String id) {
        return reservationOptionRepository.findById(id);
    }

    public List<ReservationOption> findByReservation(Reservation reservation) {
        return reservationOptionRepository.findByReservation(reservation);
    }

    public ReservationOption save(ReservationOption reservationOption) {
        return reservationOptionRepository.save(reservationOption);
    }

    public void deleteById(String id) {
        reservationOptionRepository.deleteById(id);
    }

    public ReservationOption update(String id, ReservationOption reservationOption) {
        if (reservationOptionRepository.existsById(id)) {
            reservationOption.setId(id);
            return reservationOptionRepository.save(reservationOption);
        }
        throw new RuntimeException("Option de réservation non trouvée avec l'id: " + id);
    }
} 