package itu.jca.eval.test.coworking.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private String id;

    @Column(name = "dateReservation", nullable = false)
    private Date dateReservation;

    @Column(name = "heureDebut", nullable = false)
    private Time heureDebut;

    @Column(nullable = false)
    private Integer duree;

    @Column(nullable = false)
    private double montant;

    @ManyToOne
    @JoinColumn(name = "idClient", nullable = false)
    private Utilisateur client;

    @ManyToOne
    @JoinColumn(name = "idEspace", nullable = false)
    private Espace espace;
} 