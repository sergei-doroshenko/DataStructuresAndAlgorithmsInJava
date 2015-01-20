package chap04.Deque;

/**
 * Created by Sergei Doroshenko on 19.01.2015.
 */
public class Deque {
    private int maxSize;
    private long[] queArray;
    private int front;
    private int rear;
    private int nItems;
    //--------------------------------------------------------------
    public Deque(int s) {               // constructor
        maxSize = s;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }
    //--------------------------------------------------------------
    public void insertLeft(long j) {              // put item at rear of queue
        if (isFull()) throw new RuntimeException("Queue is full!");

        if (front == 0) {
            front = maxSize - 1;
        }
        queArray[--front] = j;                 // decrement front and insert
        nItems++;                            // one more item
    }

    private void move (int start, int end, int offset) {
        if (start < end) {
            for (int i = end; i <= start; i--) {
                queArray[i + offset] = queArray[i];
            }
        }
    }

    public void insertRight(long j) {              // put item at rear of queue
        if (isFull()) throw new RuntimeException("Queue is full!");

        if (rear == maxSize-1) { rear = -1; }   // deal with wraparound

        queArray[++rear] = j;                 // increment rear and insert
        nItems++;                            // one more item
    }
    //--------------------------------------------------------------
    public long remove() {             // take item from front of queue
        if (isEmpty()) throw new RuntimeException("Queue is empty.");

        long temp = queArray[front++];        // get value and incr front
        if(front == maxSize) { front = 0; }   // deal with wraparound
        nItems--;                             // one less item
        return temp;
    }
    //--------------------------------------------------------------
    public long peekFront() {          // peek at front of queue
        return queArray[front];
    }
    //--------------------------------------------------------------
    public boolean isEmpty() {         // true if queue is empty
        return (nItems == 0);
    }
    //--------------------------------------------------------------
    public boolean isFull() {         // true if queue is full
        return (nItems == maxSize);
    }
    //--------------------------------------------------------------
    public int size() {             // number of items in queue
        return nItems;
    }

    @Override
    public String toString() {
        String string = "Deque{";              // start string

        int ind = front;                       // start from front index
        int itemsCount = 0;                    // output counter

        while (itemsCount < nItems) {          // true, while all item doesn't print
            string += queArray[ind];           // add item to result string
            if (itemsCount < nItems - 1)
                string += ", ";                // add comma if item doesn't last one
            if (ind == maxSize -1) ind = -1;   // cyclic 'transfer'
            ind++;                             // increment index
            itemsCount++;                      // increment items counter
        }

        return string += "}"; // add last item and return string  --> queArray[rear] +
    }

}
