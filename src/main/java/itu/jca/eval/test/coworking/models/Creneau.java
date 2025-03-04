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
@Table(name = "creneau")
public class Creneau {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "creneau_seq_generator")
    @SequenceGenerator(
        name = "creneau_seq_generator",
        sequenceName = "get_creneau_seq",
        allocationSize = 1
    )
    private String id;

    @Column(name = "heureDebut", nullable = false)
    private Integer heureDebut;

    @Column(name = "heureFin", nullable = false)
    private Integer heureFin;
} 