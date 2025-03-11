package itu.jca.eval.test.coworking.models;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "creneau")
public class Creneau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "heureDebut", nullable = false)
    private Time heureDebut;

    @Column(name = "heureFin", nullable = false)
    private Time heureFin;
} 