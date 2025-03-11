package itu.jca.eval.test.coworking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "espace")
@Getter
public class Espace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "nom", nullable = false)
    private String nom;


    public Espace () {}
    public Espace (String[] values) {
        setNom(values);
    }

    public void setNom(String[] values) {
        if (values.length > 2) {
            this.nom = values[1].trim();
        }else{
            this.nom = values[0].trim();
        }
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
} 