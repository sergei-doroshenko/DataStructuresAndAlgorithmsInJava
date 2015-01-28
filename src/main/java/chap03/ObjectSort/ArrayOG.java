package chap03.ObjectSort;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by Sergei_Doroshenko on 1/12/2015.
 */
public class ArrayOG<T> implements Iterable<T> {
    private T[] a;                    // ref to array a
    private int nElems;               // number of data items
    private int copyCounter;
    private int compareCounter;

    //--------------------------------------------------------------
    public ArrayOG(int max) {        // constructor

        a = (T[]) new Object[max];   // create the array
        nElems = 0;                  // no items yet
    }
    //--------------------------------------------------------------


    public int getCopyCounter() {
        return copyCounter;
    }

    public void setCopyCounter(int copyCounter) {
        this.copyCounter = copyCounter;
    }

    public int getCompareCounter() {
        return compareCounter;
    }

    public void setCompareCounter(int compareCounter) {
        this.compareCounter = compareCounter;
    }

    public int size() {
        return nElems;
    }

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
     * Time complexity = O(n2)
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
        copyCounter += 1;
        //System.out.println("Swap: a[" + one + "]=" + a[one] +" , a[" + two + "]=" + a[two]);
    }
    //--------------------------------------------------------------

    /**
     * Two ways move start --> end
     *        than
     *               start <-- end
     * Time complexity = O(n2)
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

    /**
     * Time complexity = O(n2)
     * @param comparator
     */
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

    /**
     * Time complexity = O(n2)
     * @param comparator
     */
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

    public void insertionSort(int left, int rigth, Comparator<T> comparator) {
        int in, out;                      //  sorted on left of out

        for(out = left + 1; out <= rigth; out++) {
            T temp = a[out];            // remove marked item
            in = out;                   // start shifts at out

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

    /**
     * Complexity = O(n^3/2) - O(n^7/6)
     * @param comparator
     */
    public void shellSort(Comparator<T> comparator) {
        int inner, outer;
        T temp;

        int h = 1;                     // find initial value of h
        while(h <= nElems/3) {
            h = h * 3 + 1;                // (1, 4, 13, 40, 121, ...)
        }

        while(h > 0) {                     // decreasing h, until h = 1

            // h-sort the file
            for(outer = h; outer < nElems; outer++) {
                temp = a[outer];
                inner = outer;
                // one subpass (eg 0, 4, 8)

                while(inner > h - 1 && comparator.compare(a[inner-h], temp) >= 0) { // a[inner-h] >=  temp
                    a[inner] = a[inner-h];
                    inner -= h;
                }

                a[inner] = temp;
            }  // end for

            h = (h-1) / 3;              // decrease h
        }  // end while(h>0)

    }  // end shellSort()
    //--------------------------------------------------------------

    /**
     * Time complexity = O(N*logN)
     * @param comparator - comparator for compare two items
     */
    public void quickSort(Comparator<T> comparator) {
        recQuickSort2(0, nElems - 1, comparator);
        /* Another approach - remove insertion sort from recQuickSort
         for sort small sub-arrays and use insertion sort after quick sort
         when array is almost sorted. */
    }

    /**
     * Simple implementation of Quick sort algorithm
     * @param left - left bound of array (sub-array)
     * @param right - right bound of array (sub-array)
     * @param comparator - comparator for compare two items
     */
    public void recQuickSort1(int left, int right, Comparator<T> comparator) {
        /* ------------- Recursion base case  -------------------  */
        if(right-left <= 0) {              // if size <= 1,
            return;                        // already sorted
        } else {                           // size is 2 or larger
            /* IMPORTANT STEP */
            T pivot = a[right];           // rightmost item used in previous implementation

            T median = medianOf3(left, right, comparator);
            // partition range
            int partition = partitionIt1(left, right, median, comparator);
            /* After partition pivot element is already sorted and placed at final position. */

            recQuickSort1(left, partition - 1, comparator);   // sort left side
            recQuickSort1(partition + 1, right, comparator);  // sort right side
        }
    }  // end recQuickSort1()


    /**
     * Improved implementation with median and manual sort for sub-arrays which contains less than 4 elements.
     * @param left - left bound of array (sub-array)
     * @param right - right bound of array (sub-array)
     * @param comparator - comparator for compare two items
     */
    public void recQuickSort2(int left, int right, Comparator<T> comparator) {
        int size = right - left + 1;

        /* ------------- Recursion base case  -------------------  */
        if(size <= 3) {                           // manual sort if small
            manualSort(left, right, comparator);
        } else {
            /* IMPORTANT STEP */
            T median = medianOf3(left, right, comparator);

            // partition range
            int partition = partitionIt2(left, right, median, comparator);

            recQuickSort2(left, partition - 1, comparator);   // sort left side
            recQuickSort2(partition + 1, right, comparator);  // sort right side

        }

    }  // end recQuickSort2()

    /**
     * Improved implementation with median and insertion sort for sub-arrays with size less than 10.
     * @param left - left bound of array (sub-array)
     * @param right - right bound of array (sub-array)
     * @param comparator - comparator for compare two items
     */
    public void recQuickSort3(int left, int right, Comparator<T> comparator) {
        int size = right-left+1;

        /* ------------- Recursion base case  -------------------  */
        if(size < 10) {                           // manual sort if small
            insertionSort(left, right, comparator);
        } else {
            /* IMPORTANT STEP */
            T median = medianOf3(left, right, comparator);

            // partition range
            int partition = partitionIt2(left, right, median, comparator);

            recQuickSort3(left, partition - 1, comparator);   // sort left side
            recQuickSort3(partition + 1, right, comparator);  // sort right side

        }

    }  // end recQuickSort3()

    public void manualSort(int left, int right, Comparator<T> comparator) {
        int size = right - left + 1;
        if(compareCounter++ > -1 && size <= 1) {
            return;         // no sort necessary
        }

        if(size == 2) {    // 2-sort left and right
            if( compareCounter++ > -1 && comparator.compare(a[left], a[right]) > 0 ) {// a[left] > a[right]
                swap(left, right);
            }
            return;
        } else {               // size is 3, 3-sort left, center, & right
            if( compareCounter++ > -1 && comparator.compare(a[left],a[right-1]) > 0 ) {   // a[left] > a[right-1]
                swap(left, right - 1);                           // left, center
            }

            if( compareCounter++ > -1 && comparator.compare(a[left], a[right]) > 0 ) {    // a[left] > a[right]
                swap(left, right);                               // left, right
            }

            if( compareCounter++ > -1 && comparator.compare(a[right-1], a[right]) > 0 ) { // a[right-1] > a[right]
                swap(right - 1, right);                          // center, right
            }

            //compareCounter += 3;
        }
    }  // end manualSort()

    /** This method returns the index number of the partition:
     * the left element in the right (larger keys) sub-array.
     * Time complexity = O(N)
     * @param left - left bound of array (sub-array)
     * @param right - right bound of array (sub-array)
     * @param pivot - average estimated value for the partition array
     * @param comparator - comparator for compare two array items
     * @return - the left element in the right (larger keys) sub-array
     */
    public int partitionIt(int left, int right, T pivot, Comparator<T> comparator) {
        /*  Initial pointers positions   */
        int leftPtr = left - 1;           // right of first elem (leftPtr - left Pointer)
        int rightPtr = right + 1;         // left of pivot

        while(true) {
            /*=========-------- Move from left(e.g. 0-index) to right ----------=================*/
            // find bigger item in left (smaller) part
            while(leftPtr < right &&       // compare leftPtr with right to avoid array out of bound
                    comparator.compare(a[++leftPtr], pivot) < 0) //a[++leftPtr] < pivot
                ;  // (nop)

            /*=======------ Move from right (e.g. arr.length-1-index) to left -------=============*/
            // find smaller item in right (bigger) part
            while(rightPtr > left &&       // compare rightPtr with left to avoid array out of bound
                    comparator.compare(a[--rightPtr], pivot) > 0) // a[--rightPtr] > pivot
                ;  // (nop)

            if(leftPtr >= rightPtr) {          // if pointers cross,
                break;                         // partition DONE
            } else {                           // not crossed, so
                swap(leftPtr, rightPtr);       // swap elements
            }
        }  // end while(true)
        return leftPtr;                   // return partition
    }  // end partitionIt()

    /* For partition in simple quick sort implementation quick sort 1 */
    public int partitionIt1(int left, int right, T pivot, Comparator<T> comparator) {
        /*  Initial pointers positions   */
        int leftPtr = left-1;           // left    (after ++)
        int rightPtr = right;           // right-1 (after --)

        while(true) {
            /*=========-------- Move from left(e.g. 0-index) to right ----------=================*/
            // find bigger item in left (smaller) part
            while(comparator.compare(a[++leftPtr], pivot) < 0) //a[++leftPtr] < pivot
                ;  // (nop)

            /*=======------ Move from right (e.g. arr.length-1-index) to left -------=============*/
            // find smaller item in right (bigger) part
            while(rightPtr > 0 && comparator.compare(a[--rightPtr], pivot) > 0) // a[--rightPtr] > pivot
                ;  // (nop)

            if(leftPtr >= rightPtr) {          // if pointers cross,
                break;                         // partition DONE
            } else {                           // not crossed, so
                swap(leftPtr, rightPtr);       // swap elements
            }
        }  // end while(true)
        swap(leftPtr, right);             // restore pivot
        return leftPtr;                   // return partition
    }  // end partitionIt1()

    /* For partition in simple quick sort implementation quick sort 2 */
    public int partitionIt2(int left, int right, T pivot, Comparator<T> comparator) {
        /*  Initial pointers positions   */
        int leftPtr = left;             // right of first elem
        int rightPtr = right - 1;       // left of pivot

        while(true) {
            /*=========-------- Move from left(e.g. 0-index) to right ----------=================*/
            // find bigger item in left (smaller) part
            while(compareCounter++ > -1 && comparator.compare(a[++leftPtr], pivot) < 0) //a[++leftPtr] < pivot
            ;  // (nop)

            /*=======------ Move from right (e.g. arr.length-1-index) to left -------=============*/
            // find smaller item in right (bigger) part
            while(compareCounter++ > -1 && comparator.compare(a[--rightPtr], pivot) > 0) // a[--rightPtr] > pivot
            ;  // (nop)

            if(compareCounter++ > -1 && leftPtr >= rightPtr) {    // if pointers cross,
                break;                                            // partition DONE
            } else {                                              // not crossed, so
                swap(leftPtr, rightPtr);                          // swap elements
            }
        }  // end while(true)
        swap(leftPtr, right - 1);              // restore pivot
        return leftPtr;                        // return partition
    }  // end partitionIt1()

    public T medianOf3(int left, int right, Comparator<T> comparator) {
        int center = (left + right)/2;

        // order left & center
            if( comparator.compare(a[left], a[center]) > 0 ) // a[left] > a[center]
            swap(left, center);
        // order left & right
        if( comparator.compare(a[left], a[right]) > 0 )  // a[left] > a[right]
            swap(left, right);
        // order center & right
        if( comparator.compare(a[center],a[right]) > 0 ) // a[center] > a[right]
            swap(center, right);

        swap(center, right-1);             // put pivot on right
        return a[right-1];          // return median value
    }  // end medianOf3()


    //-----------------------------------------------------------------------------------------------------


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

    @Override
    public String toString() {
        String string = "";
        for (T item : a) {
            string += item + " ";
        }
        return string;
    }
}
