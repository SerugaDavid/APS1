package APS1.teorija2;
import java.util.Stack;

public class Obracanje {
    public static void main(String[] args) {
        Stack<Integer> sklad = new Stack<>();
        for (int i = 0; i < 10; i++){
            sklad.push(i);
        }
        System.out.println(sklad);
        obrni(sklad, 5, 4);
        System.out.println(sklad);
        obrni(sklad, 5, 4);
        obrni2(sklad, 5, 4);
        System.out.println(sklad);
    }

    public static void obrniCel(Stack<Integer> original){
        Stack<Integer> prvi = new Stack<>();
        Stack<Integer> drugi = new Stack<>();
        while (!original.empty()){
            prvi.push(original.pop());
        }
        while (!prvi.empty()){
            drugi.push(prvi.pop());
        }
        while (!drugi.empty()){
            original.push(drugi.pop());
        }
    }

    public static void obrni(Stack<Integer> s, int n, int m){
        Stack<Integer> prelozeni = new Stack<>();
        Stack<Integer> glavni = new Stack<>();
        for (int i = 0; i < n; i++){
            prelozeni.push(s.pop());
        }
        for (int i = 0; i < m; i++){
            glavni.push(s.pop());
        }
        obrniCel(glavni);
        while (!glavni.empty()){
            s.push(glavni.pop());
        }
        while (!prelozeni.empty()){
            s.push(prelozeni.pop());
        }
    }

    public static void obrni2(Stack<Integer> s, int n, int m){
        Stack<Integer> prelozeni = new Stack<>();
        Stack<Integer> glavni = new Stack<>();
        int prelozi = s.size() - n - m;
        for (int i = 0; i < prelozi; i++){
            prelozeni.push(s.pop());
        }
        for (int i = 0; i < m; i++){
            glavni.push(s.pop());
        }
        obrniCel(glavni);
        while (!glavni.empty()){
            s.push(glavni.pop());
        }
        while (!prelozeni.empty()){
            s.push(prelozeni.pop());
        }
    }
}
