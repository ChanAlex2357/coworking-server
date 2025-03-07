package itu.jca.eval.test.coworking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.jca.eval.test.coworking.models.Utilisateur;
import itu.jca.eval.test.coworking.repository.UtilisateurRepository;

import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Optional<Utilisateur> findByContact(String contact) {
        return utilisateurRepository.findByContact(contact);
    }

    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }
} 