package itu.jca.eval.test.coworking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.jca.eval.test.coworking.models.ReservationOption;
import itu.jca.eval.test.coworking.models.Option;
import itu.jca.eval.test.coworking.models.PrixOption;
import itu.jca.eval.test.coworking.models.Reservation;
import itu.jca.eval.test.coworking.repository.ReservationOptionRepository;

@Service
public class ReservationOptionService {
    @Autowired
    private OptionService optionService;
    @Autowired
    private PrixOptionService prixOptionService;
    @Autowired
    private ReservationOptionRepository reservationOptionRepository;

    public List<ReservationOption> findAll() {
        return reservationOptionRepository.findAll();
    }

    public Optional<ReservationOption> findById(String id) {
        return reservationOptionRepository.findById(id);
    }

    public List<ReservationOption> findByReservation(Reservation reservation) {
        return reservationOptionRepository.findByReservation(reservation);
    }

    public ReservationOption save(ReservationOption reservationOption) {
        return reservationOptionRepository.save(reservationOption);
    }

    public void deleteById(String id) {
        reservationOptionRepository.deleteById(id);
    }

    public ReservationOption update(String id, ReservationOption reservationOption) {
        if (reservationOptionRepository.existsById(id)) {
            reservationOption.setId(id);
            return reservationOptionRepository.save(reservationOption);
        }
        throw new RuntimeException("Option de réservation non trouvée avec l'id: " + id);
    }

    public ReservationOption save(Option option,Reservation reservation) throws Exception {
        PrixOption prixOption = prixOptionService.findCurrentOptionPrix(option);
        ReservationOption resOption = new ReservationOption();
        resOption.setOption(option);
        resOption.setReservation(reservation);
        resOption.setPu(prixOption.getPu());
        save(resOption);
        // Mise à jour du montant total
        reservation.setMontant(reservation.getMontant()+ prixOption.getPu());
        return resOption;
    }
    
    public ReservationOption[] loadReservationOptions(String optionsStr,Reservation reservation) throws Exception {
        // Traitement des options
        ReservationOption[] reservationOptions = null;
        if (!optionsStr.isEmpty() && !optionsStr.equals("\"\"")) {
            String[] optionIds = optionsStr.replace("\"", "").split(",");
            reservationOptions = new ReservationOption[optionIds.length];
            System.out.println("OPTIONS LENGTH : "+optionIds.length);
            for (int i = 0; i < optionIds.length; i+=1) {
                String optionId = optionIds[i].trim().toUpperCase();
                Option option = 
                    optionService.findById(optionId)
                    .orElseThrow(() -> new RuntimeException("Option non trouvée: " + optionId));
                reservationOptions[i] = save(option,reservation);
            }
        }
        return reservationOptions;
    }
} 