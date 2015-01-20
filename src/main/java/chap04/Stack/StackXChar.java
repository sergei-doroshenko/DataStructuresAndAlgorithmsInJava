package chap04.Stack;

/**
 * Created by user on 19.01.2015.
 */
public class StackXChar {
    private int maxSize;        // size of stack array
    private char[] stackArray;
    private int top;            // top of stack
    //--------------------------------------------------------------
    public StackXChar(int s) {       // constructor
        maxSize = s;             // set array size
        stackArray = new char[maxSize];  // create array
        top = -1;                // no items yet
    }
    //--------------------------------------------------------------
    public void push(char j){    // put item on top of stack
        stackArray[++top] = j;     // increment top, insert item
    }
    //--------------------------------------------------------------
    public char pop() {           // take item from top of stack
        return stackArray[top--];  // access item, decrement top
    }
    //--------------------------------------------------------------
    public char peek() {         // peek at top of stack
        return stackArray[top];
    }

    public char peekN(int n) { // return item at index n
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
        for(int j=0; j<size(); j++) {
            System.out.print( peekN(j) );
            System.out.print(' ');
        }
        System.out.println("");
    }
}
