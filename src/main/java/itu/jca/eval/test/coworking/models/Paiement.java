package itu.jca.eval.test.coworking.models;

import java.util.Date;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import itu.jca.eval.test.coworking.utils.TimeUtils;
@Getter
@Entity
@Table(name = "paiement")
public class Paiement {
    @Id
    private String id;

    @Column(name = "datepaiement", nullable = false)
    private Date datePaiement;

    @ManyToOne
    @JoinColumn(name = "idreservation", nullable = false)
    private Reservation reservation;

    public void setId(String id) {
        this.id = id;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public void setDatePaiement(String datePaiement) {
        setDatePaiement(TimeUtils.formatDate(datePaiement));
    }
} 