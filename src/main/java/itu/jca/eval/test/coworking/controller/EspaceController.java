package itu.jca.eval.test.coworking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itu.jca.eval.test.coworking.api.results.EspaceCrenauxResponse;
import itu.jca.eval.test.coworking.dto.EspaceCreneauxRequest;
import itu.jca.eval.test.coworking.service.EspaceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/api/espace")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EspaceController {
    
    @Autowired
    private EspaceService espaceService;

    @PostMapping("/creneaux")
    public ResponseEntity<?> getEspaceCreneaux(@RequestBody EspaceCreneauxRequest request) {
        EspaceCrenauxResponse espaceCrenauxResponse = new EspaceCrenauxResponse(request.getDate());
        try {
            espaceService.findAllEspaceCrenaux(espaceCrenauxResponse);
        } catch (Exception e) {
            espaceCrenauxResponse.setException(e);
            return ResponseEntity.badRequest().body(espaceCrenauxResponse);
        }
        return ResponseEntity.ok().body(espaceCrenauxResponse);
    }
    
}
