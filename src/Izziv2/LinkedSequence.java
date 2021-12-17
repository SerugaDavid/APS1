package APS1.Izziv2;


public class LinkedSequence<T> implements Sequence<T> {
    class Node {
        T value;
        Node next, prev;

        public Node(T element, Node next, Node prev){
            this.value = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node first, last;
    private int size;

    public LinkedSequence(){
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        Node temp = this.first;
        if (size > 0)
            sb.append(this.first.value.toString());
        for (int i = 0; i < this.size-1; i++){
            temp = temp.next;
            sb.append(", ").append(temp.value.toString());
        }
        sb.append("]");
        return sb.toString();
    }

    private Node indeks(int a) throws CollectionException{
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        if (a >= size())
            throw new CollectionException(ERR_MSG_INDEX);
        Node temp = this.first;
        for (int i = 0; i < a; i++){
            temp = temp.next;
        }
        return temp;
    }

    @Override
    public T get(int i) throws CollectionException {
        return indeks(i).value;
    }

    @Override
    public void add(T x) throws CollectionException {
        if (isEmpty()){
            this.first = new Node(x, null, null);
            this.last = this.first;
        }
        else {
            this.last = new Node(x, null, this.last);
            this.last.prev.next = this.last;
        }
        this.size++;
    }

    @Override
    public void insert(int i, T x) throws CollectionException {
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        if (i > size())
            throw new CollectionException(ERR_MSG_INDEX);
        try {
            Node temp = indeks(i);
            temp.prev = new Node(x, temp, temp.prev);
            if (temp.prev.prev != null)
                temp.prev.prev.next = temp.prev;
            else {
                this.first = temp.prev;
            }
            this.size++;
        }
        catch (CollectionException e){
            add(x);
        }
    }

    @Override
    public T set(int i, T x) throws CollectionException {
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        if (i >= size())
            throw new CollectionException(ERR_MSG_INDEX);
        T temp = get(i);
        indeks(i).value = x;
        return temp;
    }

    @Override
    public T delete(int i) throws CollectionException {
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        if (i >= size())
            throw new CollectionException(ERR_MSG_INDEX);
        T temp = get(i);
        if (size()==1){
            first = null;
            last = null;
            this.size--;
            return temp;
        }
        if (i == 0) {
            this.first = this.first.next;
            this.first.prev = null;
        }
        else if (i == this.size-1){
            this.last = this.last.prev;
            this.last.next = null;
        }
        else {
            Node temp2 = indeks(i).next;
            indeks(i).prev.next = temp2;
            temp2.prev = indeks(i - 1);
        }
        this.size--;
        return temp;
    }
}

interface Collection {
    static final String ERR_MSG_EMPTY = "Collection is empty.";
    // static final String ERR_MSG_FULL = "Collection is full.";

    boolean isEmpty();
    // boolean isFull();
    int size();
    String toString();
}

interface Sequence<T> extends Collection {
    static final String ERR_MSG_INDEX = "Wrong index in sequence.";
    T get(int i) throws CollectionException;
    void add(T x) throws CollectionException;
    void insert(int i, T x) throws CollectionException;
    T set(int i, T x) throws CollectionException;
    T delete(int i) throws CollectionException;
}