package chap06.StackTriangle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Sergei Doroshenko on 26.01.2015.
 */
public class StackTriangleApp {
    static int theNumber;
    static int theAnswer;
    static StackX theStack;
    static int codePart;
    static Params theseParams;
    //-------------------------------------------------------------
    public static void main(String[] args) throws IOException {
        System.out.print("Enter a number: ");
        theNumber = getInt();
        recTriangle();
        System.out.println("Triangle="+theAnswer);
    }  // end main()
    //-------------------------------------------------------------
    public static void recTriangle() {
        theStack = new StackX(10000);
        codePart = 1;
        while( step() == false);  // call step() until it's true
                                  // null statement
    }
    //-------------------------------------------------------------
    public static boolean step() {
        switch(codePart) {
            case 1:                              // initial call
                System.out.printf("case %d. theAnswer = %3d Stack: %s\n", codePart, theAnswer, theStack);
                theseParams = new Params(theNumber, 6);
                theStack.push(theseParams);
                codePart = 2;

                break;
            case 2:                              // method entry
                System.out.printf("case %d. theAnswer = %3d Stack: %s\n", codePart, theAnswer, theStack);
                theseParams = theStack.peek();
                if(theseParams.n == 1) {         // test
                    theAnswer = 1;
                    codePart = 5;   // exit
                } else {
                    codePart = 3;   // recursive call
                }
                break;
            case 3:                              // method call
                System.out.printf("case %d. theAnswer = %3d Stack: %s\n", codePart, theAnswer, theStack);
                Params newParams = new Params(theseParams.n - 1, 4);
                theStack.push(newParams);
                codePart = 2;  // go enter method
                break;
            case 4:                              // calculation
                System.out.printf("case %d. theAnswer = %3d Stack: %s\n", codePart, theAnswer, theStack);
                theseParams = theStack.peek();
                theAnswer = theAnswer + theseParams.n;
                codePart = 5;
                break;
            case 5:                              // method exit
                System.out.printf("case %d. theAnswer = %3d Stack: %s\n", codePart, theAnswer, theStack);
                theseParams = theStack.peek();
                codePart = theseParams.returnAddress; // (4 or 6)
                theStack.pop();
                break;
            case 6:                              // return point
                System.out.printf("case %d. theAnswer = %3d Stack: %s\n", codePart, theAnswer, theStack);
                return true;
        }  // end switch

        return false;
    }  // end triangle
    //-------------------------------------------------------------
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
    //-------------------------------------------------------------
    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }

}
