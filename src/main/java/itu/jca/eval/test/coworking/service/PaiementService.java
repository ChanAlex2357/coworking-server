package itu.jca.eval.test.coworking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.jca.eval.test.coworking.models.Paiement;
import itu.jca.eval.test.coworking.models.Reservation;
import itu.jca.eval.test.coworking.repository.PaiementRepository;

@Service
public class PaiementService {
    
    @Autowired
    private PaiementRepository paiementRepository;

    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }

    public Optional<Paiement> findById(String id) {
        return paiementRepository.findById(id);
    }

    public List<Paiement> findByReservation(Reservation reservation) {
        return paiementRepository.findByReservation(reservation);
    }

    public Paiement save(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    public void deleteById(String id) {
        paiementRepository.deleteById(id);
    }

    public Paiement update(String id, Paiement paiement) {
        if (paiementRepository.existsById(id)) {
            paiement.setId(id);
            return paiementRepository.save(paiement);
        }
        throw new RuntimeException("Paiement non trouvé avec l'id: " + id);
    }
} 