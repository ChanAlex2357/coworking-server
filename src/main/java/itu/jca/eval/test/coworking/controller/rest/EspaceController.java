package itu.jca.eval.test.coworking.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itu.jca.eval.test.coworking.api.builder.ApiResponseBuilder;
import itu.jca.eval.test.coworking.api.results.EspaceCrenauxResponse;
import itu.jca.eval.test.coworking.dto.EspaceCreneauxRequest;
import itu.jca.eval.test.coworking.models.Espace;
import itu.jca.eval.test.coworking.service.EspaceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/api/espaces")
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

    @GetMapping
    public ResponseEntity<?> findEspaces(){
        List<Espace> espaces ;
        try {
            espaces = espaceService.findAll();
            return ResponseEntity.ok().body(ApiResponseBuilder.success("espaces Ok", espaces));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.error500(e));
        }
    }
    
}
