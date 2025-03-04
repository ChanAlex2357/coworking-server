package itu.jca.eval.test.coworking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.jca.eval.test.coworking.models.OptionPrix;
import itu.jca.eval.test.coworking.models.Option;
import itu.jca.eval.test.coworking.repository.OptionPrixRepository;

@Service
public class OptionPrixService {
    
    @Autowired
    private OptionPrixRepository optionPrixRepository;

    public List<OptionPrix> findAll() {
        return optionPrixRepository.findAll();
    }

    public Optional<OptionPrix> findById(String id) {
        return optionPrixRepository.findById(id);
    }

    public List<OptionPrix> findByOption(Option option) {
        return optionPrixRepository.findByOption(option);
    }

    public OptionPrix findCurrentOptionPrix(Option option) {
        return optionPrixRepository.findFirstByOptionOrderByDatePrixDesc(option);
    }

    public OptionPrix save(OptionPrix optionPrix) {
        return optionPrixRepository.save(optionPrix);
    }

    public void deleteById(String id) {
        optionPrixRepository.deleteById(id);
    }

    public OptionPrix update(String id, OptionPrix optionPrix) {
        if (optionPrixRepository.existsById(id)) {
            optionPrix.setId(id);
            return optionPrixRepository.save(optionPrix);
        }
        throw new RuntimeException("Prix option non trouvé avec l'id: " + id);
    }
} 