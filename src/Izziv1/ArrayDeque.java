package APS1.Izziv1;
//import java.util.Collections;

class ArrayDeque<T> implements Deque<T>, Stack<T>, Sequence<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] a;
    private int front, back, size;

    public ArrayDeque(){
        a = (T[])(new Object[DEFAULT_CAPACITY]);
        this.front = 0;
        this.back = 0;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean isFull() {
        return this.size == DEFAULT_CAPACITY;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if (size > 0)
            sb.append(this.a[this.front].toString());
        for (int i = 0; i < this.size-1; i++){
            sb.append(", ").append(this.a[this.next(this.front + i)].toString());
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
        if (this.isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        return this.a[this.front];
    }

    @Override
    public T back() throws CollectionException {
        if (this.isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        return this.a[this.prev(this.back)];
    }

    @Override
    public void enqueue(T x) throws CollectionException {
        if (this.isFull())
            throw new CollectionException(ERR_MSG_FULL);
        this.a[this.back] = x;
        this.back = this.next(this.back);
        this.size++;
    }

    @Override
    public void enqueueFront(T x) throws CollectionException {
        if (this.isFull())
            throw new CollectionException(ERR_MSG_FULL);
        this.front = this.prev(this.front);
        this.a[this.front] = x;
        this.size++;
    }

    @Override
    public T dequeue() throws CollectionException {
        if (this.isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        T temp = this.a[this.front];
        this.a[this.front] = null;
        this.front = this.next(this.front);
        this.size--;
        return temp;
    }

    @Override
    public T dequeueBack() throws CollectionException {
        if (this.isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        this.back = this.prev(this.back);
        T temp = this.a[this.back];
        this.a[this.back] = null;
        this.size--;
        return temp;
    }

    @Override
    public T top() throws CollectionException {
        return this.back();
    }

    @Override
    public void push(T x) throws CollectionException {
        this.enqueue(x);
    }

    @Override
    public T pop() throws CollectionException {
        return this.dequeueBack();
    }

    @Override
    public void add(T x) throws CollectionException {
        this.enqueue(x);
    }

    @Override
    public T get(int i) throws CollectionException {
        if (this.isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        if (i >= this.size)
            throw new CollectionException(ERR_MSG_INDEX);
        return this.a[(this.front + i)%DEFAULT_CAPACITY];
    }
}

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
}