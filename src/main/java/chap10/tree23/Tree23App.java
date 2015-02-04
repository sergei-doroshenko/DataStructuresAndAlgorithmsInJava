package chap10.tree23;

import chap10.tree234.DataItem;
import chap10.tree234.TraverseHandler;
import libs.AppUtils;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Sergei Doroshenko on 03.02.2015.
 * The search times for a 2-3-4 tree and for a balanced binary tree
 * such as a red-black tree are approximately equal, and are both O(logN).
 */
public class Tree23App {
    public static void main(String[] args) throws IOException {
      long value;
      Tree23 theTree = new Tree23();

      theTree.insert(10);
      theTree.insert(20);
      theTree.insert(30);
      theTree.insert(40);
      theTree.insert(50);
      theTree.insert(60);
      theTree.insert(70);
      theTree.insert(80);
      theTree.insert(90);
      theTree.insert(12);
      theTree.insert(14);
      theTree.insert(15);
      theTree.insert(11);
      theTree.insert(31);

      Random r = new Random();
      for (int i = 0; i < 100; i++) {
          theTree.insert(r.nextInt(99) + i);
      }


      char choice = ' ';
      while(choice != '0') {
          System.out.println("------------------------------------------------------------------------------");
         System.out.println("Commands list:");
         System.out.println("s - show, i - insert, f - find, m - find minimum, t - traverse, 'Enter' - exit");
          System.out.println("--------------------------------------------------------------------------------");
          System.out.print("Enter your choice: ");
         choice = AppUtils.getChar();

         switch(choice) {
            case 's': {
               theTree.displayTree();
               break;}
            case 'i': {
               System.out.print("Enter value to insert: ");
               value = AppUtils.getInt();
               theTree.insert(value);
                break;}
            case 'f': {
               System.out.print("Enter value to find: ");
               value = AppUtils.getInt();
               int found = theTree.find(value);
               if(found != -1)
                  System.out.println("Found " + value);
               else
                  System.out.println("Could not find " + value);
               break; }
             case 'm': {
                 System.out.println("Minimum is " + theTree.min());
                 break;
             }
             case 't': {
                 System.out.println("Traversing tree:");
                 theTree.traverse(new TraverseHandler() {
                     @Override
                     public void handle(DataItem dataItem) {
                         System.out.print(dataItem);
                     }
                 });
                 System.out.println("/");
                 break;
             }
            case '0': {
                System.out.print("Exit app...");
                break; }
            default: {
                System.out.print("Invalid entry\n");
            }
         }  // end switch
      }  // end while
    }  // end main()
}
