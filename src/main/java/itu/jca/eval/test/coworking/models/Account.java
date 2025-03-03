package itu.jca.eval.test.coworking.models;

import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    @Column(nullable = false)
    String login;

    @Column(nullable = false)
    String password;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "idUtilisateur" , nullable = false)
    Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "idRole" , nullable = false)
    RoleUtilisateur role;
}
