package itu.jca.eval.test.coworking.models;

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
@Table(name = "reservationoption")
public class ReservationOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private double pu;

    @ManyToOne
    @JoinColumn(name = "idOption", nullable = false)
    private Option option;

    @ManyToOne
    @JoinColumn(name = "idReservation", nullable = false)
    private Reservation reservation;
} 