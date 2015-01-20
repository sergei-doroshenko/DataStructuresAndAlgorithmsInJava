package chap04.Stack;

/**
 * Created by user on 19.01.2015.
 */
public class StackXInt {
    private int maxSize;        // size of stack array
    private int[] stackArray;
    private int top;            // top of stack
    //--------------------------------------------------------------
    public StackXInt(int s) {       // constructor
        maxSize = s;             // set array size
        stackArray = new int[maxSize];  // create array
        top = -1;                // no items yet
    }
    //--------------------------------------------------------------
    public void push(int j){    // put item on top of stack
        stackArray[++top] = j;     // increment top, insert item
    }
    //--------------------------------------------------------------
    public int pop() {           // take item from top of stack
        return stackArray[top--];  // access item, decrement top
    }
    //--------------------------------------------------------------
    public int peek() {         // peek at top of stack
        return stackArray[top];    }

    public int peekN(int n) { // return item at index n
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
