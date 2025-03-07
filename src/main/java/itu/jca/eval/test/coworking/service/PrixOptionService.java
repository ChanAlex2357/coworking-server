package itu.jca.eval.test.coworking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import itu.jca.eval.test.coworking.models.PrixOption;
import itu.jca.eval.test.coworking.models.Option;
import itu.jca.eval.test.coworking.repository.PrixOptionRepository;

@Service
public class PrixOptionService {
    
    @Autowired
    private PrixOptionRepository optionPrixRepository;

    public List<PrixOption> findAll() {
        return optionPrixRepository.findAll();
    }

    public Optional<PrixOption> findById(String id) {
        return optionPrixRepository.findById(id);
    }

    public List<PrixOption> findByOption(Option option) {
        return optionPrixRepository.findByOption(option);
    }

    public PrixOption findCurrentOptionPrix(Option option) {
        return optionPrixRepository.findFirstByOptionOrderByDatePrixDesc(option);
    }

    public PrixOption save(PrixOption optionPrix) {
        System.out.println("PrixOption on saving ...");
        System.out.println(optionPrix);
        try {
            return optionPrixRepository.save(optionPrix);
        } catch (Exception e) {
            System.out.println("!!!!! Erreur lors de la sauvegarde du prix option: " + e.getMessage());
            throw new RuntimeException("Erreur lors de la sauvegarde du prix option: " + e.getMessage());
        }
    }

    public void deleteById(String id) {
        optionPrixRepository.deleteById(id);
    }

    public PrixOption update(String id, PrixOption optionPrix) {
        if (optionPrixRepository.existsById(id)) {
            optionPrix.setId(id);
            return optionPrixRepository.save(optionPrix);
        }
        throw new RuntimeException("Prix option non trouvé avec l'id: " + id);
    }
} 