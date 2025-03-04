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
@Table(name = "prixespace")
public class PrixEspace {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prix_espace_seq_generator")
    @SequenceGenerator(
        name = "prix_espace_seq_generator",
        sequenceName = "get_prix_espace_seq",
        allocationSize = 1
    )
    private String id;

    @Column(name = "datePrix", nullable = false)
    private LocalDate datePrix;

    @Column(name = "prixHeure", nullable = false)
    private BigDecimal prixHeure;

    @ManyToOne
    @JoinColumn(name = "idEspace", nullable = false)
    private Espace espace;
} 