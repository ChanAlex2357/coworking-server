package itu.jca.eval.test.coworking.utils;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeUtils {
    public static Time formatTime(String timeStr) {
        try {
            // D'abord essayer de parser avec le format HH:mm:ss
            DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime heure;
            try {
                heure = LocalTime.parse(timeStr, fullFormatter);
            } catch (DateTimeParseException e) {
                // Si échec, essayer avec le format HH:mm et ajouter les secondes
                DateTimeFormatter shortFormatter = DateTimeFormatter.ofPattern("HH:mm");
                heure = LocalTime.parse(timeStr, shortFormatter);
                heure = LocalTime.of(heure.getHour(), heure.getMinute(), 0);
            }
            return Time.valueOf(heure);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Format de temps invalide : " + timeStr);
        }
    }

    public static Date formatDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return Date.valueOf(LocalDate.parse(date, formatter));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Format de date invalide. Utilisez le format dd/MM/yyyy : " + date);
        }
    }

    public static Time add(Time time , int hour,int min , int sec) {
        LocalTime newTime = time.toLocalTime();
        newTime = newTime.plusHours(hour);
        newTime = newTime.plusMinutes(min);
        newTime = newTime.plusSeconds(sec);
        return Time.valueOf(newTime);
    }
}
