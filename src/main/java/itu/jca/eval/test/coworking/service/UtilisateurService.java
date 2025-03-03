package itu.jca.eval.test.coworking.service;
import org.springframework.stereotype.Service;

import itu.jca.eval.test.coworking.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
}
