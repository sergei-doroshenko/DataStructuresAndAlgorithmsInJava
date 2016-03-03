package interview.hanoy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Sergei_Admin on 02.03.2016.
 */
public class TowersOfHanoi2 {

    public static Deque<Integer> start = new ArrayDeque<>();
    public static Deque<Integer>  auxiliary = new ArrayDeque<>();
    public static Deque<Integer> end = new ArrayDeque<>();

    public void solve(int n, Deque<Integer> start, Deque<Integer>  auxiliary, Deque<Integer> end) {
        if (n == 1) {
            System.out.println(start.peek() + " -> " + end); // Move the disc from start pole to end pole
            end.push(start.pop());
            System.out.println(start+" "+auxiliary+" "+end);
        } else {
            solve(n - 1, start, end, auxiliary); // Move (n-1) discs from start pole to auxiliary pole

            System.out.println(start.peek() + " -> " + end); // Move the last disc from start pole to end pole
            end.push(start.pop());
            System.out.println(start+" "+auxiliary+" "+end);

            solve(n - 1, auxiliary, start, end); // Move the (n-1) discs from auxiliary pole to end pole
        }
    }

    public static void main(String[] args) {
        TowersOfHanoi2 towersOfHanoi = new TowersOfHanoi2();
        /*System.out.print("Enter number of discs: ");
        Scanner scanner = new Scanner(System.in);
        int discs = scanner.nextInt();*/
        int discs = 3;

        for (int i = discs; i > 0; i--) {
            start.push(i);
        }
//        System.out.println(start);

        towersOfHanoi.solve(discs, start, auxiliary, end);

//        System.out.println(start+" "+auxiliary+" "+end);
    }
}
