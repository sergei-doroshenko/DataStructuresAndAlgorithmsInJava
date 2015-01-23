package chap04.Deque;

/**
 * Created by Sergei Doroshenko on 19.01.2015.
 */
public class DequeArr {
    private int maxSize;
    private long[] queArray;
    private int front;
    private int rear;
    private int nItems;
    //--------------------------------------------------------------
    public DequeArr(int s) {               // constructor
        maxSize = s;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }
    //--------------------------------------------------------------

    /**
     * Insert item at the top end
     * @param j long element that we insert into the deque
     */
    public void insertLeft(long j) {              // put item at rear of queue
        if (isFull()) throw new RuntimeException("Queue is full!");

        if (front == 0) {             // when insert item push front pointer
            front = maxSize - 1;      // from the right (maxSize - 1) to the left (0)
        }

        queArray[--front] = j;                 // decrement front and insert
        nItems++;                            // one more item
    }

    public void insertRight(long j) {              // put item at rear of queue
        if (isFull()) throw new RuntimeException("Deque is full!");

        if (rear == maxSize-1) { rear = -1; }   // deal with wraparound

        queArray[++rear] = j;                 // increment rear and insert
        nItems++;                            // one more item
    }
    //--------------------------------------------------------------
    public long removeLeft() {             // take item from front of queue
        if (isEmpty()) throw new RuntimeException("Deque is empty.");

        long temp = queArray[front++];        // get value and incr front
        if(front == maxSize) { front = 0; }   // deal with wraparound
        nItems--;                             // one less item
        return temp;
    }

    public long removeRight() {             // take item from front of queue
        if (isEmpty()) throw new RuntimeException("Queue is empty.");

        long temp = queArray[rear--];          // get value and incr front
        if(rear == -1) { rear = maxSize -1; }   // deal with wraparound
        nItems--;                              // one less item
        return temp;
    }
    //--------------------------------------------------------------
    public long peekFront() {          // peek at front of queue
        return queArray[front];
    }

    public long peekRear() {           // peek at rear of queue
        return queArray[rear];
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
