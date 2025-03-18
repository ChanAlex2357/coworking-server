package itu.jca.eval.test.coworking.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itu.jca.eval.test.coworking.api.builder.ApiResponseBuilder;
import itu.jca.eval.test.coworking.models.Option;
import itu.jca.eval.test.coworking.service.OptionService;

@RestController
@RequestMapping(path = "/api/options")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @GetMapping
    public ResponseEntity<?> findOptions(){
        List<Option> options = null;
        try {
            options = optionService.findAll();
            return ResponseEntity.ok().body(ApiResponseBuilder.success("options finded successfully!", options));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ApiResponseBuilder.error500(e));
        }
    }
}
