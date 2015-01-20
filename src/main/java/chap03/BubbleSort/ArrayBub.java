package chap03.BubbleSort;

import java.util.Comparator;

/**
 * Created by Sergei_Doroshenko on 1/12/2015.
 */
public class ArrayBub {

    private long[] a;                 // ref to array a
    private int nElems;               // number of data items
    //--------------------------------------------------------------
    public ArrayBub(int max)          // constructor
    {
        a = new long[max];                 // create the array
        nElems = 0;                        // no items yet
    }
    //--------------------------------------------------------------
    public void insert(long value)    // put element into array
    {
        a[nElems] = value;             // insert it
        nElems++;                      // increment size
    }
    //--------------------------------------------------------------
    public void display() {             // displays array contents
        for(int j=0; j<nElems; j++)       // for each element,
            System.out.printf("%2d ", a[j]);  // display it
        System.out.println("");
    }
    //--------------------------------------------------------------
    public String bubbleSort() {
        long start = System.currentTimeMillis();
        int out, in, compare = 0, copy = 0;

        for(out = nElems-1; out > 1; out--) {  // outer loop (backward)
            for (in = 0; in < out; in++) {      // inner loop (forward)
                compare++;
                if (a[in] > a[in + 1]) {       // out of order?
                    swap(in, in + 1);          // swap them
                    copy++;
                }
            }
        }
        return String.format("Compare: %3d Copy: %3d Elapsed time: %3.3f", compare, copy, (System.currentTimeMillis() - start)/1000.0);
    }  // end bubbleSort()
    //--------------------------------------------------------------

    public String shakerSort() {
        long start = System.currentTimeMillis();
        int out, in = 0, compare = 0, copy = 0;

        for(out = nElems-1; out > 0; out--) {  // outer loop (backward)
            if (in < out) {
                for (; in < out; in++) {      // inner loop (forward)
                    //if (a[in] > a[in + 1]) {       // out of order?
                    compare++;
                    if (a[in] > a[in + 1]) {
                        swap(in, in + 1);          // swap them
                        copy++;
                    }
                }
            } else {
                for (; in > 0; in--) {      // inner loop (backward)
                    compare++;
                    if (a[in] < a[in - 1]) {
                        swap(in, in - 1);          // swap them
                        copy++;
                    }
                }
            }
        }
        return String.format("Compare: %3d Copy: %3d Elapsed time: %3.3f", compare, copy, (System.currentTimeMillis() - start)/1000.0);
    }  // end shakerSort()

    public String oddEvenSort() {
        long start = System.currentTimeMillis();
        int in, compare = 0, copy = 0;
        boolean isCange = true;

        while (isCange) {
            isCange = false;
            for (in = 0; in < nElems - 1; in += 2) {
                compare++;
                if (a[in] > a[in + 1]) {       // out of order?
                    swap(in, in + 1);          // swap them
                    copy++;
                    isCange = true;
                }
            }

            for (in = 1; in < nElems - 1; in += 2) {
                compare++;
                if (a[in] > a[in + 1]) {       // out of order?
                    swap(in, in + 1);          // swap them
                    copy++;
                    isCange = true;
                }
            }

        }
        return String.format("Compare: %3d Copy: %3d Elapsed time: %3.3f", compare, copy, (System.currentTimeMillis() - start)/1000.0);
    }

    private void swap(int one, int two) {
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }
//--------------------------------------------------------------
}
