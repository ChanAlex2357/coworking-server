package itu.jca.eval.test.coworking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "roleutilisateur")
public class RoleUtilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    String id;

    @Column(nullable =  false)
    String libelle;

}
