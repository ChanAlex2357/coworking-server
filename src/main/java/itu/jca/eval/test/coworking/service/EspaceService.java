package itu.jca.eval.test.coworking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import itu.jca.eval.test.coworking.models.Espace;
import itu.jca.eval.test.coworking.repository.EspaceRepository;

@Service
public class EspaceService {
    
    @Autowired
    private EspaceRepository espaceRepository;

    public List<Espace> findAll() {
        return espaceRepository.findAll();
    }

    public Optional<Espace> findById(String id) {
        return espaceRepository.findById(id);
    }

    public Espace save(Espace espace) {
        System.out.println(
            espace.getNom()
        );
        return espaceRepository.save(espace);
    }

    public void deleteById(String id) {
        espaceRepository.deleteById(id);
    }

    public Espace update(String id, Espace espace) {
        if (espaceRepository.existsById(id)) {
            espace.setId(id);
            return espaceRepository.save(espace);
        }
        throw new RuntimeException("Espace non trouvé avec l'id: " + id);
    }

    @Transactional(readOnly = true)
    public Optional<Espace> findByNom(String nom) {
        return espaceRepository.findByNom(nom);
    }
} 