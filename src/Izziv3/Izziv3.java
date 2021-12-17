package APS1.Izziv3;

import java.util.Random;

public class Izziv3 {
    public static void main(String[] args) {
        izpis();
    }

    public static int[] generateTable(int n){
        int[] tabela = new int[n];
        for (int i = 1; i <= n; i++){
            tabela[i-1] = i;
        }
        return tabela;
    }

    public static int findLinear(int[] a, int v){
        for (int i = 0; i < a.length; i++){
            if (v == a[i])
                return i;
        }
        return -1;
    }

    public static int findBinary(int[] a, int l, int r, int v){
        int sredina = (int) Math.ceil(l/2.0 + r/2.0);
        if (v == a[sredina])
            return sredina;
        if (v < a[sredina])
            return findBinary(a, l, sredina, v);
        return findBinary(a, sredina, r, v);
    }

    public static long timeLinear(int n){
        int[] tabela = generateTable(n);
        long time = System.nanoTime();
        for (int i = 0; i < 1000; i++){
            int random = new Random().nextInt(n)+1;
            if (findLinear(tabela, random) != random-1)
                System.out.println("napaka");
        }
        time = (System.nanoTime() - time) / 1000;
        return time;
    }

    public static long timeBinary(int n){
        int[] tabela = generateTable(n);
        long time = System.nanoTime();
        for (int i = 0; i < 1000; i++){
            int random = new Random().nextInt(n)+1;
            if (findBinary(tabela, 0, n-1, random) != random-1)
                System.out.println("napaka");
        }
        time = (System.nanoTime() - time) / 1000;
        return time;
    }

    public static void izpis(){
        System.out.println("  n       |      linearno |      dvojisko |  ");
        System.out.println("----------+---------------+---------------+--");
        for (int i = 100000; i <= 1000000; i += 10000){
            System.out.printf("%9d | %13d | %13d |  \n", i, timeLinear(i), timeBinary(i));
        }
    }
}
