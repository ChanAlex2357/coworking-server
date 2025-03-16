package itu.jca.eval.test.coworking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.jca.eval.test.coworking.enums.ReservationEtat;
import itu.jca.eval.test.coworking.models.Paiement;
import itu.jca.eval.test.coworking.models.Reservation;
import itu.jca.eval.test.coworking.repository.PaiementRepository;
import itu.jca.eval.test.coworking.utils.ImportUtils;

@Service
public class PaiementService {
    @Autowired
    private ReservationService reservationService;
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

    public void loadPaiement(String line) throws Exception {
        String[] values = ImportUtils.splitLine(line,",",3);
        Paiement paiement = new Paiement();
        paiement.setId(values[0]);
        paiement.setDatePaiement(values[2]);
        paiement.setReservation(reservationService.findById(values[1]).orElseThrow(() -> new RuntimeException("Reservation non trouvée avec l'id: " + values[1])));
        if (paiement.getReservation().getEtat() == ReservationEtat.RESERVER) {
            reservationService.valider(paiement.getReservation());
        }
        createPaiement(paiement);
    }

    public Paiement createPaiement(Paiement paiement) throws Exception {
        controllerEtatReservationAvantPaiement(paiement.getReservation());
        paiement.payer();
        reservationService.payer(paiement.getReservation());
        return save(paiement);
    }

    public Paiement valider(Paiement paiement) throws Exception {
        paiement.valider();
        reservationService.validerPaiementReservation(paiement.getReservation());
        return save(paiement);
    }

    public void controllerEtatReservationAvantPaiement(Reservation reservation) throws Exception{
        if (reservation.getEtat() == ReservationEtat.RESERVER) {
            throw new Exception("La reservation doit etre valider avant d'etre payer");
        }
        else if (reservation.getEtat().getEtat() >= ReservationEtat.PAYER.getEtat()) {
            throw new Exception("La reservation a deja ete payer");
        }
    } 
} 