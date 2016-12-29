package coursera._1_algorithmic_toolbox._1_intro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergei_Doroshenko on 12/29/2016.
 */
public class FibNum {
    /*
    Recursion implementation.
     */
    public static int fibNumRec(int n){
        if (n <= 1) {
            return n;
        }
        return fibNumRec(n - 1) + fibNumRec(n - 2);
    }

    /*
    More efficient array based implementation.
     */
    public static int fibNumArr(int n) {
        List<Integer> numbers = new ArrayList<>( n );
        numbers.add( 0 );
        numbers.add( 1 );

        for (int i = 2; i <= n; i++) {
            numbers.add( numbers.get( i - 1 ) + numbers.get( i - 2 ) );
        }

        return numbers.get( n );
    }

    public static int fibNumVar(int n){
        int tempOne = 0;
        int tempTwo = 1;

        for (int i = 2; i <=n; i++){
            int temp = tempTwo;
            tempTwo += tempOne;
            tempOne = temp;
        }

        return tempTwo;
    }

    public static void main ( String[] args ) {
        int n = 10;
        System.out.println( fibNumRec( n ) );
        System.out.println( fibNumArr( n ) );
        System.out.println( fibNumVar( n ) );
    }
}
