package APS1.Izziv2;

import java.util.Random;

public class Izziv2 {
    static class Oseba{
        private String ime, priimek;
        private int letoR;
        private final String[] imena = {"David", "Ana", "Dominik", "Monika", "Matevž", "Ajda", "Lovro", "Klara"};
        private final String[] priimki = {"Novak", "Dobravec", "Demšar", "Orel", "Mihelič", "Zalar"};

        public Oseba(){
            Random rn = new Random();
            this.ime = this.imena[rn.nextInt(this.imena.length)];
            this.priimek = this.priimki[rn.nextInt(this.priimki.length)];
            this.letoR = 1920+(rn.nextInt(100));
        }

        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("{").append(this.ime).append(" ").append(this.priimek).append(", ").append(this.letoR).append("}");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Sequence<Oseba> zaporedje = new LinkedSequence<>();
        dodajOsebe(zaporedje, 10);
        vrini(zaporedje, 4);
        pridobi(zaporedje, 6);
        nastavi(zaporedje, 5);
        izbrisi(zaporedje, 10);
    }

    public static void dodajOsebe(Sequence<Oseba> zaporedje, int stevilo){
        for (int i = 0; i < stevilo; i++){
            try{
                zaporedje.add(new Oseba());
            }
            catch (CollectionException e){
                System.out.println(e);
            }
        }
        System.out.println(zaporedje.toString());
    }

    public static void vrini(Sequence<Oseba> zaporedje, int indeks){
        try {
            zaporedje.insert(indeks, new Oseba());
        }
        catch (CollectionException e){
            System.out.println(e);
        }
        System.out.println(zaporedje.toString());
    }

    public static void pridobi(Sequence<Oseba> zaporedje, int indeks){
        try {
            System.out.println(zaporedje.get(indeks));
        }
        catch (CollectionException e){
            System.out.println(e);
        }
    }

    public static void nastavi(Sequence<Oseba> zaporedje, int indeks){
        try {
            System.out.println(zaporedje.set(indeks, new Oseba()));
        }
        catch (CollectionException e){
            System.out.println(e);
        }
        System.out.println(zaporedje.toString());
    }

    public static void izbrisi(Sequence<Oseba> zaporedje, int indeks){
        try {
            System.out.println(zaporedje.delete(indeks));
        }
        catch (CollectionException e){
            System.out.println(e);
        }
        System.out.println(zaporedje.toString());
    }
}
