package itu.jca.eval.test.coworking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utilisateur_seq_generator")
    @SequenceGenerator(
        name = "utilisateur_seq_generator",
        sequenceName = "get_utilisateur_seq",
        allocationSize = 1
    )
    private String id;

    @Column(nullable = false)
    String nom;
}
