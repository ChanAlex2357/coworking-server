package itu.jca.eval.test.coworking.models;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "prixespace")
@Getter
public class PrixEspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "dateprix", nullable = false)
    private Date datePrix;

    @Column(name = "prixheure", nullable = false)
    private double prixHeure;

    @ManyToOne
    @JoinColumn(name = "idespace", nullable = false)
    private Espace espace;

    public PrixEspace(){}
    public PrixEspace(Espace espace,Date datePrix,String pu) {
        setEspace(espace);
        setDatePrix(datePrix);
        setPrixHeure(pu);
    }
    public PrixEspace(Espace espace,String pu) {
        this(espace, Date.valueOf(LocalDate.now()), pu);
    }

    public void setEspace(Espace espace) {
        this.espace = espace;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setDatePrix(String date){
        if(date == null) throw new IllegalArgumentException("La date ne peut pas être nulle");
        try {
            this.datePrix = Date.valueOf(date);
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Format de date invalide. Utilisez le format YYYY-MM-DD");
        }
    }
    public void setDatePrix(Date datePrix) {
        this.datePrix = datePrix;
    }

    public void setPrixHeure(String prixHeure){
        if(prixHeure == null) throw new IllegalArgumentException("Le prix ne peut pas être nul");
        try {
            if(Double.valueOf(prixHeure) < 0) throw new IllegalArgumentException("Le prix ne peut pas être négatif");
            this.prixHeure = Double.valueOf(prixHeure);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Format de prix invalide");
        }
    }
    public void setPrixHeure(Double prixHeure) {
        this.prixHeure = prixHeure;
    }
} 