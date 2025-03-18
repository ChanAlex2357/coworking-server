package itu.jca.eval.test.coworking.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import itu.jca.eval.test.coworking.api.builder.ApiResponseBuilder;
import itu.jca.eval.test.coworking.dto.ReservationFormData;
import itu.jca.eval.test.coworking.models.Reservation;
import itu.jca.eval.test.coworking.service.ReservationService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping()
    public ResponseEntity<?> findReservation(@RequestParam String userId){
        try {
            List<Reservation> reservations = reservationService.findByClient(userId);
            return ResponseEntity.ok().body(ApiResponseBuilder.success("reservation finded", reservations));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.error500(e));
        }
    }

    @GetMapping(path = "/annuler")
    public ResponseEntity<?> getMethodName(@RequestParam String id) {
        try {
            Reservation reservation = reservationService.findById(id).orElseThrow(() -> new IllegalArgumentException("Reservation introuvable : "+id));
            reservationService.annuler(reservation);
            return ResponseEntity.ok().body(ApiResponseBuilder.success("Reservation annuler", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ApiResponseBuilder.error400(e));
        }
    }
    
}
