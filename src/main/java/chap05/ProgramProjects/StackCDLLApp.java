package chap05.ProgramProjects;

import chap05.LinkStack.StackSLL;

/**
 * Created by Sergei Doroshenko on 21.01.2015.
 */
public class StackCDLLApp {
    public static void main(String[] args) {
        StackCDLL<Integer> theStack = new StackCDLL<>(); // make stack

        theStack.push(20);                    // push items
        theStack.push(40);

        theStack.displayStack();              // display stack

        theStack.push(60);                    // push items
        theStack.push(80);

        theStack.displayStack();              // display stack

        while (!theStack.isEmpty()) {
            System.out.println("Pop: " + theStack.pop());
            theStack.displayStack();
        }

    }  // end main()
}
