package chap04.PriorityQ;

import chap03.ObjectSort.ArrayOG;

import java.util.Comparator;

/**
 * Created by user on 19.01.2015.
 */
public class PriorityQO<T> {
    // array in sorted order, from max at 0 to min at size-1
    private int maxSize;
    private ArrayOG<T> queArray;
    private Comparator<T> comparator;

    public PriorityQO(int s, Comparator<T> comparator) {         // constructor
        this.maxSize = s;
        this.queArray = new ArrayOG<>(maxSize);
        this.comparator = comparator;
    }

    public void insert(T item) {
        queArray.insert(item);
        queArray.quickSort(comparator);
    }

    public T remove() {             // remove minimum item
        return queArray.delete();
    }

    public int size() { return queArray.size(); }

    public boolean isEmpty() {        // true if queue is empty
        return (queArray.size() == 0);
    }

    public boolean isFull() {          // true if queue is full
        return (queArray.size() == maxSize);
    }

    @Override
    public String toString() {
        return queArray.toString();
    }
}
