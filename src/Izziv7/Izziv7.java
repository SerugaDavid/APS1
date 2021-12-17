package APS1.Izziv7;

import java.util.Scanner;

public class Izziv7 {
    public static int M = 0;
    public static int C = 0;
    public static int vstavljanje = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Vnesi velikost:");
        int velikost = Integer.parseInt(sc.nextLine());
        Sequence<Oseba> osebe = ustvari(velikost);
        boolean ponovi = true;
        int atr, smer;
        while (ponovi) {
            System.out.println("Vnesi atribut: ime(0), priimek(1), letnica rojstva(2)");
            atr = sc.nextInt();
            System.out.println("Vnesi smer: naraščajoče(1), padajoče(-1)");
            smer = sc.nextInt();
            Oseba.setAtr(atr);
            Oseba.setSmer(smer);
            System.out.println(toStr(osebe));
            System.out.println("Pri kateri velikosti zaporeja naj se aktivira navadno vstavljanje?");
            vstavljanje = sc.nextInt();
            osebe = quickSort(osebe, 0, osebe.size()-1);
            System.out.println(toStr(osebe));
            System.out.println(stats());
            System.out.println("Ponovi: da(1), ne(0)");
            ponovi = 1 == sc.nextInt();
            M = 0;
            C = 0;
        }
    }

    public static String stats(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ Primerjave: ");
        sb.append(C);
        sb.append(", Premiki: ");
        sb.append(M);
        sb.append(" ]");
        return sb.toString();
    }

    public static Sequence<Oseba> ustvari(int velikost){
        Sequence<Oseba> osebe = new LinkedSequence<>();
        for (int i = 0; i < velikost; i++){
            osebe.add(new Oseba());
        }
        return osebe;
    }

    public static String toStr(Sequence<Oseba> osebe){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        if (osebe.size() != 0)
            try {
                sb.append(osebe.get(0).get());
            } catch (CollectionException ignored){}
        for (int i = 1; i < osebe.size(); i++){
            sb.append(", ");
            try {
                sb.append(osebe.get(i).get());
            } catch (CollectionException ignored){}
        }
        sb.append(" ]");
        return sb.toString();
    }

    public static Sequence<Oseba> quickSort(Sequence<Oseba> zaporedje, int left, int right){
        if (right - left < vstavljanje)
            return straightInsertion(zaporedje, left, right);
        Par items = partition(zaporedje, left, right);
        zaporedje = quickSort(items.getZaporedje(), left, items.getIndeks()-1);
        zaporedje = quickSort(zaporedje, items.getIndeks()+1, right);
        return zaporedje;
    }

    public static Par partition(Sequence<Oseba> zaporedje, int left, int right){
        int l = left + 1;
        int r = right;
        Oseba temp;
        int smer = Oseba.getSmer();
        while (l <= r){
            try{
                while (l < right+1 && zaporedje.get(l).compareTo(zaporedje.get(left))*smer < 0) {
                    l++;
                    C++;
                }
                while (r >= left && zaporedje.get(r).compareTo(zaporedje.get(left))*smer > 0) {
                    r--;
                    C++;
                }
                if (l > r)
                    break;
                temp = zaporedje.get(l);
                zaporedje.set(l, zaporedje.get(r));
                zaporedje.set(r, temp);
                M+=3;
                l++;
                r--;
            } catch (CollectionException ignored){}
        }
        try {
            temp = zaporedje.get(left);
            zaporedje.set(left, zaporedje.get(r));
            zaporedje.set(r, temp);
            M+=3;
        } catch (CollectionException ignored){}
        return new Par(zaporedje, r);
    }

    public static Sequence<Oseba> straightInsertion(Sequence<Oseba> zaporedje, int left, int right){
        if (right-left < 1)
            return zaporedje;
        int j, smer;
        Oseba k;
        smer = Oseba.getSmer();
        for (int i = left+1; i <= right; i++){
            try {
                k = zaporedje.get(i);
                M++;
                j = i;
                while (j > left && zaporedje.get(j-1).compareTo(k)*smer > 0){
                    C++;
                    zaporedje.set(j, zaporedje.get(j-1));
                    M++;
                    j--;
                }
                zaporedje.set(j, k);
                M++;
            } catch (CollectionException ignored){}
        }
        return zaporedje;
    }
}
