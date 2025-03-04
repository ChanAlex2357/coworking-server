package itu.jca.eval.test.coworking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq_generator")
    @SequenceGenerator(
        name = "account_seq_generator",
        sequenceName = "get_account_seq",
        allocationSize = 1
    )
    private String id;

    @Column(nullable = false)
    String login;

    @Column(nullable = false)
    String password;

    @ManyToOne
    @JoinColumn(name = "idutilisateur", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "idrole", nullable = false)
    private RoleUtilisateur role;

    public boolean checkPassword(String password){
        return getPassword().equals(password);
    }
}