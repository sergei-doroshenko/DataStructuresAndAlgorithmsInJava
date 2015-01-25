package chap02.HighArray;

/**
 * Created by Sergei on 09.01.2015.
 */
public class HighArray {
    private long[] a;                 // ref to array a
    private int nElems;               // number of data items
    //-----------------------------------------------------------
    public HighArray(int max){         // constructor

        a = new long[max];                 // create the array
        nElems = 0;                        // no items yet
    }
    //-----------------------------------------------------------

    public int size() { return nElems; }

    public boolean find(long searchKey) { // findL specified value
        int j;
        for (j = 0; j < nElems; j++) {           // for each element,
            if (a[j] == searchKey) {         // found item?
                break;                       // exit loop before end
            }
        }

        if (j == nElems) {                    // gone to end?
            return false;                   // yes, can't findL it
        } else {
            return true;                    // no, found it
        }
    }  // end findL()
    //-----------------------------------------------------------
    public void insert(long value) {    // put element into array
        a[nElems] = value;             // insert it
        nElems++;                      // increment size
    }
    //-----------------------------------------------------------
    public boolean delete(long value) {
        int j;
        for(j=0; j<nElems; j++)        // look for it
            if( value == a[j] )
                break;
        if(j==nElems)                  // can't findL it
            return false;
        else                           // found it
        {
            for(int k=j; k<nElems; k++) // move higher ones down
                a[k] = a[k+1];
            nElems--;                   // decrement size
            return true;
        }
    }  // end delete()
    //-----------------------------------------------------------
    public void display() {            // displays array contents

        for(int j=0; j<nElems; j++)       // for each element,
            System.out.print(a[j] + " ");  // display it
        System.out.println("");
    }
    //-----------------------------------------------------------

    public long get(int index) {
        if (index < 0 || index > nElems) throw new IndexOutOfBoundsException("No such index: " + index);
        return a[index];
    }

    public int getMax() {
        int maxIndex = 0;
        for (int i = 1; i < nElems; i++) {
            if (a[i] > a[maxIndex]) maxIndex = i;
        }
        return maxIndex;
    }

    public long removeMax() {
        long max = get(getMax());
        delete(max);
        return max;
    }

    public HighArray noDups() {
        long temp;
        for (int i = 0; i < size(); i++) {
            temp = get(i);
            int overlap = 0;
            for (int j = 0; j < size(); j++) {
                if (temp == get(j)) {
                    overlap++;
                    if (overlap > 1) {
                        delete(get(j));
                    }
                }
            }
        }

        return this;
    }
}
