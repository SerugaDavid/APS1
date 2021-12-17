package APS1.Naloga2;

import java.util.Scanner;
import static java.lang.Math.log10;

public class Naloga2 {

    static int c;
    static int m;
    static int max;

    public static void main(String[] args) {
        Scanner v, n;
        v = new Scanner(System.in);
        int size = 0;
        int[] seznam = new int[10];
        int[] ukazi = new int[3];
        String vrstica;
        vrstica = v.nextLine();
        n = new Scanner(vrstica);
        for (int i = 0; i < 3; i++){
            ukazi[i] = koda(i, n.next());
        }
        vrstica = v.nextLine();
        n = new Scanner(vrstica);
        while (n.hasNext()){
            seznam = append(seznam, n.nextInt(), size);
            size++;
        }
        seznam = koncni(seznam, size);
        sort(ukazi, seznam);
    }

    public static int[] koncni(int[] seznam, int size){
        int[] nov = new int[size];
        for (int i = 0; i < size; i++){
            nov[i] = seznam[i];
        }
        return nov;
    }

    public static int[] append(int[] seznam, int element, int size){
        if (size == seznam.length) {
            int[] nov = new int[size * 2];
            for (int i = 0; i < seznam.length; i++){
                nov[i] = seznam[i];
            }
            nov[size] = element;
            return nov;
        }
        seznam[size] = element;
        return seznam;
    }

    public static int koda(int indeks, String ukaz){
        switch (indeks){
            case 0:
                if (ukaz.equals("trace"))
                    return 0;
                return 1;
            case 1:
                switch (ukaz){
                    case "insert":
                        return 0;
                    case "select":
                        return 1;
                    case "bubble":
                        return 2;
                    case "heap":
                        return 3;
                    case "merge":
                        return 4;
                    case "quick":
                        return 5;
                    case "radix":
                        return 6;
                    case "bucket":
                        return 7;
                }
                return 8;
            case 2:
                if (ukaz.equals("up"))
                    return 1;
                return -1;
        }
        return -10;
    }

    public static String toStrNavadno(int[] seznam, int end, int l, int r, boolean quick){
        StringBuilder sb = new StringBuilder();
        for (int i = l; i < r; i++){
            if ((i == end) || (quick && i == end+1))
                sb.append("| ");
            sb.append(seznam[i]);
            sb.append(" ");
        }
        if (end == r || (quick && end+1 == r))
            sb.append("| ");
        return sb.toString();
    }

