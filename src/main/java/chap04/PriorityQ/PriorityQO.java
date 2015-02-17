package chap04.PriorityQ;

import chap03.ObjectSort.ArrayOG;
import chap14.mstw.Edge;

import java.util.Comparator;

/**
 * Created by Sergei Doroshenko on 19.01.2015.
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

    public T removeN (int n) {        // remove item at n
        return queArray.delete(n);
    }

    public T peekMin() {         // peek at minimum item
        return queArray.peekMin();
    }

    public T peekN(int n) {     // peek at item n
        return queArray.peekN(n);
    }

    public int find(int findDex) { // find item with specified destVert value

        for (int j = 0; j < size(); j++) {
            Object o = queArray.peekN(j);
            if (o instanceof Edge && ((Edge) o).destVert == findDex ) {
                return j;
            }
        }

        return -1;
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
