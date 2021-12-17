package APS1.Izziv4;

import java.util.ArrayList;
import java.util.List;

public class Izziv4 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        char[] elementi = ustvariElemente(n);
        int[][] koordinate = ustvariKoordinate(n);
        //tukaj naredi risalno površino kar razred CompleteBinaryTreeDrawer
        CompleteBinaryTreeDrawer drevo = new CompleteBinaryTreeDrawer(elementi, koordinate, log2(n), n);
        long time = System.nanoTime();
        drevo.pobrisi();
        drevo.drawLevelorder();
        System.out.printf("%d\n", (System.nanoTime() - time));
        time = System.nanoTime();
        drevo.pobrisi();
        drevo.drawPreorder(0);
        System.out.printf("%d\n", (System.nanoTime() - time));
        time = System.nanoTime();
        drevo.pobrisi();
        drevo.drawInorder(0);
        System.out.printf("%d\n", (System.nanoTime() - time));
        time = System.nanoTime();
        drevo.pobrisi();
        drevo.drawPostorder(0);
        System.out.printf("%d\n", (System.nanoTime() - time));
    }

    public static char[] ustvariElemente(int n){
        char[] elementi = new char[n];
        for (int i = 0; i < n; i++){
            elementi[i] = (char)(i+65);
        }
        return elementi;
    }

    public static int[][] ustvariKoordinate(int n){
        int[][] koordinate = new int[2][n];
        // določi y
        for (int i = 0; i < n; i++){
            koordinate[1][i] = log2(i+1);
        }
        // določi x
        int[] x = prazen(n);
        x = dolociX(0, 0, x);
        List<Integer> xi = new ArrayList<Integer>(x.length);
        for (int i:x){
            xi.add(i);
        }
        int temp;
        for (int i = 0; i < n; i++){
            temp = xi.indexOf(i);
            koordinate[0][i] = temp;
        }
        // return
        return koordinate;
}

    public static int[] dolociX(int i, int dalje, int[] xi){
        int levi = 2*i + 1;
        if (levi >= xi.length){
            xi[dalje] = i;
            return xi;
        }
        xi = dolociX(levi, dalje, xi);
        int indeks = nezaseden(xi);
        xi[indeks] = i;
        int desni = levi + 1;
        if (desni < xi.length)
            xi = dolociX(desni, indeks+1, xi);
        return xi;
    }

    public static int nezaseden(int[] xi){
        for (int i = 0; i < xi.length; i++){
            if (xi[i] == -1)
                return i;
        }
        return xi.length;
    }

    public static int[] prazen(int n){
        int[] x = new int[n];
        for (int i = 0; i < n; i++)
            x[i] = -1;
        return x;
    }

    public static int log2(int x){
        double log = (Math.log(x) / Math.log(2));
        return (int) Math.floor(log);
    }
}
