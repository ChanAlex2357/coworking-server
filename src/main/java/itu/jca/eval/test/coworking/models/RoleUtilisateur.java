package itu.jca.eval.test.coworking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
@Entity
@Table(name = "roleutilisateur")
public class RoleUtilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_user_seq_generator")
    @SequenceGenerator(
        name = "role_user_seq_generator",
        sequenceName = "get_role_user_seq",
        allocationSize = 1
    )
    private String id;

    @Column(nullable = false)
    private String libelle;
}
