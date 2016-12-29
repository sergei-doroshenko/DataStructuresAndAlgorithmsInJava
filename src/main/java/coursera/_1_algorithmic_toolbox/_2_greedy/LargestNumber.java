package coursera._1_algorithmic_toolbox._2_greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by админ on 29.12.2016.
 */
public class LargestNumber {

    private static boolean isGreaterOrEqual( int a, int b ) {
        String x = a + "" + b;
        String y = b + "" + a;
        return Integer.valueOf(x) >= Integer.valueOf(y);
    }

    public static String largestNumber(List<Integer> data) {
        String result = "";
        int maxDigit = 0;
        int ind = 0;

        while ( data.size() > 0 ) {
            for ( int i = 0; i < data.size(); i++ ) {
                if ( isGreaterOrEqual( data.get( i ), maxDigit ) ) {
                    maxDigit = data.get( i );
                    ind = i;
                }
            }

            result += maxDigit;
            maxDigit = 0;
            data.remove( ind );
        }


        return result;
    }

    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>();
        data.add( 1 );
        data.add( 112 );
        System.out.println( largestNumber( data ));
    }
}
