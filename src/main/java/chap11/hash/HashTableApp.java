package chap11.hash;

import libs.AppUtils;

import java.io.IOException;

/**
 * Created by Sergei Doroshenko on 06.02.2015.
 */
public class HashTableApp {
    public static void main(String[] args) throws IOException {
        DataItem aDataItem;
        int aKey, size, n, keysPerCell;
        // get sizes
        System.out.print("Enter size of hash table: ");
        size = AppUtils.getInt();
        System.out.print("Enter initial number of items: ");
        n = AppUtils.getInt();
        keysPerCell = 10;
        // make table
        HashTable theHashTable = new HashTable(size);

        for(int j = 0; j < n; j++) {       // insert data
            aKey = (int)(java.lang.Math.random() * keysPerCell * size);
            aDataItem = new DataItem(aKey);
            theHashTable.insert(aDataItem);
        }

        while(true) {                  // interact with user
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, delete, or find: ");
            char choice = AppUtils.getChar();
            switch(choice)
            {
                case 's':
                    theHashTable.displayTable();
                    break;
                case 'i':
                    System.out.print("Enter key value to insert: ");
                    aKey = AppUtils.getInt();
                    aDataItem = new DataItem(aKey);
                    theHashTable.insert(aDataItem);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = AppUtils.getInt();
                    theHashTable.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = AppUtils.getInt();
                    aDataItem = theHashTable.find(aKey);
                    if(aDataItem != null) {
                        System.out.println("Found " + aKey);
                    }
                    else
                        System.out.println("Could not find " + aKey);
                    break;
                default:
                    System.out.print("Invalid entry\n");
            }  // end switch
        }  // end while
    }  // end main()
}
