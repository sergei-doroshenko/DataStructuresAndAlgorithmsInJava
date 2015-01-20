package chap03.ObjectSort;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by Sergei_Doroshenko on 1/12/2015.
 */
public class ArrayOG<T> implements Iterable<T> {
    private T[] a;                    // ref to array a
    private int nElems;               // number of data items
    //--------------------------------------------------------------
    public ArrayOG(int max) {        // constructor

        a = (T[]) new Object[max];   // create the array
        nElems = 0;                  // no items yet
    }
    //--------------------------------------------------------------
    // put person into array
    public void insert(T t) {
        a[nElems] = t;
        nElems++;                          // increment size
    }
    //--------------------------------------------------------------
    public void display() {             // displays array contents
        for(int j  = 0; j < nElems; j++)       // for each element,
            System.out.println(a[j]);          // display it
    }

    //--------------------------------------------------------------
    public void bubbleSort(Comparator<T> comparator) {
        int out, in;

        for(out = nElems-1; out > 0; out--) {  // outer loop (backward)
            for (in = 0; in < out; in++) {      // inner loop (forward)
                //if (a[in] > a[in + 1]) {       // out of order?
                if (comparator.compare(a[in], a[in + 1]) > 0) {
                    swap(in, in + 1);          // swap them
                }
            }
        }
    }  // end bubbleSort()
    //--------------------------------------------------------------
    private void swap(int one, int two) {
        T temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }
    //--------------------------------------------------------------
    public void bubbleSort2(Comparator<T> comparator) {
        int outRight, in = 0;

        for(outRight = nElems-1; outRight > 0; outRight--) {  // outer loop (backward)
            if (in < outRight) {
                for (; in < outRight; in++) {      // inner loop (forward)
                    //if (a[in] > a[in + 1]) {       // out of order?
                    if (comparator.compare(a[in], a[in + 1]) > 0) {
                        swap(in, in + 1);          // swap them
                    }
                }
            } else {
                for (; in > 0; in--) {      // inner loop (backward)
                    if (comparator.compare(a[in], a[in - 1]) < 0) {
                        swap(in, in - 1);          // swap them
                    }
                }
            }
        }
    }  // end bubbleSort2()
    //----------------------------------------------------------------
    public void selectionSort(Comparator<T> comparator) {
        int out, in, min;

        for(out = 0; out < nElems - 1; out++) {  // outer loop

            min = out;                     // minimum

            for(in=out+1; in<nElems; in++) { // inner loop
                //if (a[in] < a[min]) {         // if min greater,
                if (comparator.compare(a[in], a[min]) < 0) {
                    min = in;               // we have a new min
                }
            }
            swap(out, min);                // swap them
        }  // end for(out)
    }  // end selectionSort()
    //--------------------------------------------------------------
    public void insertionSort(Comparator<T> comparator) {
        int in, out;

        for(out = 1; out < nElems; out++) {
            T temp = a[out];       // out is dividing line
            in = out;                   // start shifting at out

            while(in > 0 && comparator.compare(a[in-1], temp) > 0) { // until smaller one found,
                a[in] = a[in-1];         // shift item to the right
                --in;                    // go left one position
            }
            a[in] = temp;               // insert marked item
        }  // end for
    }  // end insertionSort()

    @Override
    public Iterator<T> iterator() {
        return new ArrayOGIterator();
    }

    private class ArrayOGIterator implements Iterator<T> {
        private int i = nElems;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            //return a[--i]; // Reverse order
            return a[nElems - i--]; // Direct order
        }

        @Override
        public void remove() {

        }
    }
//--------------------------------------------------------------
}
