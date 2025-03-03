package itu.jca.eval.test.coworking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import itu.jca.eval.test.coworking.models.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String>{
    
}
