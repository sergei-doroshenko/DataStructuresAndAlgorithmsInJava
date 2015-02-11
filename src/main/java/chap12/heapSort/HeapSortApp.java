package chap12.heapSort;

import chap12.heap.Heap;
import libs.AppUtils;

import java.io.IOException;

/**
 * Created by Sergei Doroshenko on 11.02.2015.
 * The Efficiency of Heapsort
 * As we noted, heapsort runs in O(N*logN) time. Although it may be slightly slower
 * than quicksort, an advantage over quicksort is that it is less sensitive to the initial
 * distribution of data. Certain arrangements of key values can reduce quicksort to slow
 * O(N2) time, whereas heapsort runs in O(N*logN) time no matter how the data is
 * distributed.
 */
public class HeapSortApp {
    public static void main(String[] args) throws IOException {
        int size, j;

        System.out.print("Enter number of items: ");
        size = AppUtils.getInt();
        Heap<Integer> theHeap = new Heap<>(size);

        for(j=0; j<size; j++) {      // fill array with random nodes
            int random = (int)(java.lang.Math.random()*100);
            theHeap.insertAt(j, random);
            theHeap.incrementSize();
        }

        System.out.print("Random: ");
        theHeap.displayArray();  // display random array
        theHeap.displayHeap();      // display heap
        /* size/2 points to first node from last (without children) nodes */
        /*
        We can start at node N/2-1, the rightmost node with children, instead of N-1,
        the last node. Thus, we need only half as many trickle operations as we would using
        insert() N times.
         */
        for(j = size/2 - 1; j >= 0; j--) { // make random array into heap
            theHeap.trickleDown(j);
        }

        System.out.print("Heap:   ");
        theHeap.displayArray();     // dislay heap array
        theHeap.displayHeap();      // display heap

        for(j = size-1; j >= 0; j--) {   // remove from heap and store at array end
            Integer biggestNode = theHeap.remove();
            theHeap.insertAt(j, biggestNode);
        }
        System.out.print("Sorted: ");
        theHeap.displayArray();     // display sorted array
    }  // end main()
}
