package chap05.LinkQueue;

/**
 * Created by user on 21.01.2015.
 */
public class LinkQueueApp {
    public static void main(String[] args) {
        QueueSLL<Integer> theQueue = new QueueSLL<>();
        theQueue.insert(20);                 // insert items
        theQueue.displayQueue();             // display queue
        theQueue.insert(40);
        theQueue.displayQueue();             // display queue

        theQueue.insert(60);                 // insert items
        theQueue.displayQueue();             // display queue
        theQueue.insert(80);
        theQueue.displayQueue();             // display queue

        System.out.println("Remove: " + theQueue.remove());                   // remove items
        theQueue.displayQueue();             // display queue
        System.out.println("Remove: " + theQueue.remove());                   // remove items
        theQueue.displayQueue();             // display queue
    }  // end main()
}
