package coursera._1_algorithmic_toolbox._1_intro;

/**
 * Created by админ on 29.12.2016.
 */
public class GCD {
    public static long gcdNaive(long a, long b) {
        long currentGCD = 1;
        long min = a < b ? a : b;
        for (int i = 2; i <= min; i++) {
            if ( a % i == 0 && b % i == 0 && i > currentGCD ) {
                currentGCD = i;
            }
        }

        return currentGCD;
    }

    public static long gcdEuclid(long a, long b) {
        if ( b == 0 ) {
            return a;
        }

        return gcdEuclid( b, a % b );
    }

    public static void main(String[] args) {
        System.out.println(gcdNaive( 28851538, 1183019 ));
        System.out.println(gcdEuclid( 28851538, 1183019 ));
    }
}
