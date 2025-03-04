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
@Table(name = "option")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "option_seq_generator")
    @SequenceGenerator(
        name = "option_seq_generator",
        sequenceName = "get_option_seq",
        allocationSize = 1
    )
    private String id;

    @Column(name = "option", nullable = false)
    private String option;
} 