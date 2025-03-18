package itu.jca.eval.test.coworking.dto.models;

import java.sql.Date;
import java.sql.Time;

public interface EspaceCreneauDto {
    String getId();
    Time getHeuredebut();
    Time getHeurefin();
    Date getDatereservation();
    String getEspace();
    String getEtat();
}