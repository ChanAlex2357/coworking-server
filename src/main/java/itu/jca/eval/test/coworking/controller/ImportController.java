package itu.jca.eval.test.coworking.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import itu.jca.eval.test.coworking.dto.ImportResponse;
import itu.jca.eval.test.coworking.models.Espace;
import itu.jca.eval.test.coworking.models.PrixEspace;
import itu.jca.eval.test.coworking.service.EspaceService;
import itu.jca.eval.test.coworking.service.PrixEspaceService;
import itu.jca.eval.test.coworking.service.PrixOptionService;
import itu.jca.eval.test.coworking.service.OptionService;
import itu.jca.eval.test.coworking.models.Utilisateur;
import itu.jca.eval.test.coworking.service.UtilisateurService;
import itu.jca.eval.test.coworking.models.Reservation;
import itu.jca.eval.test.coworking.service.ReservationService;
import itu.jca.eval.test.coworking.models.Option;
import itu.jca.eval.test.coworking.models.PrixOption;
import itu.jca.eval.test.coworking.models.ReservationOption;
import itu.jca.eval.test.coworking.service.ReservationOptionService;
import org.springframework.transaction.annotation.Transactional;


@RestController
@RequestMapping("/api/import")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImportController {

    @Autowired
    private EspaceService espaceService;

    @Autowired
    private PrixEspaceService prixEspaceService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private PrixOptionService prixOptionService;

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationOptionService reservationOptionService;

    @PostMapping("/espace")
    public ResponseEntity<?> importEspace(@RequestParam("file") MultipartFile file) {
        List<String> errors = new ArrayList<>();
        int lineNumber = 1;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            // Skip header line
            String line = br.readLine();
            if (line == null) {
                return ResponseEntity.badRequest().body("Le fichier est vide");
            }

            while ((line = br.readLine()) != null) {
                lineNumber++;
                try {
                    String[] values = line.split(",");
                    if (values.length != 2) {
                        errors.add("Ligne " + lineNumber + ": Format incorrect");
                        continue;
                    }
                    Espace espace = new Espace(values);
                    espace = espaceService.save(espace);
                    System.out.println("espace saved !");
                    // Generer un PrixEspace
                    try {
                        PrixEspace prixEspace = new PrixEspace(espace,values[1]);
                        prixEspaceService.save(prixEspace);
                    } catch (NumberFormatException e) {
                        errors.add("Ligne " + lineNumber + ": Prix invalide - " + values[1]);
                        continue;
                    } catch (IllegalArgumentException e) {
                        errors.add("Ligne " + lineNumber + ": " + e.getMessage());
                        continue;
                    }
                } catch (Exception e) {
                    errors.add("Ligne " + lineNumber + ": " + e.getMessage());
                }
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la lecture du fichier: " + e.getMessage());
        }

        if (!errors.isEmpty()) {
            ImportResponse response = new ImportResponse(
                "Import terminé avec des erreurs",
                errors
            );
            // System.out.println(response);
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok("Import espace réussi");
    }


    @PostMapping("/option")
    public ResponseEntity<?> importOption(@RequestParam("file") MultipartFile file) {
        List<String> errors = new ArrayList<>();
        int lineNumber = 1;
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line = reader.readLine();
            if (line == null) {
                return ResponseEntity.badRequest().body("Le fichier est vide");
            }

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    String[] values = line.split(",");
                    if (values.length != 3) {
                        errors.add("Ligne " + lineNumber + ": Format incorrect");
                        continue;
                    }
                    optionService.saveOptionWithPrice(values);
                } catch (Exception e) {
                    errors.add("Ligne " + lineNumber + ": " + e.getMessage());
                    throw new RuntimeException("Erreur lors de l'import à la ligne " + lineNumber, e);
                }
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la lecture du fichier: " + e.getMessage());
        }

            if (!errors.isEmpty()) {
                return ResponseEntity.badRequest().body(new ImportResponse(
                    "Import terminé avec des erreurs",
                    errors
                ));
            }

        return ResponseEntity.ok("Import option réussi");
    }

    @PostMapping("/reservation")
    @Transactional
    public ResponseEntity<?> importReservation(@RequestParam("file") MultipartFile file) {
        List<String> errors = new ArrayList<>();
        int lineNumber = 1;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            // Skip header line
            String line = br.readLine();
            if (line == null) {
                return ResponseEntity.badRequest().body("Le fichier est vide");
            }

            while ((line = br.readLine()) != null) {
                lineNumber++;
                try {
                    String[] values = line.split(",");
                    if (values.length != 7) {
                        System.out.println("VALUES : "+values.length);
                        errors.add("Ligne " + lineNumber + ": Format incorrect");
                        continue;
                    }

                    // Parse values
                    String ref = values[0].trim();
                    String espaceName = values[1].trim();
                    String clientContact = values[2].trim();
                    String dateStr = values[3].trim();
                    String heureDebut = values[4].trim();
                    Integer duree = Integer.parseInt(values[5].trim());
                    String optionsStr = values[6].trim();

                    // Recherche de l'espace
                    Espace espace = espaceService.findByNom(espaceName)
                        .orElseThrow(() -> new RuntimeException("Espace non trouvé: " + espaceName));
                    System.out.println("Espace ok");
                    // Recherche ou création de l'utilisateur
                    Utilisateur client = utilisateurService.findByContact(clientContact)
                    .orElseGet(() -> {
                        Utilisateur newClient = new Utilisateur();
                        newClient.setContact(clientContact);
                        return utilisateurService.save(newClient);
                    });
                    System.out.println("Utilisateur ok");

                    // Parse de la date et de l'heure
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dateReservation = LocalDate.parse(dateStr, dateFormatter);
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime heureDebutTime = LocalTime.parse(heureDebut, timeFormatter);

                    // Création de la réservation
                    Reservation reservation = new Reservation();
                    reservation.setId(ref);
                    reservation.setDateReservation(Date.valueOf(dateReservation));
                    reservation.setHeureDebut(Time.valueOf(heureDebutTime));
                    reservation.setDuree(duree);
                    reservation.setClient(client);
                    reservation.setEspace(espace);

                    // Calcul du montant de base (prix de l'espace * durée)
                    PrixEspace prixEspace = prixEspaceService.findCurrentPrixEspace(espace);
                    double montantBase = prixEspace.getPrixHeure() * duree;
                    reservation.setMontant(montantBase);

                    // Sauvegarde de la réservation
                    reservation = reservationService.save(reservation);

                    // Traitement des options
                    if (!optionsStr.isEmpty() && !optionsStr.equals("\"\"")) {
                        String[] optionIds = optionsStr.replace("\"", "").split(",");
                        for (String optionId : optionIds) {
                            Option option = optionService.findById(optionId.trim())
                                .orElseThrow(() -> new RuntimeException("Option non trouvée: " + optionId));

                            PrixOption prixOption = prixOptionService.findCurrentOptionPrix(option);
                            
                            ReservationOption resOption = new ReservationOption();
                            resOption.setOption(option);
                            resOption.setReservation(reservation);
                            resOption.setPu(prixOption.getPu());
                            
                            reservationOptionService.save(resOption);

                            // Mise à jour du montant total
                            reservation.setMontant(reservation.getMontant()+ prixOption.getPu());
                        }
                        // Mise à jour du montant final
                        reservationService.save(reservation);
                    }

                } catch (Exception e) {
                    errors.add("Ligne " + lineNumber + ": " + e.getMessage());
                }
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la lecture du fichier: " + e.getMessage());
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(new ImportResponse(
                "Import terminé avec des erreurs",
                errors
            ));
        }
        return ResponseEntity.ok("Import des réservations réussi");
    }

}