package itu.jca.eval.test.coworking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.jca.eval.test.coworking.models.Creneau;
import itu.jca.eval.test.coworking.repository.CreneauRepository;

@Service
public class CreneauService {
    
    @Autowired
    private CreneauRepository creneauRepository;

    public List<Creneau> findAll() {
        return creneauRepository.findAll();
    }

    public Optional<Creneau> findById(String id) {
        return creneauRepository.findById(id);
    }

    public Creneau save(Creneau creneau) {
        return creneauRepository.save(creneau);
    }

    public void deleteById(String id) {
        creneauRepository.deleteById(id);
    }

    public Creneau update(String id, Creneau creneau) {
        if (creneauRepository.existsById(id)) {
            creneau.setId(id);
            return creneauRepository.save(creneau);
        }
        throw new RuntimeException("Créneau non trouvé avec l'id: " + id);
    }
} 