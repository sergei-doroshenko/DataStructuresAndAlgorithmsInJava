package chap11.ProgrammingProjects;

/**
 * Created by Sergei Doroshenko on 06.02.2015.
 */
public class HashTableStr {
    private DataItemStr[] hashArray;    // array holds hash table
    private int arraySize;
    private DataItemStr nonItem;        // for deleted items
    // -------------------------------------------------------------
    public HashTableStr(int size) {      // constructor
        arraySize = size;
        hashArray = new DataItemStr[arraySize];
        nonItem = new DataItemStr("-1");   // deleted item key is -1
    }

    public int hashFunc(int key) {
        return key % arraySize;       // hash function
    }

    public int hashFunc3(String key) {
        int hashVal = 0;

        for(int j = 0; j < key.length(); j++) {// left to right
            int letter;
            if (key.charAt(j) == ' ') letter = 0; // space char code
            else letter = key.charAt(j) - 96; // get char code
            hashVal = (hashVal * 27 + letter) % arraySize; // mod
        }

        return hashVal; // no mod
    } // end hashFunc3()

    public void insert(DataItemStr item) { // insert a DataItem
        // (assumes table not full)

        String key = item.getKey();      // extract key
        int hashVal = hashFunc3(key);  // hash the key
        // until empty cell or -1,
        while(hashArray[hashVal] != null && hashArray[hashVal].getKey() != "-1")  {
            ++hashVal;                 // go to next cell
            hashVal %= arraySize;      // wraparound if necessary
        }
        hashArray[hashVal] = item;    // insert item
    }  // end insert()

    public DataItemStr delete(String key) {  // delete a DataItem

        int hashVal = hashFunc3(key);  // hash the key

        while(hashArray[hashVal] != null) { // until empty cell,
            if(hashArray[hashVal].getKey().equals(key)) { // found the key?
                DataItemStr temp = hashArray[hashVal]; // save item
                hashArray[hashVal] = nonItem;       // delete item
                return temp;                        // return item
            }
            ++hashVal;                 // go to next cell
            hashVal %= arraySize;      // wraparound if necessary
        }

        return null;                  // can't find item
    }  // end delete()

    public DataItemStr find(String key) {   // find item with key

        int hashVal = hashFunc3(key);  // hash the key

        while(hashArray[hashVal] != null) { // until empty cell,

            if(hashArray[hashVal].getKey().equals(key)) { // found the key?
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
        System.out.println("----------------------------------------------");
        System.out.println("Table: ");
        System.out.println("----------------------------------------------");

        for(int j = 0; j < arraySize; j++) {
            System.out.print(j + ". ");
            if(hashArray[j] != null) {
                System.out.print(hashArray[j].getKey());
            } else {
                System.out.print("** ");
            }
            System.out.print("\n");
        }
        System.out.println("----------------------------------------------");
    }
    // -------------------------------------------------------------
}
