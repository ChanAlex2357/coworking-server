package itu.jca.eval.test.coworking.models;

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
@Table(name = "paiement")
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paiement_seq_generator")
    @SequenceGenerator(
        name = "paiement_seq_generator",
        sequenceName = "get_paiement_seq",
        allocationSize = 1
    )
    private String id;

    @Column(name = "datePaiement", nullable = false)
    private LocalDate datePaiement;

    @ManyToOne
    @JoinColumn(name = "idReservation", nullable = false)
    private Reservation reservation;
} 