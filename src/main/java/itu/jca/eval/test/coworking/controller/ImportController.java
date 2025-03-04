package itu.jca.eval.test.coworking.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private EspaceService espaceService;

    @Autowired
    private PrixEspaceService prixEspaceService;

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

                    // Create Espace
                    Espace espace = new Espace();
                    espace.setNom(values[0].trim());
                    
                    // Save Espace
                    espace = espaceService.save(espace);

                    // Create PrixEspace
                    PrixEspace prixEspace = new PrixEspace();
                    prixEspace.setEspace(espace);
                    prixEspace.setDatePrix(LocalDate.now());
                    try {
                        prixEspace.setPrixHeure(new BigDecimal(values[1].trim()));
                    } catch (NumberFormatException e) {
                        errors.add("Ligne " + lineNumber + ": Prix invalide - " + values[1]);
                        continue;
                    }

                    // Save PrixEspace
                    prixEspaceService.save(prixEspace);

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

        return ResponseEntity.ok("Import réussi");
    }
}