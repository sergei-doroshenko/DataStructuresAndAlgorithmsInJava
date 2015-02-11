package chap12.heap;

import libs.AppUtils;

import java.io.IOException;
import java.util.Comparator;

/**
 * Created by Sergei Doroshenko on 11.02.2015.
 */
public class HeapApp {
    public static void main(String[] args) throws IOException {

        Heap<Integer> theHeap = new Heap<>(31);  // make a Heap; max size 31

        /*Heap<Integer> theHeap = new Heap<>(31, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });*/

        theHeap.insert(70);                      // insert 10 items
        theHeap.insert(40);
        theHeap.insert(50);
        theHeap.insert(20);
        theHeap.insert(60);
        theHeap.insert(100);
        theHeap.insert(80);
        theHeap.insert(30);
        theHeap.insert(10);
        theHeap.insert(90);

        /* toss() and restoreHeap() methods */
        /*theHeap.toss(100);
        theHeap.toss(80);
        theHeap.toss(30);
        theHeap.toss(10);
        theHeap.toss(90);
        theHeap.displayHeap();
        theHeap.restoreHeap();
        theHeap.displayHeap();*/



        /*Heap<Person> theHeap = new Heap<>(31, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });  // make a Heap; max size 31
        theHeap.insert(new Person(1, 30));      // insert 10 items
        theHeap.insert(new Person(2, 32));
        theHeap.insert(new Person(3, 45));
        theHeap.insert(new Person(4, 51));
        theHeap.insert(new Person(5, 28));*/

        int value, value2;
        boolean success;
        while(true) {                  // until [Ctrl]-[C]

            System.out.print("Enter first letter of ");
            System.out.print("show, insert, toss, remove, change, (h) to restore heap: ");
            int choice = AppUtils.getChar();
            switch(choice) {
                case 's':                        // show
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
