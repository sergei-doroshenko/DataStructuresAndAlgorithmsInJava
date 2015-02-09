package chap11.ProgrammingProjects;

/**
 * Created by Sergei Doroshenko on 06.02.2015.
 * Quadratic Probing
 */
public class HashTableQP {
    private DataItem[] hashArray;    // array holds hash table
    private int arraySize;
    private DataItem nonItem;        // for deleted items
    // -------------------------------------------------------------
    public HashTableQP(int size) {      // constructor
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem() {

            @Override
            public int getKey() {
                return -1;
            }
        };   // deleted item key is -1
    }

    // hash function
    public int hashFunc(int key) {
        return key % arraySize;
    }

    public void insert(DataItem item) { // insert a DataItem
        // (assumes table not full)

        int key = item.getKey();       // extract key
        int initPosition = hashFunc(key);   // hash the key
        int hashVal = initPosition;
        int step = 1; // start step 1

        // until empty cell or -1,
        while(hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1)  {
            // add the step (Quadratic Probing) and go to next cell
            hashVal = initPosition + (step * step++);
            hashVal %= arraySize;                       // wraparound if necessary
        }
        hashArray[hashVal] = item;    // insert item
    }  // end insert()

    public DataItem delete(int key) {  // delete a DataItem

        int initPosition = hashFunc(key);   // hash the key
        int hashVal = initPosition;
        int step = 1; // start step 1

        while(hashArray[hashVal] != null) { // until empty cell,

            if(hashArray[hashVal].getKey() == key) { // found the key?
                DataItem temp = hashArray[hashVal]; // save item
                hashArray[hashVal] = nonItem;       // delete item
                return temp;                        // return item
            }

            // add the step (Quadratic Probing) and go to next cell
            hashVal = initPosition + (step * step++);
            hashVal %= arraySize;                       // wraparound if necessary
        }

        return null;                  // can't find item
    }  // end delete()

    public DataItem find(int key) {   // find item with key

        int initPosition = hashFunc(key);   // hash the key
        int hashVal = initPosition;
        int step = 1; // start step 1

        while(hashArray[hashVal] != null) { // until empty cell,

            if(hashArray[hashVal].getKey() == key) { // found the key?
                return hashArray[hashVal];           // yes, return item
            }

            // add the step (Quadratic Probing) and go to next cell
            hashVal = initPosition + (step * step++);
            /*
            while hashVal < arraySize, hashVal % arraySize = hashVal (e.g. 17 % 20 = 17)
            when hashVal = arraySize, hashVal % arraySize = 0 (e.g. 20 % 20 = 0)
             */
            hashVal %= arraySize;      // wraparound if necessary
        }
        return null;                  // can't find item
    }

    @Override
    public String toString() {
        String str = "Table: ";

        for(int j = 0; j < arraySize; j++) {
            if(hashArray[j] != null) {
                str += hashArray[j].getKey() + " ";
            } else {
                str += "** ";
            }
        }
        str += "\n";
        return str;
    }

}
