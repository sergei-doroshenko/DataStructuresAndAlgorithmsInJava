package chap04.Stack;

import chap04.Deque.Deque;

/**
 * Created by user on 19.01.2015.
 */
public class StackDequeLong {

    private Deque deque;

    //--------------------------------------------------------------
    public StackDequeLong(int s) {       // constructor


        deque = new Deque(s);  // create array

    }
    //--------------------------------------------------------------
    public void push(long j){    // put item on top of stack

        deque.insertLeft(j);     // increment top, insert item
    }
    //--------------------------------------------------------------
    public long pop() {           // take item from top of stack

        return deque.removeLeft();  // access item, decrement top
    }
    //--------------------------------------------------------------
    public long peek() {         // peek at top of stack

        return deque.peekFront();
    }
    //--------------------------------------------------------------
    public boolean isEmpty() {    // true if stack is empty

        return (deque.isEmpty());
    }
    //--------------------------------------------------------------
    public boolean isFull() {     // true if stack is full

        return (deque.isFull());
    }
    //--------------------------------------------------------------
}
