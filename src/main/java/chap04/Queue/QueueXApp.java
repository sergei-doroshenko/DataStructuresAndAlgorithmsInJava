package chap04.Queue;

/**
 * Created by user on 19.01.2015.
 */
public class QueueXApp {
    public static void main(String[] args) {

        QueueX queueX = new QueueX(5);  // queue holds 5 items

        queueX.insert(10);   // insert 5 items
        queueX.insert(20);
        queueX.insert(30);
        queueX.insert(40);
        queueX.insert(10);



        queueX.remove();              // remove 3 items
        queueX.remove();              //    (10, 20, 30)
        queueX.remove();

        System.out.printf("Size: %d, is queue empty: %s, is full: %s", queueX.size(), queueX.isEmpty(), queueX.isFull());
        queueX.insert(50);            // insert 3 more items
        queueX.insert(60);            //    (wraps around)
        queueX.insert(70);
        // queueX.insert(505);  // Exception in thread "main" java.lang.RuntimeException: Queue is full!
        System.out.printf("\nSize: %d, is queue empty: %s, is full: %s\n", queueX.size(), queueX.isEmpty(), queueX.isFull());

        while( !queueX.isEmpty() ) {   // remove and display
            // all items
            long n = queueX.remove();  // (40, 50, 60, 70, 80)
            System.out.print(n);
            System.out.print(" ");
        }
        System.out.printf("\nSize: %d, is queue empty: %s, is full: %s",queueX.size(), queueX.isEmpty(), queueX.isFull());
        // Prints: Size: 6, is queue empty: true, is full: false
        // To fix try to uncomment to strings in QueueX class
    }  // end main()
}
