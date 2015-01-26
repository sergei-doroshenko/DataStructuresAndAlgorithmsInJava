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
            System.out.printf("%2d. %s\n", j, a[j]);          // display it
    }

    //--------------------------------------------------------------

    /**
     * One way move start --> end
     * @param comparator
     */
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

    /**
     * Two ways move start --> end
     *        than
     *               start <-- end
     * @param comparator
     */
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

    /**
     * Time complexity of this method is O(N × logN );
     * @param comparator
     */
    public void mergeSort(Comparator<T> comparator) {          // called by main()
                                       // provides workspace
        T[] workSpace = (T[]) new Object[nElems];
        recMergeSort(workSpace, 0, nElems-1, comparator);
    }
    //-----------------------------------------------------------
    private void recMergeSort(T[] workSpace, int lowerBound, int upperBound, Comparator<T> comparator) {

        if(lowerBound == upperBound) {           // if range is 1,
            return;                              // no use sorting
        } else {                                   // find midpoint
            int mid = (lowerBound + upperBound) / 2;
            // sort low half
            recMergeSort(workSpace, lowerBound, mid, comparator);
            // sort high half
            recMergeSort(workSpace, mid + 1, upperBound, comparator);
            // merge them
            merge(workSpace, lowerBound, mid + 1, upperBound, comparator);
        }  // end else
    }  // end recMergeSort()
    //-----------------------------------------------------------

    /**
     * Merge two sub-arrays (ranges) of a[]-array (instance field):
     * 1st from lowPtr to highPtr (exclusive)      = [lowPtr, highPtr)
     * 2nd from highPtr (inclusive) to upperBound = [highPtr, upperBound]
     * e.g. if we have array of ten int {0, 1, 2, 3, 4, 5, 6, 7, 8, 9} than
     * lowPtr = 0, highPtr = 5, upperBound = 9, so
     * 1st sub-array is {0, 1, 2, 3, 4} - [0, 5)
     * 2nd sub-array is {5, 6, 7, 8, 9} - [5, 9]
     * @param workSpace - temporary array to hold sorted elements
     * @param lowPtr - start index of 1st (low) sub-range (sub-array)
     * @param highPtr - start index of 2nd (high) sub-range (sub-array)
     * @param upperBound - high index (bound) of merge range
     * @param comparator - comparator for compare array elements
     */
    private void merge(T[] workSpace, int lowPtr, int highPtr, int upperBound, Comparator<T> comparator) {
        int j = 0;                            // workspace index
        int lowerBound = lowPtr;              // save initial lower bound index
        int mid = highPtr - 1;                // set middle index of merge range
        int n = upperBound - lowerBound + 1;  // number of items

        // We merge 2 subarrays:
        // 1st subarray from lowPrt to mid; 2nd subarray from highPtr to upperBound
        while(lowPtr <= mid && highPtr <= upperBound) {           // neither array empty
            if (comparator.compare(a[lowPtr], a[highPtr]) < 0) {  // compare two elements of merging arrays
                workSpace[j++] = a[lowPtr++];                     // if low element less than put it in workSpace-array and INCREMENT INDEXES
            } else {
                workSpace[j++] = a[highPtr++];                    // if high element less...
            }
        }

        while(lowPtr <= mid) {                 // lower half  is empty,
            workSpace[j++] = a[lowPtr++];      // but higher isn't
        }
        while(highPtr <= upperBound) {         // higher half  is empty,
            workSpace[j++] = a[highPtr++];     // but lower isn't
        }

        for(j = 0; j < n; j++) {               // copy merged (sorted) elements from workSpace to a[] - array
            a[lowerBound + j] = workSpace[j];
        }
    }  // end merge()
    //-----------------------------------------------------------

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
