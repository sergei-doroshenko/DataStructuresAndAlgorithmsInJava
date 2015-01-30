package chap07.ProgrammingProjects;

import chap03.ObjectSort.ArrayOG;

import java.util.Comparator;

/**
 * Created by user on 29.01.2015.
 */
public class ArrayPart<T> extends ArrayOG<T> {

    public ArrayPart(int max) {
        super(max);
    }

    public int partitionIt(int left, int right, Comparator<T> comparator) {
        /*  Initial pointers positions   */
        int leftPtr = left - 1;           // right of first elem (leftPtr - left Pointer)
        int rightPtr = right;         // left of pivot
        T pivot = a[nElems - 1];
        System.out.println("Pivot is " + pivot);
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
        swap(leftPtr, right);             // restore pivot
        return leftPtr;                   // return partition
    }  // end partitionIt()
}
