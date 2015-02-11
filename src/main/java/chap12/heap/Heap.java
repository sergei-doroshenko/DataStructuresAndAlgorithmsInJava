package chap12.heap;

import java.util.Comparator;

/**
 * Created by Sergei Doroshenko on 11.02.2015.
 * For a node at index x in the array,
 * Its parent is (x-1) / 2; Its left child is 2*x + 1; Its right child is 2*x + 2.
 * schema:
 *            (x-1) / 2
 *               |
 *               x
 *       |                 |
 *    2*x + 1           2*x + 2
 * ------------------------------------------------------------------------------
 * A heap is a special kind of binary tree, the number of levels L in a binary tree equals log2(N+1),
 * where N is the number of nodes. The trickleUp() and trickleDown() routines cycle through their loops L-1 times,
 * so the first takes time proportional to log2N, and the second somewhat more because of the
 * extra comparison. Thus, the heap operations we’ve talked about here all operate in O(logN) time.
 */
public class Heap<T> { //  extends Comparable<T>
    private int maxSize;           // size of array
    /*
    Wildcards are useful in situations where only partial knowledge about the type parameter is required.
    [...] An upper bound is signified by the syntax:

    ? extends B
    where B is the upper bound. [...] it is permissible to declare lower bounds on a wildcard, using the syntax:

    ? super B
    where B is a lower bound.

    In this specific case, what it is saying is that the type has to implement comparable of itself or its superclass.
     */
    private final Comparator<? super T> comparator;
    private int currentSize;       // number of nodes in array
    private T[] heapArray;

    // -------------------------------------------------------------
    public Heap(int mx) {           // constructor
        this.maxSize = mx;
        this.comparator = null;
        this.currentSize = 0;
        this.heapArray = (T[]) new Object[maxSize];  // create array
    }

    public Heap(int mx, Comparator<? super T> comparator) {
        this.maxSize = mx;
        this.comparator = comparator;
        this.currentSize = 0;
        this.heapArray = (T[]) new Object[maxSize];  // create array
    }

    // -------------------------------------------------------------
    public int size() { return currentSize; }

    public boolean isEmpty() { return currentSize == 0; }
    // -------------------------------------------------------------
    public boolean insert(T key) {
        if(currentSize == maxSize) {
            return false;
        }

        heapArray[currentSize] = key;
        trickleUp(currentSize++);
        return true;
    }  // end insert()

    public void insertAt(int index, T newNode) { heapArray[index] = newNode; }

    public void incrementSize() { currentSize++; }

    public boolean toss(T key) {
        if(currentSize == maxSize) {
            return false;
        }

        heapArray[currentSize++] = key;
        return true;
    }
    // -------------------------------------------------------------

    private int getCmpResult(T t1, T t2) {
        if (comparator == null) {
            Comparable<? super T> k1 = (Comparable<? super T>) t1; // cast t1 to Comparable
            return k1.compareTo(t2);
        }
        return comparator.compare(t1, t2);
    }

    public void trickleUp(int index) {
        int parent = (index-1) / 2;
        T bottom = heapArray[index];

        while( index > 0 && getCmpResult(heapArray[parent], bottom) < 0) { // heapArray[parent].getKey().compareTo(bottom.getKey())
            heapArray[index] = heapArray[parent];  // move it down
            index = parent;
            parent = (parent-1) / 2;
        }  // end while
        heapArray[index] = bottom;
    }  // end trickleUp()
    // -------------------------------------------------------------
    public T remove() {          // delete item with max key
                                    // (assumes non-empty list)
        T root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }  // end remove()
    // -------------------------------------------------------------
    public void trickleDown(int index) {

        int largerChild;
        T top = heapArray[index];         // save root
        while(index < currentSize/2) {    // while node has at least one child,
            int leftChild = 2*index+1;
            int rightChild = leftChild+1;
            // find larger child
            if(rightChild < currentSize &&  // (rightChild exists?)
                   getCmpResult(heapArray[leftChild], heapArray[rightChild])  < 0) { // heapArray[leftChild].getKey().compareTo(heapArray[rightChild].getKey())
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }
            // top >= largerChild?
            if( getCmpResult(top, heapArray[largerChild]) >= 0 ) // top.getKey().compareTo(heapArray[largerChild].getKey())
                break;
            // shift child up
            heapArray[index] = heapArray[largerChild];
            index = largerChild;            // go down
        }  // end while
        heapArray[index] = top;            // root to index
    }  // end trickleDown()
    // -------------------------------------------------------------
    public boolean change(int index, T newValue) {

        if(index < 0 || index >= currentSize) {
            return false;
        }

        T oldValue = heapArray[index];      // remember old
        heapArray[index] = newValue;  // change to new

        if(getCmpResult(oldValue, newValue) < 0) { // if raised, oldValue.compareTo(newValue)
            trickleUp(index);                // trickle it up
        } else {                                 // if lowered,
            trickleDown(index);              // trickle it down
        }

        return true;
    }  // end change()

    /**
     * size/2 points to first node from last (without children) nodes
     * We can start at node N/2-1, the rightmost node with children, instead of N-1,
     * the last node. Thus, we need only half as many trickle operations as we would using
     * insert() N times.
     */
    public void restoreHeap() {
        for(int j = currentSize/2 -1; j >= 0; j--) { // make random array into heap
            this.trickleDown(j);
        }
    }

    // -------------------------------------------------------------
    public void displayHeap() {

        System.out.print("heapArray: ");    // array format
        for(int m = 0; m < currentSize; m++) {
            if (heapArray[m] != null) {
                System.out.print(heapArray[m] + " ");
            } else {
                System.out.print("-- ");
            }
        }
        System.out.println();
        // heap format
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;                          // current item
        String dots = "...............................";
        System.out.println(dots + dots);      // dotted top line

        while(currentSize > 0) {             // for each heap item

            if(column == 0) {                 // first item in row?
                for (int k = 0; k < nBlanks; k++) {  // preceding blanks
                    System.out.print(' ');
                }
            }
            // display item
            System.out.print(heapArray[j]);

            if(++j == currentSize) {           // done?
                break;
            }

            if(++column == itemsPerRow) {       // end of row?
                nBlanks /= 2;                 // half the blanks
                itemsPerRow *= 2;             // twice the items
                column = 0;                   // start over on
                System.out.println();         //    new row
            } else {                             // next item on row
                for (int k = 0; k < nBlanks * 2 - 2; k++) {
                    System.out.print(' ');     // interim blanks
                }
            }
        }  // end for
        System.out.println("\n" + dots + dots); // dotted bottom line
    }  // end displayHeap()

    public void displayArray() {
        for(int j=0; j<maxSize; j++) {
            System.out.print(heapArray[j] + " ");
        }
        System.out.println("");
    }
}
