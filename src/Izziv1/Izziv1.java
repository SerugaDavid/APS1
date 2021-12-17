package APS1.Izziv1;

public class Izziv1 {
    public static void main(String[] args) {
        Stack<Integer> sklad = new ArrayDeque<>();
        Deque<Integer> vrsta = new ArrayDeque<>();
        Deque<Integer> vrsta2 = new ArrayDeque<>();
        Sequence<Integer> zaporedje = new ArrayDeque<>();
        System.out.println("Sklad: "+sklad.toString());
        for (int i = 0; !sklad.isFull(); i++){
            try {
                sklad.push(i);
            }
            catch (CollectionException e){
                System.out.println(e);
                break;
            }
        }
        System.out.println("Sklad: "+sklad.toString());
        System.out.println("Vrsta1: "+vrsta.toString());
        System.out.println("Vrsta2: "+vrsta2.toString());
        while (!sklad.isEmpty()){
            try{
                vrsta.enqueue(sklad.pop());
            }
            catch (CollectionException e){
                System.out.println(e);
                break;
            }
        }
        System.out.println("Sklad: "+sklad.toString());
        System.out.println("Vrsta1: "+vrsta.toString());
        System.out.println("Vrsta2: "+vrsta2.toString());
        while (!vrsta.isEmpty()){
            try {
                vrsta2.enqueueFront(vrsta.dequeueBack());
            }
            catch (CollectionException e){
                System.out.println(e);
                break;
            }
        }
        System.out.println("Vrsta1: "+vrsta.toString());
        System.out.println("Vrsta2: "+vrsta2.toString());
        System.out.println("Zaporedje: "+zaporedje.toString());
        while (!vrsta2.isEmpty()){
            try {
                zaporedje.add(vrsta2.dequeue());
            }
            catch (CollectionException e){
                System.out.println(e);
                break;
            }
        }
        System.out.println("Vrsta2: "+vrsta2.toString());
        System.out.println("Zaporedje: "+zaporedje.toString());
        try {
            System.out.println(zaporedje.get(zaporedje.size()-1));
        }
        catch (CollectionException e){
            System.out.println(e);
        }
    }
}
