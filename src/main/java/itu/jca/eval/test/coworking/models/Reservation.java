package itu.jca.eval.test.coworking.models;

import java.sql.Date;
import java.sql.Time;
import itu.jca.eval.test.coworking.utils.TimeUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "reservation")
public class Reservation{
    @Id
    private String id;

    @Column(name = "datereservation", nullable = false)
    private Date dateReservation;

    @Column(name = "heuredebut", nullable = false)
    private Time heureDebut;

    @Column(nullable = false)
    private int duree;

    @Column(nullable = false)
    private double montant;

    @ManyToOne
    @JoinColumn(name = "idclient", nullable = false)
    private Utilisateur client;

    @ManyToOne
    @JoinColumn(name = "idespace", nullable = false)
    private Espace espace;

    public void setId(String id) {
        this.id = id;
    }
    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }
    public void setHeureDebut(Time heureDebut) {
        if (heureDebut.toLocalTime().getHour() > 18 || heureDebut.toLocalTime().getHour() < 8) {
            throw new IllegalArgumentException("L'heure de début de la réservation ne peut pas être supérieure à 18h ou inférieure à 8h");
        }
        this.heureDebut = heureDebut;
    }
    public void setDuree(int duree) {
        if (duree > 4 || duree < 1) {
            throw new IllegalArgumentException("La durée de la réservation ne peut pas être supérieure à 4 heures ou inférieure à 1 heure");
        }
        this.duree = duree;
    }
    public void setMontant(double montant) {
        if (montant <= 0 ) {
            throw new IllegalArgumentException("Le montant ne peut pas être négatif ou nul");
        }
        this.montant = montant;
    }
    public void setClient(Utilisateur client) {
        if (client == null) {
            throw new IllegalArgumentException("Impossible de faire une reservation pour un client inexistant");
        }
        this.client = client;
    }
    public void setEspace(Espace espace) {
        if (espace == null) {
            throw new IllegalArgumentException("Impossible de faire une reservation pour un espace inexistant");
        }
        this.espace = espace;
    }
    public void setDateReservation(String dateReservation) {
        if (dateReservation == null) {
            throw new IllegalArgumentException("La date de reservation ne peut pas être nulle");
        }
        setDateReservation(TimeUtils.formatDate(dateReservation));
    }
    public void setHeureDebut(String heureDebut) {
        if (heureDebut == null) {
            throw new IllegalArgumentException("L'heure de debut ne peut pas être nulle");
        }
        setHeureDebut(TimeUtils.formatTime(heureDebut));
    }
    public void setDuree(String duree) {
        if (duree == null) {
            throw new IllegalArgumentException("La duree ne peut pas être nulle");
        }
        int dureeInt = Integer.parseInt(duree);
        setDuree(dureeInt);
    }
    public void setMontant(String montant) {
        if (montant == null) {
            throw new IllegalArgumentException("Le montant ne peut pas être nul");
        }
        double montantDouble = Double.parseDouble(montant);
        setMontant(montantDouble);
    }
    

} 