package chap11.hashChainTree;

import libs.AppUtils;

import java.io.IOException;

/**
 * Created by Sergei Doroshenko on 09.02.2015.
 */
public class HashChainTreeApp {
    public static void main(String[] args) throws IOException {
        int aKey, size, n, keysPerCell = 100;
        // get sizes
        System.out.print("Enter size of hash table: ");
        size = AppUtils.getInt();
        System.out.print("Enter initial number of items: ");
        n = AppUtils.getInt();
        // make table
        HashTableTree<Integer> theHashTable = new HashTableTree(size);

        for(int j = 0; j < n; j++) {        // insert data
            aKey = (int)(Math.random() * keysPerCell * size);
            theHashTable.insert(aKey);
        }

        while(true) {                   // interact with user
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, delete, or find: ");
            char choice = AppUtils.getChar();
            switch(choice) {
                case 's':
                    theHashTable.displayTable();
                    break;
                case 'i':
                    System.out.print("Enter key value to insert: ");
                    aKey = AppUtils.getInt();

                    theHashTable.insert(aKey);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = AppUtils.getInt();
                    theHashTable.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = AppUtils.getInt();
                    Integer integer = theHashTable.find(aKey);
                    if(integer != null)
                        System.out.println("Found " + aKey);
                    else
                        System.out.println("Could not find " + aKey);
                    break;
                default:
                    System.out.print("Invalid entry\n");
            }  // end switch
        }  // end while
    }  // end main()
}
