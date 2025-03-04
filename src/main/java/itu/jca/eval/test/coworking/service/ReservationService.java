package itu.jca.eval.test.coworking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.jca.eval.test.coworking.models.Reservation;
import itu.jca.eval.test.coworking.models.Utilisateur;
import itu.jca.eval.test.coworking.repository.ReservationRepository;

@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findById(String id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> findByClient(Utilisateur client) {
        return reservationRepository.findByClient(client);
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void deleteById(String id) {
        reservationRepository.deleteById(id);
    }

    public Reservation update(String id, Reservation reservation) {
        if (reservationRepository.existsById(id)) {
            reservation.setId(id);
            return reservationRepository.save(reservation);
        }
        throw new RuntimeException("Réservation non trouvée avec l'id: " + id);
    }
} 