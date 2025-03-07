package itu.jca.eval.test.coworking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import itu.jca.eval.test.coworking.models.Utilisateur;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
    Optional<Utilisateur> findByContact(String contact);
}
