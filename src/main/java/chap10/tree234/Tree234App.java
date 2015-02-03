package chap10.tree234;

import libs.AppUtils;

import java.io.IOException;

/**
 * Created by Sergei Doroshenko on 03.02.2015.
 * The search times for a 2-3-4 tree and for a balanced binary tree
 * such as a red-black tree are approximately equal, and are both O(logN).
 */
public class Tree234App {
    public static void main(String[] args) throws IOException {
      long value;
      Tree234 theTree = new Tree234();

      theTree.insert(50);
      theTree.insert(40);
      theTree.insert(60);
      theTree.insert(30);
      theTree.insert(70);

      while(true) {
         System.out.print("Enter first letter of ");
         System.out.print("show, insert, or find: ");
         char choice = AppUtils.getChar();

         switch(choice) {
            case 's':
               theTree.displayTree();
               break;
            case 'i':
               System.out.print("Enter value to insert: ");
               value = AppUtils.getInt();
               theTree.insert(value);
                break;
            case 'f':
               System.out.print("Enter value to find: ");
               value = AppUtils.getInt();
               int found = theTree.find(value);
               if(found != -1)
                  System.out.println("Found "+value);
               else
                  System.out.println("Could not find "+value);
               break;
            default:
               System.out.print("Invalid entry\n");
            }  // end switch
         }  // end while
      }  // end main()
}
