package itu.jca.eval.test.coworking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.jca.eval.test.coworking.models.Espace;
import itu.jca.eval.test.coworking.models.PrixEspace;
import itu.jca.eval.test.coworking.models.Reservation;
import itu.jca.eval.test.coworking.models.Utilisateur;
import itu.jca.eval.test.coworking.repository.ReservationRepository;
import itu.jca.eval.test.coworking.utils.ImportUtils;

@Service
public class ReservationService {
    @Autowired
    private ReservationOptionService reservationOptionService;
    @Autowired
    private  UtilisateurService utilisateurService;
    @Autowired
    private EspaceService espaceService;
    @Autowired
    private PrixEspaceService prixEspaceService;

    @Autowired
    private ReservationDetailsService reservationDetailsService;
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
        controllerMontant(reservation);
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

    public void setClientFromContact(String clientContact , Reservation  reservation) {
        Utilisateur client = utilisateurService.findByContact(clientContact)
        .orElseThrow(() ->{
            throw new IllegalArgumentException("Client introuvable pour le contact specifier : "+clientContact);
        });
        System.out.println("- - - CLIENT OK");
        reservation.setClient(client);
    }

    public void setEspaceFromName(String espaceName , Reservation reservation)  {
        Espace espace = espaceService.findByNom(espaceName)
        .orElseThrow(() -> new IllegalArgumentException("Espace non trouvé: " + espaceName));
        System.out.println("- - - ESPACE OK");
        reservation.setEspace(espace);
    }

    public void controllerMontant(Reservation reservation) {
        if (reservation.getMontant() > 0) {
            return;
        }
        PrixEspace prixEspace = prixEspaceService.findCurrentPrixEspace(reservation.getEspace());
        double montantBase = prixEspace.getPrixHeure() * reservation.getDuree();
        reservation.setMontant(montantBase);
    }

    public Reservation loadReservation(String line) throws Exception {
        String[] values = null;
        try {
            values = ImportUtils.splitLine(line, ";", 7);
        } catch (Exception e) {
            throw e;
        }
        // Parse values
        String ref = values[0].trim();
        String espaceName = values[1].trim();
        String clientContact = values[2].trim();
        String dateStr = values[3].trim();
        String heureDebut = values[4].trim();
        int duree = Integer.parseInt(values[5].trim());
        String optionsStr = values[6].trim();
        // Création de la réservation
        Reservation reservation = new Reservation();
        reservation.setId(ref);
        reservation.setDateReservation(dateStr);
        reservation.setHeureDebut(heureDebut);
        reservation.setDuree(duree);
        System.out.println("RESERVATION BASE");
        try {
            setClientFromContact(clientContact, reservation);
        } catch (Exception eClient) {
            try {
                Utilisateur cUtilisateur = new Utilisateur();
                cUtilisateur.setNom(values[2]);
                cUtilisateur.setContact(clientContact);
                cUtilisateur = utilisateurService.save(cUtilisateur);
                reservation.setClient(cUtilisateur);
            } catch (Exception e) {
                throw eClient;
            }
        }
        setEspaceFromName(espaceName, reservation);
        // Sauvegarde de la réservation
        reservation = save(reservation);
        reservationOptionService.loadReservationOptions(optionsStr,reservation);
        reservation = save(reservation);
        reservationDetailsService.loadReservationDetails(reservation);
        return reservation;
    }
} 