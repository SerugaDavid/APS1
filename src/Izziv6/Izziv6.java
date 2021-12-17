package APS1.Izziv6;

import java.util.Scanner;

public class Izziv6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Vnesi velikost:");
        int velikost = Integer.parseInt(sc.nextLine());
        Oseba[] osebe = ustvari(velikost);
        boolean ponovi = true;
        int atr, smer;
        while (ponovi) {
            System.out.println("Vnesi atribut: ime(0), priimek(1), letnica rojstva(2)");
            atr = Integer.parseInt(sc.nextLine());
            System.out.println("Vnesi smer: naraščajoče(1), padajoče(-1)");
            smer = Integer.parseInt(sc.nextLine());
            Oseba.setAtr(atr);
            Oseba.setSmer(smer);
            System.out.println(toStr(osebe, 0));
            bubblesort1(osebe);
            System.out.println("Ponovi: da(1), ne(0)");
            ponovi = 1 == Integer.parseInt(sc.nextLine());
        }
    }

    public static Oseba[] ustvari(int velikost){
        Oseba[] seznam = new Oseba[velikost];
        for (int i = 0; i < velikost; i++)
            seznam[i] = new Oseba();
        return seznam;
    }

    public static void bubblesort1(Oseba[] a){
        int smer = Oseba.getSmer();
        int start = 0;
        int end = a.length-1;
        if (smer == -1){
            start = end;
            end = 0;
        }
        int premiki;
        Oseba temp;
        while (start != end) {
            premiki = 0;
            for (int i = start; i != end; i += smer) {
                if (a[i].compareTo(a[i+smer]) > 0){
                    temp = a[i];
                    a[i] = a[i+smer];
                    a[i+smer] = temp;
                    premiki = 0;
                }
                else
                    premiki++;
            }
            end -= smer * premiki;
            if (smer == -1)
                System.out.println(toStr(a, end+1));
            else
                System.out.println(toStr(a, end));
        }
    }

    public static String toStr(Oseba[] seznam, int end){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        if (seznam.length != 0)
            sb.append(seznam[0].get());
        for (int i = 1; i < seznam.length; i++){
            if (i == end)
                sb.append(" | ");
            else
                sb.append(", ");
            sb.append(seznam[i].get());
        }
        sb.append(" ]");
        return sb.toString();
    }
}
