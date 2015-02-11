package chap12.treeHeap;

import chap08.tree.BinaryTree;
import libs.AppUtils;

import java.io.IOException;

/**
 * Created by Sergei Doroshenko on 11.02.2015.
 */
public class TreeHeapApp {
    public static void main(String[] args) throws IOException {

        TreeHeap<Integer> theHeap = new TreeHeap<>();

        theHeap.insert(50);
        theHeap.insert(25);
        theHeap.insert(75);
        theHeap.insert(12);
        theHeap.insert(37);
        theHeap.insert(43);
        theHeap.insert(30);
        theHeap.insert(33);
        theHeap.insert(87);
        theHeap.insert(93);
        theHeap.insert(97);

        int value, value2;
        boolean success;
        while(true) {                  // until [Ctrl]-[C]

            System.out.print("Enter first letter of ");
            System.out.print("show, insert, toss, remove, change, (h) to restore heap: ");
            int choice = AppUtils.getChar();
            switch(choice) {
                case 's':                        // show
                    System.out.println("size: " + theHeap.size());
                    theHeap.displayHeap();
                    break;
                case 'i':                        // insert
                    System.out.print("Enter value to insert: ");
                    value = AppUtils.getInt();
                    success = theHeap.insert(value);
                    if( !success ) {
                        System.out.println("Can't insert; heap full");
                    }
                    break;
                case 't':
                    System.out.print("Enter value to toss: ");
                    value = AppUtils.getInt();
                    success = theHeap.toss(value);
                    if( !success ) {
                        System.out.println("Can't toss; heap full");
                    }
                    break;
                case 'r':                        // remove
                    if( !theHeap.isEmpty() ) {
                        System.out.println("Removed: " + theHeap.remove());
                    } else {
                        System.out.println("Can't remove; heap empty");
                    }
                    break;
                case 'c':                        // change
                    System.out.print("Enter current index of item: ");
                    value = AppUtils.getInt();
                    System.out.print("Enter new key: ");
                    value2 = AppUtils.getInt();
                    success = theHeap.change(value, value2);
                    if ( !success ) {
                        System.out.println("Invalid index");
                    }
                    break;
                case 'h':
                    theHeap.restoreHeap();
                    System.out.println("Heap restored.");
                    break;
                default:
                    System.out.println("Invalid entry\n");
            }  // end switch
        }  // end while
    }  // end main()
}
