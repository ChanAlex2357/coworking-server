package itu.jca.eval.test.coworking.models;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "dateReservation", nullable = false)
    private LocalDate dateReservation;

    @Column(nullable = false)
    private Integer duree;

    @Column(nullable = false)
    private BigDecimal montant;

    @ManyToOne
    @JoinColumn(name = "idClient", nullable = false)
    private Utilisateur client;

    @ManyToOne
    @JoinColumn(name = "idEspace", nullable = false)
    private Espace espace;
} 