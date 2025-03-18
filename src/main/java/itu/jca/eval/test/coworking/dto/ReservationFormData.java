package itu.jca.eval.test.coworking.dto;

import java.sql.Date;

import itu.jca.eval.test.coworking.dto.models.OpitionSelectData;
import lombok.Data;

@Data
public class ReservationFormData {
    String userId;
    String espaceName;
    Date dateReservation;
    int duree;
    String heureDebut;
    OpitionSelectData[] options;
}
