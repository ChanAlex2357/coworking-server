package itu.jca.eval.test.coworking.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "reservationdetails")
public class ReservationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private BigDecimal pu;

    @ManyToOne
    @JoinColumn(name = "idCreneau", nullable = false)
    private Creneau creneau;

    @ManyToOne
    @JoinColumn(name = "idReservation", nullable = false)
    private Reservation reservation;
} 