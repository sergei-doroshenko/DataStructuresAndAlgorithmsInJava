package chap06.ProgrammProjects;

/**
 * Created by user on 26.01.2015.
 */
public class Calculator {
    /**
     * Return x * y
     * @param x
     * @param y
     * @return
     */
    public static int mult(int x, int y) {
        if (y == 1) return x;

        return x + mult(x, y - 1);
    }

    public static int power (int x, int y) {
        // base return condition
        if (y == 1) return x;

        // if y is odd
        if (y % 2 == 1) return x * power(x * x, y/2);

        // if y is even
        return power(x * x, y/2);
    }

    // calculate logarithm of integer n to base 2 or the base-2 logarithm of n
    public static int lg (int n) {
        int result = 0;
        while ((n >>= 1) > 0) {
            result++;
        }
        return result;
    }

    public static void main (String[] args) {

        System.out.println(mult(8, 8));
        System.out.println(power(2, 5));
    }
}
