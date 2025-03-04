package itu.jca.eval.test.coworking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.jca.eval.test.coworking.models.PrixEspace;
import itu.jca.eval.test.coworking.models.Espace;
import itu.jca.eval.test.coworking.repository.PrixEspaceRepository;

@Service
public class PrixEspaceService {
    
    @Autowired
    private PrixEspaceRepository prixEspaceRepository;

    public List<PrixEspace> findAll() {
        return prixEspaceRepository.findAll();
    }

    public Optional<PrixEspace> findById(String id) {
        return prixEspaceRepository.findById(id);
    }

    public List<PrixEspace> findByEspace(Espace espace) {
        return prixEspaceRepository.findByEspace(espace);
    }

    public PrixEspace findCurrentPrixEspace(Espace espace) {
        return prixEspaceRepository.findFirstByEspaceOrderByDatePrixDesc(espace);
    }

    public PrixEspace save(PrixEspace prixEspace) {
        return prixEspaceRepository.save(prixEspace);
    }

    public void deleteById(String id) {
        prixEspaceRepository.deleteById(id);
    }

    public PrixEspace update(String id, PrixEspace prixEspace) {
        if (prixEspaceRepository.existsById(id)) {
            prixEspace.setId(id);
            return prixEspaceRepository.save(prixEspace);
        }
        throw new RuntimeException("Prix espace non trouvé avec l'id: " + id);
    }
} 