    public static String toStrBuck(int[] seznam, int[] end){
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < seznam.length; i++){
            if (end.length > count && i == end[count]) {
                sb.append("| ");
                count++;
            }
            sb.append(seznam[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void reset(){
        c = 0;
        m = 0;
    }

    public static void sort(int[] ukazi, int[] seznam){
        int nacin = ukazi[0], smer = ukazi[2];
        Stats stetje = new Stats();
        if (nacin == 0)
            System.out.println(toStrNavadno(seznam, -1, 0, seznam.length, false));
        reset();
        switch (ukazi[1]){
            case 0:
                seznam = insert(nacin == 0, smer, seznam);
                if (nacin == 1){
                    stetje.append(m, c);
                    reset();
                    seznam = insert(false, smer, seznam);
                    stetje.append(m, c);
                    reset();
                    insert(false, smer*-1, seznam);
                    stetje.append(m, c);
                    System.out.println(stetje.toString());
                }
                break;
            case 1:
                seznam = select(nacin == 0, smer, seznam);
                if (nacin == 1){
                    stetje.append(m, c);
                    reset();
                    seznam = select(false, smer, seznam);
                    stetje.append(m, c);
                    reset();
                    select(false, smer*-1, seznam);
                    stetje.append(m, c);
                    System.out.println(stetje.toString());
                }
                break;
            case 2:
                seznam = bubble1(nacin == 0, smer, seznam);
                if (nacin == 1){
                    stetje.append(m, c);
                    reset();
                    seznam = bubble1(false, smer, seznam);
                    stetje.append(m, c);
                    reset();
                    bubble1(false, smer*-1, seznam);
                    stetje.append(m, c);
                    System.out.println(stetje.toString());
                }
                break;
            case 3:
                seznam = heap(nacin == 0, smer, seznam);
                if (nacin == 1){
                    stetje.append(m, c);
                    reset();
                    seznam = heap(false, smer, seznam);
                    stetje.append(m, c);
                    reset();
                    heap(false, smer*-1, seznam);
                    stetje.append(m, c);
                    System.out.println(stetje.toString());
                }
                break;
            case 4:
                seznam = merge(nacin == 0, smer, seznam, 0, seznam.length);
                if (nacin == 1){
                    stetje.append(m,c);
                    reset();
                    seznam = merge(false, smer, seznam, 0, seznam.length);
                    stetje.append(m,c);
                    reset();
                    merge(false, smer*-1, seznam, 0, seznam.length);
                    stetje.append(m,c);
                    System.out.println(stetje.toString());
                }
                break;
            case 5:
                seznam = quick(nacin == 0, smer, seznam, 0, seznam.length);
                if (nacin == 1){
                    stetje.append(m,c);
                    reset();
                    seznam = quick(false, smer, seznam, 0, seznam.length);
                    stetje.append(m,c);
                    reset();
                    quick(false, smer*-1, seznam, 0, seznam.length);
                    stetje.append(m,c);
                    System.out.println(stetje.toString());
                }
                else
                    System.out.println(toStrNavadno(seznam, -1, 0, seznam.length, false));
                break;
            case 6:
                seznam = radix(nacin == 0, smer, seznam);
                if (nacin == 1){
                    stetje.append(m,c);
                    reset();
                    seznam = radix(false, smer, seznam);
                    stetje.append(m,c);
                    reset();
                    radix(false, smer*-1, seznam);
                    stetje.append(m,c);
                    System.out.println(stetje.toString());
                }
                break;
            case 7:
                seznam = bucket(nacin == 0, smer, seznam);
                if (nacin == 1){
                    stetje.append(m,c+seznam.length-4);
                    reset();
                    seznam = bucket(false, smer, seznam);
                    stetje.append(m,c);
                    reset();
                    bucket(false, smer*-1, seznam);
                    stetje.append(m,c);
                    System.out.println(stetje.toString());
                }
                break;
        }
    }

    public static int[] insert(boolean nacin, int smer, int[] seznam){
        if (seznam.length <= 1){
            if (nacin)
                System.out.println(toStrNavadno(seznam, -1, 0, seznam.length, false));
            return seznam;
        }
        int j, k;
        boolean temp;
        for (int i = 1; i < seznam.length; i++){
            k = seznam[i];
            m++;
            j = i;
            temp = j > 0;
            while (temp && seznam[j-1]*smer > k*smer){
                seznam[j] = seznam[j-1];
                m++;
                j--;
                c++;
                temp = j > 0;
            }
            if (temp)
                c++;
            seznam[j] = k;
            m++;
            if (nacin)
                System.out.println(toStrNavadno(seznam, i+1, 0, seznam.length, false));
        }
        return seznam;
    }

    public static int[] select(boolean nacin, int smer, int[] seznam){
        int k, temp;
        for (int i = 0; i < seznam.length-1; i++){
            k = i;
            for (int j = i+1; j < seznam.length; j++){
                c++;
                if (seznam[j]*smer < seznam[k]*smer)
                    k = j;
            }
            temp = seznam[k];
            seznam[k] = seznam[i];
            seznam[i] = temp;
            m+=3;
            if (nacin)
                System.out.println(toStrNavadno(seznam, i+1, 0, seznam.length, false));
        }
        return seznam;
    }

    public static int[] bubble1(boolean nacin, int smer, int[] seznam){
        int end, temp, menjave;
        end = 0;
        while (seznam.length-1 != end){
            menjave = 0;
            for (int i = seznam.length-1; i > end; i--){
                c++;
                if (seznam[i]*smer < seznam[i-1]*smer){
                    temp = seznam[i];
                    seznam[i] = seznam[i-1];
                    seznam[i-1] = temp;
                    m+=3;
                    menjave = 1;
                }
                else
                    menjave++;
            }
            end += menjave;
            if (nacin)
                System.out.println(toStrNavadno(seznam, end, 0, seznam.length, false));
        }
        return seznam;
    }

    private static int[] reorder(int[] seznam, int start, int end){
        Par ret = new Par(seznam, start);
        while (ret.getIndeks() != -1){
            ret = shiftDown(ret.getIndeks(), ret.getSeznam(), end);
        }
        return ret.getSeznam();
    }

    public static Par shiftDown(int i, int[] seznam, int end){
        int levi = 2*i + 1;
        int desni = levi + 1;
        boolean trenutni;
        if (levi < end){
            if (desni < end){
                trenutni = seznam[desni]*max > seznam[levi]*max;
                if (trenutni && seznam[desni]*max > seznam[i]*max){
                    c+=2;
                    int temp = seznam[i];
                    seznam[i] = seznam[desni];
                    seznam[desni] = temp;
                    m+=3;
                    return new Par(seznam, desni);
                }
                c++;
            }
            c++;
            if (seznam[levi]*max > seznam[i]*max) {
                int temp = seznam[i];
                seznam[i] = seznam[levi];
                seznam[levi] = temp;
                m+=3;
                return new Par(seznam, levi);
            }
        }
        return new Par(seznam, -1);
    }

    public static int[] kopica(int[] seznam){
        int zadStars = (seznam.length-2)/2;
        for (int i = zadStars; i >= 0; i--){
            seznam = reorder(seznam, i, seznam.length);
        }
        return seznam;
    }

    public static int[] heap(boolean nacin, int smer, int[] seznam){
        max = smer;
        seznam = kopica(seznam);
        int temp;
        for (int i = seznam.length-1; i >= 0; i--){
            if (nacin)
                System.out.println(toStrNavadno(seznam, i+1, 0, seznam.length, false));
            temp = seznam[i];
            seznam[i] = seznam[0];
            seznam[0] = temp;
            m+=3;
            seznam = reorder(seznam, 0, i);
        }
        m-=3;
        return seznam;
    }

    public static int[] merge(boolean nacin, int smer, int[] seznam, int left, int right){
        if (right - left <= 1)
            return seznam;
        int polovica = (int)Math.ceil((right-left)/2.0)+left;
        if (nacin)
            System.out.println(toStrNavadno(seznam, polovica, left, right, false));
        seznam = merge(nacin, smer, seznam, left, polovica);
        seznam = merge(nacin, smer, seznam, polovica, right);
        return zdruzi(nacin, smer, seznam, left, polovica, right);
    }

    public static int[] zdruzi(boolean nacin, int smer, int[] seznam, int left, int middle, int right){
        int[] zdruzeni = new int[right-left];
        int l1 = left;
        int l2 = middle;
        int last = 0;
        for (int i = 0; middle-l1 != 0 && right-l2 != 0; i++){
            c++;
            m++;
            if (seznam[l1]*smer <= seznam[l2]*smer){
                zdruzeni[i] = seznam[l1];
                l1++;
            }
            else {
                zdruzeni[i] = seznam[l2];
                l2++;
            }
            last = i;
        }
        for (int i = last+1; middle-l1 != 0; i++){
            zdruzeni[i] = seznam[l1];
            m++;
            l1++;
        }
        for (int i = last+1; right-l2 != 0; i++){
            zdruzeni[i] = seznam[l2];
            m++;
            l2++;
        }
        if (nacin)
            System.out.println(toStrNavadno(zdruzeni, -1, 0, zdruzeni.length, false));
        for (int i = 0; i < zdruzeni.length; i++){
            seznam[i+left] = zdruzeni[i];
            m++;
        }
        return seznam;
    }

    public static int[] quick(boolean nacin, int smer, int[] seznam, int left, int right){
        if (right-left <= 1)
            return seznam;
        Par rez = partition(smer, seznam, left, right);
        if (nacin)
            System.out.println(toStrNavadno(seznam, rez.getIndeks(), left, right, true));
        seznam = quick(nacin, smer, rez.getSeznam(), left, rez.getIndeks());
        seznam = quick(nacin, smer, seznam, rez.getIndeks()+1, right);
        return seznam;
    }

    public static Par partition(int smer, int[] seznam, int left, int right){
        int l, r, temp;
        l = left+1;
        r = right-1;
        while (l <= r){
            while (l < right && seznam[l]*smer < seznam[left]*smer){
                l++;
                c++;
            }
            while (r >= left && seznam[r]*smer > seznam[left]*smer) {
                r--;
                c++;
            }
            c++;
            if (l > r)
                break;
            temp = seznam[l];
            seznam[l] = seznam[r];
            seznam[r] = temp;
            m+=3;
            l++;
            r--;
        }
        temp = seznam[left];
        seznam[left] = seznam[r];
        seznam[r] = temp;
        m+=3;
        return new Par(seznam, r);
    }

    public static int maks(int[] seznam){
        int max, maxp;
        max = 0;
        for (int i:seznam){
            maxp = (int) Math.floor(log10(i)) + 1;
            if (max < maxp)
                max = maxp;
        }
        return max;
    }

    public static int[] radix(boolean nacin, int smer, int[] seznam){
        int nar = 0;
        if (smer == -1)
            nar = 9;
        int m1 = maks(seznam);
        int dolzina = seznam.length;
        int stevilka, mesto;
        int[] temp = new int[dolzina];
        int[] c1;
        for (int k = 0; k < m1; k++){
            c1 = new int[10];
            for (int i:seznam){
                stevilka = (i / (int) Math.pow(10, k)) % 10;
                c1[(stevilka-nar)*smer] += 1;
            }
            for (int i = 1; i < 10; i++){
                c1[i] += c1[i-1];
            }
            for (int i = dolzina-1; i >= 0; i--){
                stevilka = (seznam[i] / (int) Math.pow(10, k)) % 10;
                mesto = --c1[(stevilka-nar)*smer];
                temp[mesto] = seznam[i];
            }
            seznam = koncni(temp, dolzina);
            if (nacin)
                System.out.println(toStrNavadno(seznam, -1, 0, dolzina, false));
        }
        c = 2 * dolzina * m1;
        m = c;
        return seznam;
    }

    public static int[] minMax(int[] seznam){
        int min = seznam[0];
        int max = seznam[0];
        for (int i:seznam){
            if (i > max)
                max = i;
            if (i < min)
                min = i;
        }
        return new int[]{min, max};
    }

    public static int[] bucket(boolean nacin, int smer, int[] seznam){
        int k = seznam.length/2;
        int[] minmax = minMax(seznam);
        double v = (minmax[1]-minmax[0]+1)/(double)k;
        int[] size = new int[k];
        int kos, mesto;
        int nar = 0;
        if (smer == -1)
            nar = k-1;
        for (int i:seznam){
            kos = ((int)Math.floor((i-minmax[0])/v)-nar)*smer;
            c+=2;
            m++;
            size[kos]++;
        }
        for (int i = 1; i < k; i++){
            size[i] += size[i-1];
        }
        int[] temp = new int[seznam.length];
        for (int i = seznam.length-1; i >= 0; i--){
            kos = ((int)Math.floor((seznam[i]-minmax[0])/v)-nar)*smer;
            c++;
            mesto = --size[kos];
            temp[mesto] = seznam[i];
            m++;
        }
        if (nacin)
            System.out.println(toStrBuck(temp, size));
        seznam = insert(nacin, smer, temp);
        return seznam;
    }
}

class Stats {
    private int[] primerjave;
    private int[] premiki;
    private int size;

    public Stats(){
        this.primerjave = new int[3];
        this.premiki = new int[3];
        this.size = 0;
    }

    public void append(int premiki, int primerjave){
        this.premiki[size] = premiki;
        this.primerjave[size] = primerjave;
        size++;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++){
            sb.append(premiki[i]).append(" ");
            sb.append(primerjave[i]);
            if (i != 2)
                sb.append(" | ");
        }
        return sb.toString();
    }
}

class Par {
    private int[] seznam;
    private int indeks;

    public Par(int[] seznam, int indeks){
        this.seznam = seznam;
        this.indeks = indeks;
    }

    public int getIndeks() {
        return indeks;
    }

    public int[] getSeznam() {
        return seznam;
    }
}