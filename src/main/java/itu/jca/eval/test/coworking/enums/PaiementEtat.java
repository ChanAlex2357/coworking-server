package itu.jca.eval.test.coworking.enums;

public enum PaiementEtat {
   PAYER(10),
   VALIDER(11);
   
   private final int etat;
   
   PaiementEtat( int e) {
    this.etat = e;
   }

   public int getEtat() {
       return etat;
   }
}
