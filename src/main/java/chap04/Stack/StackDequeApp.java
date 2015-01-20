package chap04.Stack;

/**
 * Created by user on 20.01.2015.
 */
public class StackDequeApp {
    public static void main (String[] args) {
        StackDequeLong stack = new StackDequeLong(10);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);

        while( !stack.isEmpty() ) {     // until it's empty,
            // delete item from stack
            long value = stack.pop();
            System.out.print(value);      // display it
            System.out.print(" ");
        }  // end while
        System.out.println("");

    }

}
