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

    public static void main (String[] args) {
        System.out.println(mult(8, 8));
    }
}
