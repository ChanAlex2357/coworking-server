package itu.jca.eval.test.coworking.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


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
import itu.jca.eval.test.coworking.service.OptionService;
import itu.jca.eval.test.coworking.service.ReservationService;
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
    private ReservationService reservationService;

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
                System.out.println("~~~ LINE "+lineNumber+": "+line+" ~~~");
                try {
                    reservationService.loadReservation(line);
                } catch (Exception e) {
                    errors.add("Ligne " + lineNumber + ": " + e.getMessage());
                    System.out.println("ERROR : "+e.getMessage());
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