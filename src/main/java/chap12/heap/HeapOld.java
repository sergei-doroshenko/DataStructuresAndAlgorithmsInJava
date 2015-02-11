package chap12.heap;

import java.util.Comparator;

/**
 * Created by Sergei Doroshenko on 11.02.2015.
 */
public class HeapOld<T> { //  extends Comparable<T>
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
    private Node<T>[] heapArray;

    // -------------------------------------------------------------
    public HeapOld(int mx) {           // constructor
        this.maxSize = mx;
        this.comparator = null;
        this.currentSize = 0;
        this.heapArray = new Node[maxSize];  // create array
    }

    public HeapOld(int mx, Comparator<? super T> comparator) {
        this.maxSize = mx;
        this.comparator = comparator;
        this.currentSize = 0;
        this.heapArray = new Node[maxSize];  // create array
    }

    // -------------------------------------------------------------
    public boolean isEmpty() { return currentSize==0; }
    // -------------------------------------------------------------
    public boolean insert(T key) {
        if(currentSize == maxSize) {
            return false;
        }
        
        Node<T> newNode = new Node<>(key);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }  // end insert()
    // -------------------------------------------------------------

    private int getCmpResult(Node<T> n1, Node<T> n2) {
        return getCmpResult(n1.getKey(), n2.getKey());  //n1.getKey().compareTo(n2.getKey());
    }

    private int getCmpResult(T t1, T t2) {
        Comparable<? super T> k1 = (Comparable<? super T>) t1; // cast t1 to Comparable

        return k1.compareTo(t2);
    }

    public void trickleUp(int index) {
        int parent = (index-1) / 2;
        Node<T> bottom = heapArray[index];

        while( index > 0 && getCmpResult(heapArray[parent], bottom) < 0) { // heapArray[parent].getKey().compareTo(bottom.getKey())
            heapArray[index] = heapArray[parent];  // move it down
            index = parent;
            parent = (parent-1) / 2;
        }  // end while
        heapArray[index] = bottom;
    }  // end trickleUp()
    // -------------------------------------------------------------
    public Node<T> remove() {          // delete item with max key
                                    // (assumes non-empty list)
        Node<T> root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }  // end remove()
    // -------------------------------------------------------------
    public void trickleDown(int index) {

        int largerChild;
        Node<T> top = heapArray[index];       // save root
        while(index < currentSize/2) {        // while node has at least one child,
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

        T oldValue = heapArray[index].getKey(); // remember old
        heapArray[index].setKey(newValue);  // change to new

        if(getCmpResult(oldValue, newValue) < 0) { // if raised, oldValue.compareTo(newValue)
            trickleUp(index);                // trickle it up
        } else {                                 // if lowered,
            trickleDown(index);              // trickle it down
        }

        return true;
    }  // end change()
    // -------------------------------------------------------------
    public void displayHeap() {

        System.out.print("heapArray: ");    // array format
        for(int m = 0; m < currentSize; m++) {
            if (heapArray[m] != null) {
                System.out.print(heapArray[m].getKey() + " ");
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
            System.out.print(heapArray[j].getKey());

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
        System.out.println("\n"+dots+dots); // dotted bottom line
    }  // end displayHeap()
}
