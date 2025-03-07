package itu.jca.eval.test.coworking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "option")
public class Option {
    @Id
    private String id;

    @Column(name = "option", nullable = false)
    private String option;


    public Option(){}
    public Option(String[] values) {
        setId(values[0].trim());
        setOption(values[1].trim());
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setOption(String option) {
        this.option = option;
    }
} 