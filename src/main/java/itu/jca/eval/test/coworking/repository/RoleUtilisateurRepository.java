package itu.jca.eval.test.coworking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import itu.jca.eval.test.coworking.models.RoleUtilisateur;

public interface RoleUtilisateurRepository extends JpaRepository<RoleUtilisateur,String>{
    
}
