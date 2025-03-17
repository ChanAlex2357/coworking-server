package itu.jca.eval.test.coworking.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itu.jca.eval.test.coworking.api.builder.ApiResponseBuilder;
import itu.jca.eval.test.coworking.dto.ReservationFormData;
import itu.jca.eval.test.coworking.models.Reservation;
import itu.jca.eval.test.coworking.service.ReservationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping(path = "/api/reservations")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ReservationController {

    private final ReservationService reservationService;

    ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    
    @PostMapping
    public ResponseEntity<?> postReservation(@RequestBody ReservationFormData reservationFormData) {
        try {
            Reservation reservation = reservationService.createReservation(reservationFormData);
            return ResponseEntity.ok().body( ApiResponseBuilder.success("Reservation created successfully", reservation));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body( ApiResponseBuilder.error400(e));
        }
    }
}
