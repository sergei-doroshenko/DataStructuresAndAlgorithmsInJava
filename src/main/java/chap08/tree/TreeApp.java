package chap08.tree;

import libs.AppUtils;

import java.io.IOException;

/**
 * Created by Sergei Doroshenko on 30.01.2015.
 */
public class TreeApp {
    public static void main(String[] args) throws IOException {
        int value;
        Tree theTree = new Tree();

        theTree.insert(50, 1.5);
        theTree.insert(25, 1.2);
        theTree.insert(75, 1.7);
        theTree.insert(12, 1.5);
        theTree.insert(37, 1.2);
        theTree.insert(43, 1.7);
        theTree.insert(30, 1.5);
        theTree.insert(33, 1.2);
        theTree.insert(87, 1.7);
        theTree.insert(93, 1.5);
        theTree.insert(97, 1.5);

        while(true) {
            System.out.print("Enter first letter of show: \n");
            System.out.print("(i)-insert, (f)-find, (d)-delete, (m)-find maximum or (t)-traverse: ");
            int choice = AppUtils.getChar();
            switch(choice) {
                case 's':
                    theTree.displayTree();
                    break;
                case 'i':
                    System.out.print("Enter value to insert: ");
                    value = AppUtils.getInt();
                    theTree.insert(value, value + 0.9);
                    break;
                case 'f':
                    System.out.print("Enter value to find: ");
                    value = AppUtils.getInt();
                    Tree.Node found = theTree.find(value);
                    if(found != null) {
                        System.out.print("Found: ");
                        found.displayNode();
                        System.out.print("\n");
                    } else {
                        System.out.print("Could not find ");
                        System.out.print(value + '\n');
                    }
                    break;
                case 'd':
                    System.out.print("Enter value to delete: ");
                    value = AppUtils.getInt();
                    boolean didDelete = theTree.delete(value);
                    if(didDelete) {
                        System.out.print("Deleted " + value + '\n');
                    } else {
                        System.out.print("Could not delete ");
                        System.out.print(value + '\n');
                    }
                    break;
                case 'm':
                    System.out.println("Max: " + theTree.max());
                    break;
                case 't':
                    System.out.print("Enter type 1, 2 or 3: ");
                    value = AppUtils.getInt();
                    theTree.traverse(value);
                    break;
                default:
                    System.out.print("Invalid entry\n");
            }  // end switch
        }  // end while
    }  // end main()
}
