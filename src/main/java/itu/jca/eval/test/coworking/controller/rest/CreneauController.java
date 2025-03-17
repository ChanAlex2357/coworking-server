package itu.jca.eval.test.coworking.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import itu.jca.eval.test.coworking.api.builder.ApiResponseBuilder;
import itu.jca.eval.test.coworking.models.Creneau;
import itu.jca.eval.test.coworking.service.CreneauService;

@RestController
@RequestMapping(path = "/api/creneaux")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CreneauController {
    
    @Autowired
    private CreneauService creneauService;

    @GetMapping
    public ResponseEntity<?> findCreneaux(){
        List<Creneau> creneax = null;
        try {
            creneax = creneauService.findAll();
            return ResponseEntity.ok().body(ApiResponseBuilder.success("creneaux finded successfully!", creneax));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ApiResponseBuilder.error500(e));
        }
    }
}
