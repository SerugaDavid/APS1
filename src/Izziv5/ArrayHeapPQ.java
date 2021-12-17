package APS1.Izziv5;

public class ArrayHeapPQ<T extends Comparable> implements PriorityQueue<T> {
    private T[] elementi;
    private int size;
    private int primerjave, premiki;
    private static int DEFAULT_CAPACITY = 64;

    public ArrayHeapPQ(){
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

    private void reorder(boolean zgoraj){
        int ret = 0;
        if (zgoraj){
            while (ret != -1){
                ret = shiftDown(ret);
            }
        }
        else {
            ret = this.size - 1;
            while (ret != -1){
                ret = shiftUp(ret);
            }
        }
    }

    private int shiftUp(int i){
        if (i == 0)
            return -1;
        int oce = (i-1)/2;
        if (this.elementi[oce].compareTo(this.elementi[i]) < 0) {
            this.primerjave++;
            T temp = this.elementi[i];
            this.elementi[i] = this.elementi[oce];
            this.elementi[oce] = temp;
            this.premiki += 3;
            return oce;
        }
        return -1;
    }

    private int shiftDown(int i){
        int levi = 2*i + 1;
        int desni = levi + 1;
        if (levi < this.size){
            if (desni < this.size){
                if (this.elementi[desni].compareTo(this.elementi[levi]) > 0 && this.elementi[desni].compareTo(this.elementi[i]) > 0){
                    this.primerjave+=2;
                    T temp = this.elementi[i];
                    this.elementi[i] = this.elementi[desni];
                    this.elementi[desni] = temp;
                    this.premiki += 3;
                    return desni;
                }
            }
            if (this.elementi[levi].compareTo(this.elementi[i]) > 0) {
                this.primerjave++;
                T temp = this.elementi[i];
                this.elementi[i] = this.elementi[levi];
                this.elementi[levi] = temp;
                this.premiki += 3;
                return levi;
            }
        }
        return -1;
    }

    // Queue
    @Override
    public T front() throws CollectionException {
        if (this.size == 0)
            throw new CollectionException(ERR_MSG_EMPTY);
        return this.elementi[0];
    }

    @Override
    public void enqueue(T x) {
        if (this.size == this.elementi.length)
            this.resize(true);
        this.premiki++;
        this.elementi[this.size] = x;
        this.size++;
        this.reorder(false);
    }

    @Override
    public T dequeue() throws CollectionException {
        if (this.size == 0)
            throw new CollectionException(ERR_MSG_EMPTY);
        if (this.size < this.elementi.length/3 && this.elementi.length > DEFAULT_CAPACITY)
            this.resize(false);
        T temp = this.elementi[0];
        this.size--;
        this.elementi[0] = this.elementi[this.size];
        this.elementi[this.size] = null;
        this.premiki += 3;
        this.reorder(true);
        return temp;
    }
}
