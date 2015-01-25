package chap02.OrderedArray;

/**
 * Created by Sergei on 09.01.2015.
 */
public class OrderedArray {
    private long[] a;                 // ref to array a
    private int nElems;               // number of data items
    //-----------------------------------------------------------
    public OrderedArray(int max) {          // constructor

        a = new long[max];             // create array
        nElems = 0;
    }
    //-----------------------------------------------------------
    public int size() { return nElems; }
    //-----------------------------------------------------------

    public long get(int index) {
        if (index < 0 || index > nElems - 1) throw new IndexOutOfBoundsException("No such index!");
        return a[index];
    }

    public int findL(long searchKey) {
        int lowerBound = 0;
        int upperBound = nElems-1;

        while(lowerBound <= upperBound) {
            int curIn = (lowerBound + upperBound ) / 2;
            long curVal = a[curIn];

            if (curVal == searchKey) {
                return curIn;              // found it

            } else if (curVal < searchKey) {  // divide range
                lowerBound = curIn + 1;  // it's in upper half
            } else {
                upperBound = curIn - 1;  // it's in lower half
            }
        }  // end while
        return nElems; // can't findL it

    }  // end find()
    //-----------------------------------------------------------

    public int findR(long key) {
        return findRec(key, 0, nElems);
    }

    private int findRec(long key, int low, int high) { // Method implemented with recursive algorithm
        if (low > high) return -1; // can't findL it QUIT from recursion

        int mid = (low + high) / 2;
        long midVal = a[mid];

        if (midVal == key) {                   // found it
            return mid;
        } else if (midVal < key) {             // it's in upper half
            return findRec(key, mid + 1, high);
        } else {                               // it's in lower half
            return findRec(key, low, mid - 1);
        }
    }

    public void insert(long value) {   // put element into array

        int j;
        for(j = 0; j < nElems; j++) {       // findL where it goes
            if (a[j] > value) {            // (linear search)
                break;
            }
        }

        for(int k = nElems; k > j; k--) {   // move bigger ones up
            a[k] = a[k - 1];
        }

        a[j] = value;                  // insert it
        nElems++;                      // increment size
    }  // end insert()
    //-----------------------------------------------------------

    public int insertB(long value) {

        int lowerBound = 0;
        int upperBound = nElems-1;
        int curIn;

        while(true) {
            curIn = (lowerBound + upperBound ) / 2;

            if (a[curIn] < value && a[curIn + 1] > value) {
                for(int k = nElems; k > curIn + 1; k--) {      // move bigger ones up
                    a[k] = a[k - 1];
                }

                a[curIn + 1] = value;                   // found place to insert it
                nElems++;
                return curIn + 1;              // found it

            } else if (lowerBound > upperBound) {
                a[nElems] = value;
                nElems++;
                return nElems - 1;             // can't findL it

            } else {                      // divide range
                if (a[curIn] < value) {
                    lowerBound = curIn + 1; // it's in upper half
                } else {
                    upperBound = curIn - 1; // it's in lower half
                }

            }  // end else divide range
        }  // end while
    }

    public boolean delete(long value) {
        int j = findL(value);

        if(j==nElems) {                 // can't findL it
            return false;
        } else {                           // found it

            for(int k=j; k<nElems; k++) { // move bigger ones down
                a[k] = a[k + 1];
            }

            nElems--;                   // decrement size
            return true;
        }
    }  // end delete()
    //-----------------------------------------------------------
    public void display() {            // displays array contents

        for(int j=0; j<nElems; j++) {       // for each element,
            System.out.print(a[j] + " ");  // display it
        }

        System.out.println("");
    }
    //-----------------------------------------------------------

    public static OrderedArray merge (OrderedArray a, OrderedArray b) {
        int resLengh = a.size() + b.size();
        OrderedArray res = new OrderedArray(resLengh);
        int aIn = 0; // index for a - array
        int bIn = 0; // index for b - array

        for (int i = 0; i < resLengh; i++) {
            if (aIn == a.size()) { // have got an end of a - array
                res.insert(b.get(bIn++));
            } else if (bIn == b.size()) { // have got an end of a - array
                res.insert(a.get(aIn++));
            } else {

                if (a.get(aIn) < b.get(bIn)) { // compare first elements of a- and b- arrays
                    res.insert(a.get(aIn++));

                } else {
                    res.insert(b.get(bIn++));
                }
            }
        }

        return res;
    }
}
