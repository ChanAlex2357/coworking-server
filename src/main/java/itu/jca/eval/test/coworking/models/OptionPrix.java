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
@Table(name = "optionprix")
public class OptionPrix {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "option_prix_seq_generator")
    @SequenceGenerator(
        name = "option_prix_seq_generator",
        sequenceName = "get_option_prix_seq",
        allocationSize = 1
    )
    private String id;

    @Column(name = "datePrix", nullable = false)
    private LocalDate datePrix;

    @Column(nullable = false)
    private BigDecimal pu;

    @ManyToOne
    @JoinColumn(name = "idOption", nullable = false)
    private Option option;
} 