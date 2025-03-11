package itu.jca.eval.test.coworking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.jca.eval.test.coworking.models.Option;
import itu.jca.eval.test.coworking.repository.OptionRepository;
import itu.jca.eval.test.coworking.models.PrixOption;

@Service
public class OptionService {
    
    @Autowired
    private OptionRepository optionRepository;
    
    @Autowired
    private PrixOptionService prixOptionService;

    public void saveOptionWithPrice(String[] values) {
        Option option = new Option(values);
        option = save(option);
        System.out.println("--- Option saved!---");
        PrixOption prixOption = new PrixOption(option, values[2]);
        prixOptionService.save(prixOption);
    }

    public List<Option> findAll() {
        return optionRepository.findAll();
    }

    public Optional<Option> findById(String id) {
        return optionRepository.findById(id);
    }

    public Option save(Option option) {
        System.out.println("Oprtion on saving ...");
        return optionRepository.save(option);
    }

    public void deleteById(String id) {
        optionRepository.deleteById(id);
    }

    public Option update(String id, Option option) {
        if (optionRepository.existsById(id)) {
            option.setId(id);
            return optionRepository.save(option);
        }
        throw new RuntimeException("Option non trouvée avec l'id: " + id);
    }
} 