package APS1.Izziv6;

import java.util.Random;

public class Oseba implements Comparable{
    private String ime, priimek;
    private int letoR;
    private final String[] imena = {"David", "Ana", "Dominik", "Monika", "Matevž", "Ajda", "Lovro", "Klara"};
    private final String[] priimki = {"Novak", "Dobravec", "Demšar", "Orel", "Mihelič", "Zalar"};
    private static int atr, smer;

    public Oseba(){
        Random rn = new Random();
        this.ime = this.imena[rn.nextInt(this.imena.length)];
        this.priimek = this.priimki[rn.nextInt(this.priimki.length)];
        this.letoR = 1910+(rn.nextInt(110));
        atr = 0;
        smer = 1;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{").append(this.ime).append(" ").append(this.priimek).append(", ").append(this.letoR).append("}");
        return sb.toString();
    }

    @Override
    public int compareTo(Oseba o) {
        return this.get().compareTo(o.get());
    }

    // Atributi
    public static void setAtr(int atr) {
        Oseba.atr = atr;
    }

    public static int getSmer() {
        return smer;
    }

    public static void setSmer(int smer) {
        Oseba.smer = smer;
    }

    // Geterji
    public String get() {
        switch (atr){
            case 0:
                return this.ime;
            case 1:
                return this.priimek;
            case 2:
                return Integer.toString(this.letoR);
            default:
                return "";
        }
    }
}
