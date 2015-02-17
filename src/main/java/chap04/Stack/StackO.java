package chap04.Stack;

import java.util.Iterator;

/**
 * Created by Sergei Doroshenko on 19.01.2015.
 */
public class StackO<T> implements Iterable<T> {
    private int maxSize;        // size of stack array
    private T[] stackArray;
    private int top;            // top of stack
    //--------------------------------------------------------------
    public StackO(int s) {       // constructor
        maxSize = s;             // set array size
        stackArray = (T[]) new Object[maxSize];  // create array
        top = -1;                // no items yet
    }
    //--------------------------------------------------------------
    public void push(T j){    // put item on top of stack
        stackArray[++top] = j;     // increment top, insert item
    }
    //--------------------------------------------------------------
    public T pop() {           // take item from top of stack
        return stackArray[top--];  // access item, decrement top
    }
    //--------------------------------------------------------------
    public T peek() {         // peek at top of stack
        return stackArray[top];    }

    public T peekN(int n) { // return item at index n
        return stackArray[n];
    }

    public int size() {        // return size
        return top + 1;
    }
    //--------------------------------------------------------------
    public boolean isEmpty() {    // true if stack is empty
        return (top == -1);
    }
    //--------------------------------------------------------------
    public boolean isFull() {     // true if stack is full
        return (top == maxSize-1);
    }
    //--------------------------------------------------------------
    public void displayStack(String s) {
        System.out.print(s);
        System.out.print("Stack (bottom-->top): ");
        for (int j = 0; j < size(); j++) {
            System.out.print( peekN(j) );
            System.out.print(' ');
        }
        System.out.println("");
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int ind = top;

            @Override
            public boolean hasNext() {
                return ind != -1;
            }

            @Override
            public T next() {
                return stackArray[ind--];
            }

            @Override
            public void remove() {

            }
        };
    }
}
