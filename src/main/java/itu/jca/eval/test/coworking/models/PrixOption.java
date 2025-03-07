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

@Getter
@Entity
@Table(name = "optionprix")
public class PrixOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "dateprix", nullable = false)
    private Date datePrix;

    @Column(nullable = false)
    private double pu;

    @ManyToOne
    @JoinColumn(name = "idoption", nullable = false)
    private Option option;

    public PrixOption(){}
    public PrixOption(Option option,Date datePrix,String pu){
        setOption(option);
        setDatePrix(datePrix);
        setPu(pu);
    }
    public PrixOption(Option option,String pu) {
        this(option, Date.valueOf(LocalDate.now()), pu);
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setOption(Option option) {
        this.option = option;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public void setDatePrix(String datePrix) {
        if(datePrix == null) throw new IllegalArgumentException("La date ne peut pas être nulle");
        try {
            this.datePrix = Date.valueOf(datePrix);
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Format de date invalide. Utilisez le format YYYY-MM-DD");
        }
    }

    public void setDatePrix(Date datePrix) {
        this.datePrix = datePrix;
    }

    public void setPu(String pu) {
        if(pu == null) throw new IllegalArgumentException("Le prix ne peut pas être nul");
        try {
            if(Double.valueOf(pu) < 0) throw new IllegalArgumentException("Le prix ne peut pas être négatif");
            this.pu = Double.valueOf(pu);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Format de prix invalide");
        }
    }

    @Override
    public String toString() {
        return "PrixOption{" +
                "id='" + id + '\'' +
                ", datePrix=" + datePrix +
                ", pu=" + pu +
                ", option=" + option +
                '}';
    }
} 