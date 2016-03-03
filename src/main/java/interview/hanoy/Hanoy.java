package interview.hanoy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Sergei_Admin on 02.03.2016.
 */
public class Hanoy {
    Deque<Integer> left = new ArrayDeque<>();
    Deque<Integer> middle = new ArrayDeque<>();
    Deque<Integer> right = new ArrayDeque<>();

    public Hanoy(int size) {
        for (int i = size; i >= 0; i--) {
            left.push(i);
        }
        System.out.println("Source: " + left);
    }

    public void doTowers() {
        move(left, middle, right);
    }

    private void move (Deque<Integer> source, Deque<Integer> target, Deque<Integer> support) {
        System.out.println("Entered: " + source + ", " + target + ", " + support);
        if (source.size() == 1) {
            step(source, target);
        } else {
            step(source, target);
            move(source, support, target);
            step(source, support);
            move(target, support, source);
        }
    }

    private void step (Deque<Integer> src, Deque<Integer> dest) {
        System.out.println("Move: " + src + " -> " + dest);
        dest.push(src.pop());
    }

    public static void main(String[] args) {
        Hanoy hanoy = new Hanoy(2);
        hanoy.doTowers();
        System.out.println(hanoy.left + " " + hanoy.middle + hanoy.right);
    }
}
