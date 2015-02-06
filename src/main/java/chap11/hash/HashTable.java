package chap11.hash;

/**
 * Created by Sergei Doroshenko on 06.02.2015.
 */
public class HashTable {
    private DataItem[] hashArray;    // array holds hash table
    private int arraySize;
    private DataItem nonItem;        // for deleted items
    // -------------------------------------------------------------
    public HashTable(int size) {      // constructor
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1);   // deleted item key is -1
    }

    public int hashFunc(int key) {
        return key % arraySize;       // hash function
    }

    public void insert(DataItem item) { // insert a DataItem
        // (assumes table not full)

        int key = item.getKey();      // extract key
        int hashVal = hashFunc(key);  // hash the key
        // until empty cell or -1,
        while(hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1)  {
            ++hashVal;                 // go to next cell
            hashVal %= arraySize;      // wraparound if necessary
        }
        hashArray[hashVal] = item;    // insert item
    }  // end insert()

    public DataItem delete(int key) {  // delete a DataItem

        int hashVal = hashFunc(key);  // hash the key

        while(hashArray[hashVal] != null) { // until empty cell,
            if(hashArray[hashVal].getKey() == key) { // found the key?
                DataItem temp = hashArray[hashVal]; // save item
                hashArray[hashVal] = nonItem;       // delete item
                return temp;                        // return item
            }
            ++hashVal;                 // go to next cell
            hashVal %= arraySize;      // wraparound if necessary
        }

        return null;                  // can't find item
    }  // end delete()

    public DataItem find(int key) {   // find item with key

        int hashVal = hashFunc(key);  // hash the key

        while(hashArray[hashVal] != null) { // until empty cell,

            if(hashArray[hashVal].getKey() == key) { // found the key?
                return hashArray[hashVal];           // yes, return item
            }
            ++hashVal;                 // go to next cell
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

    // -------------------------------------------------------------
    public void displayTable() {
        System.out.print("Table: ");
        for(int j = 0; j < arraySize; j++) {
            if(hashArray[j] != null) {
                System.out.print(hashArray[j].getKey() + " ");
            } else {
                System.out.print("** ");
            }
        }
        System.out.println("");
    }
    // -------------------------------------------------------------
}
