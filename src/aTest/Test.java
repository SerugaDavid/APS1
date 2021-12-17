package APS1.aTest;

import java.util.regex.Matcher;
import static java.lang.Math.log10;

public class Test {
    public static void main(String[] args) {
        /*int[] elements = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] coX = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        int[][] koordinate = new int[2][10];
        GetCordinates(elements, coX, koordinate);
        for (int i = 0; i < 10; i++){
            System.out.printf("%d| %d, %d\n", i, koordinate[1][i], koordinate[0][i]);
        }*/

    }

    public static void GetCordinates (int[] elements, int[] coX,int[][] cordinates) {
        for (int i = 0; i < elements.length; i++) {
            cordinates[1][i]=(int)(Math.log(i + 1)/Math.log(2));
        }
        cordinates[0]= GetXcordinates(0,coX);
    }

    public static int EmptyOne (int[] coX) {
        int counter = 0;
        for (int i:coX){
            if (i != -1)
                counter++;
        }
        return counter;
    }

    public static int[] GetXcordinates (int i, int[] coX) {
        int left = 2*i + 1;
        if (left >= coX.length){
            coX[i] = EmptyOne(coX);
            return coX;
        }
        GetXcordinates (left, coX);
        int in = EmptyOne(coX);
        coX[i] = in;
        int right = left + 1;
        if (right < coX.length){
            GetXcordinates (right, coX);
        }
        return coX;

    }

}
