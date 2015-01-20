package chap04.Queue;

/**
 * Created by Sergei Doroshenko on 19.01.2015.
 * Class implements queue data structure
 * without instance variable holds element's quantity
 * e.g. nItems in Queue.class
 */
public class QueueX {
    private int maxSize;
    private long[] queArray;
    private int front;
    private int rear;

    //--------------------------------------------------------------
    public QueueX(int s) {         // constructor
        /*
        Because of this additional element we can't insert more 5 items.
        method isFull() check that there is one empty slot between front and rear.
        Here logic from method: rear + 2 == front || (front + maxSize - 2 == rear).
        So:  rear + 2 == front   ---->  incoherence sequence
        e.g. rear =1 front =3 -> can't insert -> rear = 1 + 2 = 3 == 3 (front)
        Or: front + maxSize - 2 == rear  ----> continuous sequence
        e.g. maxSize = 6 front = 0 + 4 = 4 == 4 (rear)
         */
        maxSize = s + 1;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;

    }
    //--------------------------------------------------------------
    public void insert(long j) {      // put item at rear of queue
        if (isFull()) throw new RuntimeException("Queue is full!");

        if(rear == maxSize-1) { rear = -1; }   // deal with wraparound

        queArray[++rear] = j;                 // increment rear and insert

    }
    //--------------------------------------------------------------
    public long remove() {             // take item from front of queue
        if (isEmpty()) throw new RuntimeException("Queue is empty.");

        long temp = queArray[front++];        // get value and incr front
        if(front == maxSize) { front = 0; }   // deal with wraparound

        return temp;
    }
    //--------------------------------------------------------------
    public long peekFront() {          // peek at front of queue
        return queArray[front];
    }
    //--------------------------------------------------------------
    public boolean isEmpty() {         // true if queue is empty

        return ( rear + 1 == front || (front + maxSize - 1 == rear) );
    }
    //--------------------------------------------------------------
    public boolean isFull() {         // true if queue is full

        return ( rear + 2 == front || (front + maxSize - 2 == rear) );
    }
    //--------------------------------------------------------------
    public int size() {             // number of items in queue
        //if (isEmpty()) return 0;
        //if (isFull()) return 5;
        if(rear >= front) {                   // continuous sequence
            return rear - front + 1;
        } else {                              // incoherence sequence
            return (maxSize - front) + (rear + 1);
        }
    }

}
