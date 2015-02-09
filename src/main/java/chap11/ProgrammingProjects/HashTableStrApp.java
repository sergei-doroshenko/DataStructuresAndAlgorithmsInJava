package chap11.ProgrammingProjects;

import libs.AppUtils;

import java.io.IOException;

/**
 * Created by Sergei Doroshenko on 06.02.2015.
 */
public class HashTableStrApp {
    public static void main(String[] args) throws IOException {
        DataItemStr sDataItem;
        String sKey;
        int size;
        // get sizes
        System.out.print("Enter size of hash table: ");
        size = AppUtils.getInt();

        // make table
        HashTableStr hashTable = new HashTableStr(size);

        while(true) {                  // interact with user
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, delete, or find: ");
            char choice = AppUtils.getChar();
            switch(choice) {
                case 's':
                    hashTable.displayTable();
                    break;
                case 'i':
                    System.out.print("Enter key value to insert: ");
                    sKey = AppUtils.getString();
                    sDataItem = new DataItemStr(sKey);
                    hashTable.insert(sDataItem);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    sKey = AppUtils.getString();
                    hashTable.delete(sKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    sKey = AppUtils.getString();
                    sDataItem = hashTable.find(sKey);
                    if(sDataItem != null) {
                        System.out.println("Found " + sKey);
                    } else {
                        System.out.println("Could not find " + sKey);
                    }
                    break;
                default:
                    System.out.print("Invalid entry\n");
            }  // end switch
        }  // end while
    }  // end main()
}
