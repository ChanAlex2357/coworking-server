package itu.jca.eval.test.coworking.enums;

public enum ReservationEtat {
    RESERVER(10),
    VALIDER(11),
    PAYER(12),
    VALIDER_PAIEMENT(13),
    ANNULER(14);
    
    private final int etat;
    private ReservationEtat(int etat){
        this.etat = etat;
    }
    public int getEtat() {
        return etat;
    }
}
