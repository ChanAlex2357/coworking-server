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
import jakarta.transaction.Transactional;

@Service
@Transactional
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

    public ReservationOption createReservationOption(Option option,Reservation reservation) {
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
    
    public ReservationOption createReservationOption(String optionId , Reservation reservation) {
        String id = optionId.trim().toUpperCase();
        Option option = optionService.findById(id);
        return createReservationOption(option, reservation);
    }

    public ReservationOption[] createReservationOptions(Option[] options , Reservation reservation){
        ReservationOption[] reservationOptions = new ReservationOption[options.length];
        for (int i = 0 ; i < options.length ; i++) {
            reservationOptions[i] = createReservationOption(options[i], reservation);
        }
        return reservationOptions;
    }
    
    public ReservationOption[] createReservationOptions(String[] options , Reservation reservation){
        ReservationOption[] reservationOptions = new ReservationOption[options.length];
        for (int i = 0 ; i < options.length ; i++) {
            reservationOptions[i] = createReservationOption(options[i], reservation);
        }
        return reservationOptions;
    }
    
    public ReservationOption[] loadReservationOptions(String optionsStr,Reservation reservation) {
        // Traitement des options
        ReservationOption[] reservationOptions = null;
        if (!optionsStr.isEmpty() && !optionsStr.equals("\"\"")) {
            String[] optionIds = optionsStr.replace("\"", "").split(",");
            System.out.println("OPTIONS LENGTH : "+optionIds.length);
            reservationOptions = createReservationOptions(optionIds, reservation);
        }
        return reservationOptions;
    }
} 