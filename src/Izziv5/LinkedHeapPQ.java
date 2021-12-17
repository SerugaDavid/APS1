package APS1.Izziv5;

import APS1.Izziv2.LinkedSequence;

import java.lang.management.ThreadInfo;

public class LinkedHeapPQ<T extends Comparable> implements PriorityQueue<T> {
    class Node {
        T value;
        LinkedHeapPQ.Node levi, desni, oce;

        public Node(T element, LinkedHeapPQ.Node levi, LinkedHeapPQ.Node desni, LinkedHeapPQ.Node oce){
            this.value = element;
            this.levi = levi;
            this.desni = desni;
            this.oce = oce;
        }
    }

    private Node first, last, list;
    private int size, globina;
    private int primerjave, premiki;

    public LinkedHeapPQ(){
        this.first = null;
        this.last = null;
        this.list = null;
        this.size = 0;
        this.primerjave = 0;
        this.premiki = 0;
        this.globina = 0;
    }

    // Stats
    public int[] stats(){
        return new int[]{this.premiki, this.primerjave};
    }

    // Collection
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        String[] elementi = rekurzivna(this.first);
        if (elementi.length > 0)
            sb.append(elementi[0]);
        for (int i = 1; i < elementi.length; i++){
            sb.append(", ").append(elementi[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    private String[] rekurzivna(Node element){
        String[] seznam = new String[]{element.value.toString()};
        String[] leva;
        String[] desna;
        if (element.levi != null) {
            leva = this.rekurzivna(element.levi);
            if (element.desni != null)
                desna = this.rekurzivna(element.desni);
            else
                desna = new String[0];
        }
        else {
            leva = new String[0];
            desna = new String[0];
        }
        seznam = this.zdruzi(seznam, this.zdruzi(leva, desna));
        return seznam;
    }

    private String[] zdruzi(String[] prvi, String[] drugi){
        String[] novi = new String[prvi.length + drugi.length];
        for (int i = 0; i < prvi.length; i++){
            novi[i] = prvi[i];
        }
        for (int i = 0; i < drugi.length; i++){
            novi[i+prvi.length] = drugi[i];
        }
        return novi;
    }

    // Node manipulation
    private void reorder(boolean zgoraj){
        if (zgoraj){
            Node temp = this.first;
            while (temp != null)
                temp = this.shiftDown(temp);
        }
        else {
            Node temp = this.last;
            while (temp != null)
                temp = this.shiftUp(temp);
        }
    }

    private Node shiftUp(Node element){
        if (element.oce == null || element.oce.value.compareTo(element.value) > 0) {
            this.primerjave++;
            return null;
        }
        T temp = element.value;
        element.value = (T)element.oce.value;
        element.oce.value = temp;
        this.premiki += 3;
        return element.oce;
    }

    private Node shiftDown(Node element){
        if (element.levi == null)
            return null;
        T temp = (T)element.value;
        this.premiki++;
        if (element.desni != null && element.desni.value.compareTo(element.levi.value) > 0){
            this.primerjave++;
            element.value = (T)element.desni.value;
            element.desni.value = temp;
            this.premiki += 2;
            return element.desni;
        }
        element.value = (T)element.levi.value;
        element.levi.value = temp;
        this.premiki += 2;
        return element.levi;
    }

    // Queue
    @Override
    public T front() throws CollectionException {
        if (this.size == 0)
            throw new CollectionException(ERR_MSG_EMPTY);
        return this.first.value;
    }

    @Override
    public void enqueue(T x) {
        if (this.size == 0){
            this.first = new Node(x, null, null, null);
            this.last = this.first;
            this.list = this.first;
            this.size++;
            return;
        }
        this.last = new Node(x, null, null, this.list);
        this.size++;
        this.globina = (int)Math.floor(Math.log(this.size)/Math.log(2));
        if (levi())
            this.list.levi = this.last;
        else {
            this.list.desni = this.last;
            this.novList();
        }
        this.reorder(false);
    }

    private boolean levi(){
        return this.list.levi == null;
    }

    private void novList(){
        this.list = this.rekurzivnaList(this.first, 0);
    }

    private Node rekurzivnaList(Node element, int globina){
        int trenutna = this.globina;
        if (element.levi == null){
            this.globina = globina;
            return element;
        }
        Node temp = rekurzivnaList(element.levi, globina+1);
        if (this.globina < trenutna)
            return temp;
        temp = rekurzivnaList(element.desni, globina+1);
        if (globina == 0){
            if (this.globina < trenutna)
                return temp;
            temp = element;
            for (int i = 0; i < trenutna; i++){
                temp = temp.levi;
            }
        }
        return temp;
    }

    @Override
    public T dequeue() throws CollectionException {
        if (this.size == 0)
            throw new CollectionException(ERR_MSG_EMPTY);
        T temp = this.first.value;
        this.first.value = this.last.value;
        this.size--;
        this.globina = (int)Math.floor(Math.log(this.size)/Math.log(2));
        if (this.last.oce.levi == this.last) {
            this.last.oce.levi = null;
            this.setLast();
            this.novList();
        }
        else {
            this.last.oce.desni = null;
            this.last = this.last.oce.levi;
        }
        this.reorder(true);
        return temp;
    }

    private void setLast(){
        if (this.size == 0){
            this.last = null;
            this.first = null;
            this.list = null;
            return;
        }
        if (this.size == 1){
            this.last = this.first;
            this.list = this.first;
            return;
        }
        int globina = this.globina;
        Node zadnji = isciZadnji(this.first.levi, 1);
        if (this.first.desni == null){
            this.last = zadnji;
            return;
        }
        Node zadnji2 = isciZadnji(this.first.desni, 1);
        if (globina == this.globina){
            this.last = zadnji2;
            return;
        }
        this.last = zadnji;
    }

    private Node isciZadnji(Node element, int globina){
        if (element.levi == null){
            this.globina = globina;
            return element;
        }
        Node zadnji;
        if (element.desni != null){
            zadnji = isciZadnji(element.desni, globina+1);
            return zadnji;
        }
        zadnji = isciZadnji(element.levi, globina+1);
        return zadnji;
    }
}
