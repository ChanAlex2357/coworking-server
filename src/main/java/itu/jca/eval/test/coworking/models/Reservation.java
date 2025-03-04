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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_seq_generator")
    @SequenceGenerator(
        name = "reservation_seq_generator",
        sequenceName = "get_reservation_seq",
        allocationSize = 1
    )
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