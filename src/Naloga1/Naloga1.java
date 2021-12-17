package APS1.Naloga1;

import java.util.Scanner;

@SuppressWarnings("unchecked")
public class Naloga1 {

    static boolean pogoj;
    static Sequence<Stack<String>> skladi = new ArrayDeque<>();
    static int ponovitevFun;
    static int indeksFun;

    public static void main(String[] args) {
        create();
        String vrstica, niz;
        Scanner v, n;
        v = new Scanner(System.in);
        while (v.hasNextLine()){
            pogoj = false;
            ponovitevFun = 0;
            indeksFun = 0;
            delete();
            vrstica = v.nextLine();
            n = new Scanner(vrstica);
            while (n.hasNext()){
                niz = n.next();
                if (ponovitevFun > 0){
                    try {
                        skladi.get(indeksFun).push(niz);
                    }catch (CollectionException e){System.out.println("ponovitve");}
                    ponovitevFun--;
                    continue;
                }
                ukaz(niz);
            }
        }
    }



    public static void ukaz(String niz){
        char znak = niz.charAt(0);
        if (znak == '?'){
            niz = niz.substring(1);
            if (!pogoj)
                return;
        }
        switch (niz) {
            // ukazi nad skladom 0
            case "echo":
                try {
                    System.out.println(skladi.get(0).top());
                } catch (CollectionException e) {
                    System.out.println();
                }
                break;
            case "pop":
                try {
                    skladi.get(0).pop();
                } catch (CollectionException e){
                    //System.out.println(niz+" err "+e);
                }
                break;
            case "dup":
                try {
                    String temp = skladi.get(0).top();
                    skladi.get(0).push(temp);
                } catch (CollectionException e){
                    if (!"APS1.Naloga1.Naloga1$CollectionException: Collection is empty.".equals(e.toString()))
                        System.out.println(niz+" err "+e);
                }
                break;
            case "dup2":
                try {
                    String temp = skladi.get(0).pop();
                    String temp2 = skladi.get(0).top();
                    skladi.get(0).push(temp);
                    skladi.get(0).push(temp2);
                    skladi.get(0).push(temp);
                } catch (CollectionException e){
                    System.out.println(niz+" err "+e);
                }
                break;
            case "swap":
                try{
                    String temp = skladi.get(0).pop();
                    String temp2 = skladi.get(0).pop();
                    skladi.get(0).push(temp);
                    skladi.get(0).push(temp2);
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;

            // spreminjanje vrha glavnega sklada
            case "char":
                try {
                    int temp = Integer.parseInt(skladi.get(0).pop());
                    znak = (char) temp;
                    skladi.get(0).push(Character.toString(znak));
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case "even":
                try {
                    String temp = skladi.get(0).pop();
                    int stevilo = Integer.parseInt(temp);
                    if (stevilo % 2 == 0){
                        skladi.get(0).push(Integer.toString(1));
                        break;
                    }
                    skladi.get(0).push(Integer.toString(0));
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case "odd":
                try {
                    String temp = skladi.get(0).pop();
                    int stevilo = Integer.parseInt(temp);
                    if (stevilo % 2 != 0){
                        skladi.get(0).push(Integer.toString(1));
                        break;
                    }
                    skladi.get(0).push(Integer.toString(0));
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case "!":
                try {
                    String temp = skladi.get(0).pop();
                    int fakulteta = 1;
                    for (int i = Integer.parseInt(temp); i > 1; i--){
                        fakulteta *= i;
                    }
                    skladi.get(0).push(Integer.toString(fakulteta));
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case "len":
                try {
                    String temp = skladi.get(0).pop();
                    int dolzina = temp.length();
                    skladi.get(0).push(Integer.toString(dolzina));
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;

            // operacije nad dvema elementoma na glavnem skladu
            case "<>":
                try {
                    String prvi = skladi.get(0).pop();
                    String drugi = skladi.get(0).pop();
                    if (!prvi.equals(drugi)){
                        skladi.get(0).push(Integer.toString(1));
                        break;
                    }
                    skladi.get(0).push(Integer.toString(0));
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case "<":
                try {
                    int prvi = Integer.parseInt(skladi.get(0).pop());
                    int drugi = Integer.parseInt(skladi.get(0).pop());
                    skladi.get(0).push(Integer.toString(drugi<prvi ? 1 : 0));
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case "<=":
                try {
                    int prvi = Integer.parseInt(skladi.get(0).pop());
                    int drugi = Integer.parseInt(skladi.get(0).pop());
                    skladi.get(0).push(Integer.toString(drugi<=prvi ? 1 : 0));
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case "==":
                try {
                    int prvi = Integer.parseInt(skladi.get(0).pop());
                    int drugi = Integer.parseInt(skladi.get(0).pop());
                    skladi.get(0).push(Integer.toString(drugi==prvi ? 1 : 0));
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case ">":
                try {
                    int prvi = Integer.parseInt(skladi.get(0).pop());
                    int drugi = Integer.parseInt(skladi.get(0).pop());
                    skladi.get(0).push(Integer.toString(drugi>prvi ? 1 : 0));
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case ">=":
                try {
                    int prvi = Integer.parseInt(skladi.get(0).pop());
                    int drugi = Integer.parseInt(skladi.get(0).pop());
                    skladi.get(0).push(Integer.toString(drugi>=prvi ? 1 : 0));
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case "+":
                try {
                    int prvi = Integer.parseInt(skladi.get(0).pop());
                    int drugi = Integer.parseInt(skladi.get(0).pop());
                    skladi.get(0).push(Integer.toString(prvi+drugi));
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case "-":
                try {
                    int prvi = Integer.parseInt(skladi.get(0).pop());
                    int drugi = Integer.parseInt(skladi.get(0).pop());
                    skladi.get(0).push(Integer.toString(drugi-prvi));
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case "*":
                try {
                    int prvi = Integer.parseInt(skladi.get(0).pop());
                    int drugi = Integer.parseInt(skladi.get(0).pop());
                    skladi.get(0).push(Integer.toString(prvi*drugi));
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case "/":
                try {
                    int prvi = Integer.parseInt(skladi.get(0).pop());
                    int drugi = Integer.parseInt(skladi.get(0).pop());
                    skladi.get(0).push(Integer.toString(drugi/prvi));
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case "%":
                try {
                    int prvi = Integer.parseInt(skladi.get(0).pop());
                    int drugi = Integer.parseInt(skladi.get(0).pop());
                    skladi.get(0).push(Integer.toString(drugi%prvi));
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case ".":
                try {
                    String prvi = skladi.get(0).pop();
                    String drugi = skladi.get(0).pop();
                    skladi.get(0).push(drugi+prvi);
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case "rnd":
                try {
                    int prvi = Integer.parseInt(skladi.get(0).pop());
                    int drugi = Integer.parseInt(skladi.get(0).pop());
                    int rnd = (int)(Math.random()*(prvi-drugi))+drugi;
                    skladi.get(0).push(Integer.toString(rnd));
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;

            // operacije za pogojni stavek
            case "then":
                try {
                    String pogojno = skladi.get(0).pop();
                    Stack<String> temp = new ArrayDeque<>();
                    while (!chechInt(pogojno)){
                        temp.push(pogojno);
                        pogojno = skladi.get(0).pop();
                    }
                    while (!temp.isEmpty()){
                        skladi.get(0).push(temp.pop());
                    }
                    pogoj = !pogojno.equals("0");
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case "else":
                pogoj = !pogoj;
                break;

            // operacije za delo z poljubnim skladom
            case "print":
                try {
                    int indeks = Integer.parseInt(skladi.get(0).pop());
                    Stack<String> prazen = new ArrayDeque<>();
                    String element;
                    while (!skladi.get(indeks).isEmpty()){
                        element = skladi.get(indeks).pop();
                        prazen.push(element);
                    }
                    while (!prazen.isEmpty()){
                        element = prazen.pop();
                        System.out.printf("%s ", element);
                        skladi.get(indeks).push(element);
                    }
                } catch (CollectionException e){
                    //System.out.println(e);
                }
                System.out.println();
                break;
            case "clear":
                try {
                    int indeks = Integer.parseInt(skladi.get(0).pop());
                    emptyStack(indeks);
                } catch (CollectionException e){}//System.out.println(niz+" err "+e);}
                break;
            case "run":
                try {
                    int indeks = Integer.parseInt(skladi.get(0).pop());
                    Stack<String> prazen = new ArrayDeque<>();
                    String element;
                    while (!skladi.get(indeks).isEmpty()){
                        element = skladi.get(indeks).pop();
                        prazen.push(element);
                    }
                    Stack<String> pomozni = new ArrayDeque<>();
                    while (!prazen.isEmpty()){
                        element = prazen.pop();
                        pomozni.push(element);
                        skladi.get(indeks).push(element);
                    }
                    while (!pomozni.isEmpty())
                        prazen.push(pomozni.pop());
                    while (!prazen.isEmpty())
                        ukaz(prazen.pop());
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case "loop":
                try {
                    int indeks = Integer.parseInt(skladi.get(0).pop());
                    int ponovitve = Integer.parseInt(skladi.get(0).pop());
                    for (int i = 0; i < ponovitve; i++){
                        skladi.get(0).push(Integer.toString(indeks));
                        ukaz("run");
                    }
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case "fun":
                try {
                    int indeks = Integer.parseInt(skladi.get(0).pop());
                    int stSkladov = Integer.parseInt(skladi.get(0).pop());
                    indeksFun = indeks;
                    ponovitevFun = stSkladov;
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case "move":
                try {
                    int indeks = Integer.parseInt(skladi.get(0).pop());
                    int ponovitve = Integer.parseInt(skladi.get(0).pop());
                    for (int i = 0; i < ponovitve; i++){
                        skladi.get(indeks).push(skladi.get(0).pop());
                    }
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            case "reverse":
                try {
                    int indeks = Integer.parseInt(skladi.get(0).pop());
                    Stack<String> temp = new ArrayDeque<>();
                    Stack<String> temp2 = new ArrayDeque<>();
                    while (!skladi.get(indeks).isEmpty()){
                        temp.push(skladi.get(indeks).pop());
                    }
                    while (!temp.isEmpty()){
                        temp2.push(temp.pop());
                    }
                    while (!temp2.isEmpty()){
                        skladi.get(indeks).push(temp2.pop());
                    }
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
            default:
                try {
                    skladi.get(0).push(niz);
                } catch (CollectionException e){System.out.println(niz+" err "+e);}
                break;
        }

    }

    public static boolean chechInt (String niz){
        try {
            int stevilo = Integer.parseInt(niz);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public static int firstEmpty(int start){
        for (int i = start; i < 42; i++){
            try {
                if (skladi.get(i).isEmpty())
                    return i;
            } catch (CollectionException e){
                System.out.println(e);
            }
        }
        return -1;
    }

    public static void emptyStack(int indeks){
        try {
            Stack<String> sklad = skladi.get(indeks);
            while (!sklad.isEmpty())
                sklad.pop();
        } catch (CollectionException e){
            System.out.println(e);
        }
    }
    
    public static void delete(){
        for (int i = 0; i < 42; i++){
            Stack<String> nov = new ArrayDeque<>();
            try {
                skladi.set(i, nov);
            }
            catch (CollectionException e){
                System.out.println(e);
            }
        }
    }

    public static void create(){
        for (int i = 0; i < 42; i++){
            Stack<String> nov = new ArrayDeque<>();
            try {
                skladi.add(nov);
            }
            catch (CollectionException e){
                System.out.println(e);
            }
        }
    }
    
    
    
    // potrebni razredi
    // ColectionExeption
    static class CollectionException extends Exception {
        public CollectionException(String msg) {
            super(msg);
        }
    }
    
    // ArrayDeque
    static class ArrayDeque<T> implements Deque<T>, Stack<T>, Sequence<T> {
        private static final int DEFAULT_CAPACITY = 64;
        private T[] a;
        private int front, back, size;

        public ArrayDeque(){
            a = (T[])(new Object[DEFAULT_CAPACITY]);
            front = 0;
            back = 0;
            size = 0;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public boolean isFull() {
            return size == DEFAULT_CAPACITY;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public String toString(){
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            if (size > 0)
                sb.append(a[front].toString());
            for (int i = 0; i < size-1; i++){
                sb.append(", ").append(a[next(front + i)].toString());
            }
            sb.append("]");
            return sb.toString();
        }

        private int next(int i){
            return (i+1)%DEFAULT_CAPACITY;
        }

        private int prev(int i){
            return (DEFAULT_CAPACITY+i-1)%DEFAULT_CAPACITY;
        }

        @Override
        public T front() throws CollectionException {
            if (isEmpty())
                throw new CollectionException(ERR_MSG_EMPTY);
            return a[front];
        }

        @Override
        public T back() throws CollectionException {
            if (isEmpty())
                throw new CollectionException(ERR_MSG_EMPTY);
            return a[prev(back)];
        }

        @Override
        public void enqueue(T x) throws CollectionException {
            if (isFull())
                throw new CollectionException(ERR_MSG_FULL);
            a[back] = x;
            back = next(back);
            size++;
        }

        @Override
        public void enqueueFront(T x) throws CollectionException {
            if (isFull())
                throw new CollectionException(ERR_MSG_FULL);
            front = prev(front);
            a[front] = x;
            size++;
        }

        @Override
        public T dequeue() throws CollectionException {
            if (isEmpty())
                throw new CollectionException(ERR_MSG_EMPTY);
            T temp = a[front];
            a[front] = null;
            front = next(front);
            size--;
            return temp;
        }

        @Override
        public T dequeueBack() throws CollectionException {
            if (isEmpty())
                throw new CollectionException(ERR_MSG_EMPTY);
            back = prev(back);
            T temp = a[back];
            a[back] = null;
            size--;
            return temp;
        }

        @Override
        public T top() throws CollectionException {
            return back();
        }

        @Override
        public void push(T x) throws CollectionException {
            enqueue(x);
        }

        @Override
        public T pop() throws CollectionException {
            return dequeueBack();
        }

        @Override
        public void add(T x) throws CollectionException {
            enqueue(x);
        }

        @Override
        public T get(int i) throws CollectionException {
            if (isEmpty())
                throw new CollectionException(ERR_MSG_EMPTY);
            if (i >= size)
                throw new CollectionException(ERR_MSG_INDEX);
            return a[(front + i)%DEFAULT_CAPACITY];
        }

        @Override
        public T set(int i, T x) throws CollectionException {
            T temp = a[(front + i)%DEFAULT_CAPACITY];
            a[(front + i)%DEFAULT_CAPACITY] = x;
            return temp;
        }
    }

    // interface
    interface Collection {
        static final String ERR_MSG_EMPTY = "Collection is empty.";
        static final String ERR_MSG_FULL = "Collection is full.";

        boolean isEmpty();
        boolean isFull();
        int size();
        String toString();
    }


    interface Stack<T> extends Collection {
        T top() throws CollectionException;
        void push(T x) throws CollectionException;
        T pop() throws CollectionException;
    }


    interface Deque<T> extends Collection {
        T front() throws CollectionException;
        T back() throws CollectionException;
        void enqueue(T x) throws CollectionException;
        void enqueueFront(T x) throws CollectionException;
        T dequeue() throws CollectionException;
        T dequeueBack() throws CollectionException;
    }


    interface Sequence<T> extends Collection {
        static final String ERR_MSG_INDEX = "Wrong index in sequence.";
        T get(int i) throws CollectionException;
        void add(T x) throws CollectionException;
        T set(int i, T x) throws CollectionException;
    }
    
}
