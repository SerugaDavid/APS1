package APS1.Izziv5;

public class ArrayPQ<T extends Comparable> implements PriorityQueue<T> {
    private T[] elementi;
    private int size;
    private int primerjave, premiki;
    private static int DEFAULT_CAPACITY = 64;

    public ArrayPQ(){
        this.elementi = (T[])new Comparable[DEFAULT_CAPACITY];
        this.size = 0;
        this.primerjave = 0;
        this.premiki = 0;
    }

    // stats
    public int[] stats(){
        return new int[]{this.premiki, this.primerjave};
    }

    // Collection
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if (this.size > 0)
            sb.append(this.elementi[0].toString());
        for (int i = 1; i < this.size; i++){
            sb.append(", ").append(this.elementi[i].toString());
        }
        sb.append("]");
        return sb.toString();
    }

    // array manipulation
    private void resize(boolean povecaj){
        if (povecaj){
            int dolzina = this.elementi.length;
            T[] nov = (T[])new Comparable[dolzina*2];
            for (int i = 0; i < dolzina; i++){
                this.premiki++;
                nov[i] = this.elementi[i];
            }
            this.elementi = nov;
        }
        else {
            int dolzina = this.elementi.length/2;
            T[] nov = (T[])new Comparable[dolzina];
            for (int i = 0; i < dolzina; i++){
                this.premiki++;
                nov[i] = this.elementi[i];
            }
            this.elementi = nov;
        }
    }
    private int najvecji() throws CollectionException{
        if (this.isEmpty()){
            throw new CollectionException(ERR_MSG_EMPTY);
        }
        T temp = (T) this.elementi[0];
        this.premiki++;
        int najvecji = 0;
        for (int i = 1; i < this.size; i++){
            this.primerjave++;
            if (temp.compareTo(this.elementi[i]) < 0){
                this.premiki++;
                temp = (T) this.elementi[i];
                najvecji = i;
            }
        }
        return najvecji;
    }

    // Queue
    @Override
    public T front() throws CollectionException {
        int najvecji = this.najvecji();
        return (T) this.elementi[najvecji];
    }

    @Override
    public void enqueue(T x) {
        if (this.size == this.elementi.length)
            this.resize(true);
        this.premiki++;
        this.elementi[this.size] = x;
        this.size++;
    }

    @Override
    public T dequeue() throws CollectionException {
        if (this.size < this.elementi.length/3 && this.elementi.length > DEFAULT_CAPACITY)
            this.resize(false);
        int indeks = this.najvecji();
        T element = (T) this.elementi[indeks];
        this.size--;
        this.elementi[indeks] = this.elementi[this.size];
        this.elementi[this.size] = null;
        this.premiki += 3;
        return element;
    }
}
