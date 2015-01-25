package chap03.InsertSort;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Created by Sergei_Doroshenko on 1/12/2015.
 */
public class ArrayIns {
    private long[] a;                 // ref to array a
    private int nElems;               // number of data items
    //--------------------------------------------------------------
    public ArrayIns(int max)          // constructor
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
    public void display()             // displays array contents
    {
        for(int j=0; j<nElems; j++)       // for each element,
            System.out.print(a[j] + " ");  // display it
        System.out.println("");
    }
    //--------------------------------------------------------------
    public String insertionSort() {
        long start = System.currentTimeMillis();
        long compare = 0, copy = 0;
        int in, out;
        // Here we start from 1-index because at next step we compare
        // a[1] with a[0]. If we start from 0-index, there is no elements in left
        // part for compare
        for(out = 1; out < nElems; out++) {  // out is dividing line

            long temp = a[out];              // remove marked item
            in = out;                        // start shifts at out

            //while(in > 0 && a[in-1] >= temp){ // until one is smaller,
            while(in > 0) {
                compare++;
                if (a[in-1] >= temp) {
                    a[in] = a[in - 1];             // shift item to right
                    copy++;
                    --in;                        // go left one position
                    continue;
                }
                break;
            }

            a[in] = temp;                    // insert marked item

        }  // end for
        double time = (System.currentTimeMillis() - start)/1000.0;

        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols(Locale.getDefault());
        unusualSymbols.setGroupingSeparator(',');
        DecimalFormat formatter = new DecimalFormat("#,##0.#", unusualSymbols);
        formatter.setGroupingSize(3);
        return String.format("Compare: %3s Copy: %3s Elapsed time: %.3f", formatter.format(compare), formatter.format(copy), time);
    }  // end insertionSort()
    //--------------------------------------------------------------

    public long median() {
        if (nElems % 2 == 1) return a[nElems/2];
        return (a[nElems/2 - 1] + a[nElems/2]) / 2;
    }

    public void noDups() {
        int insPos = 1;
        int curInd = 0;
        int nextElemInd = 0;
        long element;

        while (curInd < nElems - 1 && nextElemInd < nElems) {
            element = a[curInd];
            nextElemInd = curInd + 1;

            while (nextElemInd < nElems && element == a[nextElemInd]) { // findL where dups ends
                nextElemInd++;                  // points to the next (not dubs) element
            }

            if (nextElemInd < nElems) {
                if (nextElemInd > curInd + 1) {    // dubs found
                    a[insPos++] = a[nextElemInd];
                    curInd = nextElemInd;
                } else {                            // if no dubs found
                    a[insPos++] = a[nextElemInd];
                    curInd++;
                }
            }
        }

        nElems = insPos;
    }
}
