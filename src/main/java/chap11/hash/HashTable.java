package chap11.hash;

import libs.Hash;

/**
 * Created by Sergei Doroshenko on 06.02.2015.
 */
public class HashTable {
    private DataItem[] hashArray;    // array holds hash table
    private int arraySize;
    private DataItem nonItem;        // for deleted items
    private int nItems;
    // -------------------------------------------------------------
    public HashTable(int size) {      // constructor
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1);   // deleted item key is -1
    }

    // hash function
    public int hashFunc(int key) {
        return key % arraySize;
    }

    // folding hash function

    /**
     * Folding hash-function, divides the key into groups and sum it in hash value
     * @param key - hashing value
     * @return - hash value
     */
    public int hashFunc2(int key) {

        double arrDc = (int) Math.log10(arraySize) + 1; // array size decimal count (e.g. for 15 = 2, for 180 = 3 etc.)
        System.out.println("Array size decimal count = " + arrDc);
        System.out.println("Key = " + key);
        double keyDc = (int) Math.log10(key) + 1; // key decimal count (e.g. for 123456798 = 9)
        System.out.println("Key decimal count = " + keyDc);
        int groupCount = (int) Math.ceil(keyDc / arrDc);
        System.out.println("Key group count = " + groupCount);
        int hash = 0;
        double divider = Math.pow(10, arrDc); // key divider (e.g. 10, 100, 1000 etc.)
        System.out.println("Group divider = " + divider);
        // Get numbers groups and sum them in hash value
        while (key > 0) {
            int group = (int) (key % divider);
            key /= divider;
            System.out.println("Group = " + group);
            hash += group;
        }
        System.out.println("Hash = " + hash + " hash % arraySize = " + (hash %= arraySize));

        return hash;
    }

    public int hashFunc3(int key) {
        return Hash.hash(key, arraySize);
    }

    private void rehash() {
        DataItem[] temp = hashArray;
        arraySize *= 2;
        hashArray = new DataItem[arraySize];

        for (DataItem item : temp) {
            if (item != null && item.getKey() != -1)
                insert(item);
        }
    }

    public void insert(DataItem item) { // insert a DataItem
        // (assumes table not full)
        if (((double) nItems)/arraySize > 0.5) rehash();  // check loadFactor than rehash
        int key = item.getKey();      // extract key
            int hashVal = hashFunc3(key);  // hash the key
        // until empty cell or -1,
        while(hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1)  {
            ++hashVal;                 // go to next cell
            hashVal %= arraySize;      // wraparound if necessary
        }
        hashArray[hashVal] = item;    // insert item
        nItems++;
    }  // end insert()

    public DataItem delete(int key) {  // delete a DataItem

        int hashVal = hashFunc3(key);  // hash the key

        while(hashArray[hashVal] != null) { // until empty cell,
            if(hashArray[hashVal].getKey() == key) { // found the key?
                DataItem temp = hashArray[hashVal]; // save item
                hashArray[hashVal] = nonItem;       // delete item
                nItems--;
                return temp;                        // return item
            }
            ++hashVal;                 // go to next cell
            hashVal %= arraySize;      // wraparound if necessary
        }

        return null;                  // can't find item
    }  // end delete()

    public DataItem find(int key) {   // find item with key

        int hashVal = hashFunc3(key);  // hash the key

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
