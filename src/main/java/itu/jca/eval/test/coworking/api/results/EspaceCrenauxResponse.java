package itu.jca.eval.test.coworking.api.results;

import java.sql.Date;

import itu.jca.eval.test.coworking.models.EspaceCreneaux;
import lombok.Data;

@Data
public class EspaceCrenauxResponse {
    Date dateReservation;
    EspaceCreneaux[] espaceCreneauxs;
    Exception exception;
    public EspaceCrenauxResponse(Date date){
        setDateReservation(date);
    }
}
