package chap06.StackTriangle;

import chap05.LinkStack.StackSLL;
import libs.AppUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Sergei Doroshenko on 26.01.2015.
 */
public class StackTriangle2App {

    static int theNumber;
    static int theAnswer;
    static StackSLL<Integer> theStack;

    public static void main(String[] args) throws IOException {
        System.out.print("Enter a number: ");
        System.out.flush();
        theNumber = AppUtils.getInt();
        stackTriangle();
        System.out.println("Triangle = "+theAnswer);
    }  // end main()
    //-------------------------------------------------------------
    public static void stackTriangle() {
        theStack = new StackSLL<>();          // make a stack

        theAnswer = 0;                   // initialize answer

        while(theNumber > 0) {            // until n is 1,
            theStack.push(theNumber);     // push value
            --theNumber;                  // decrement value
        }

        while( !theStack.isEmpty() ) {     // until stack empty,
            int newN = theStack.pop();    // pop value,
            theAnswer += newN;            // add to answer
        }
    }
}
