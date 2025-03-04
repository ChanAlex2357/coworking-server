package itu.jca.eval.test.coworking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "espace")
public class Espace {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "espace_seq_generator")
    @SequenceGenerator(
        name = "espace_seq_generator",
        sequenceName = "get_espace_seq",
        allocationSize = 1
    )
    private String id;

    @Column(name = "nom", nullable = false)
    private String nom;
} 