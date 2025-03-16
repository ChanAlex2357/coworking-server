package itu.jca.eval.test.coworking.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import itu.jca.eval.test.coworking.api.results.EspaceCrenauxResponse;
import itu.jca.eval.test.coworking.dto.models.EspaceCreneauDto;
import itu.jca.eval.test.coworking.models.EspaceCreneaux;
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


    public void findCreneaux(Date dateReservation , EspaceCreneaux espaceCreneaux){
        List<EspaceCreneauDto> creneaux = espaceRepository.findCreneaux(dateReservation,espaceCreneaux.getEspace().getNom());
        espaceCreneaux.setCreneaux(creneaux);
    }
    public EspaceCreneaux findCreneaux(Date dateReservation , Espace espace){
        EspaceCreneaux espaceCreneaux = new EspaceCreneaux(espace);
        findCreneaux(dateReservation,espaceCreneaux);
        return espaceCreneaux;
        
    }

    public EspaceCreneaux[] findAllEspaceCrenaux(Date dateReservation){
        List<Espace> espaces = findAll();
        EspaceCreneaux[] espaceCreneauxs  = new EspaceCreneaux[espaces.size()];
        int index = 0;
        for(Espace e : espaces) {
            espaceCreneauxs[index] = findCreneaux(dateReservation, e);
            index+=1;            
        }
        return espaceCreneauxs;
    }
    public void findAllEspaceCrenaux(EspaceCrenauxResponse espaceCrenauxResponse){
        espaceCrenauxResponse.setEspaceCreneauxs(
            findAllEspaceCrenaux(espaceCrenauxResponse.getDateReservation())
        );
    }
} 