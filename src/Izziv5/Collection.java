package APS1.Izziv5;

interface Collection {
    static final String ERR_MSG_EMPTY = "Collection is empty.";
    // static final String ERR_MSG_FULL = "Collection is full.";

    boolean isEmpty();
    // boolean isFull();
    int size();
    String toString();
}

interface Queue<T> extends Collection {
    T front() throws CollectionException;
    void enqueue(T x);
    T dequeue() throws CollectionException;
}

interface PriorityQueue<T extends Comparable> extends Queue<T> {

}