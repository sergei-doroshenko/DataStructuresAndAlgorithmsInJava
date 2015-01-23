package chap04.Deque;

/**
 * Created by user on 19.01.2015.
 */
public class DequeApp {
    public static void main (String[] args) {
        DequeArr deque = new DequeArr(5);
        System.out.println(deque);

        deque.insertRight(10);
        deque.insertRight(20);
        deque.insertRight(30);
        deque.insertRight(40);
        System.out.println(deque);

        System.out.println("Removed: " + deque.removeLeft());
        System.out.println("Removed: " + deque.removeLeft());
        System.out.println("Removed: " + deque.removeLeft());
        System.out.println("Removed: " + deque.removeLeft());

        System.out.println(deque);

        deque.insertLeft(11);
        deque.insertLeft(12);
        deque.insertLeft(13);
        System.out.println(deque);

        System.out.println("Removed: " + deque.removeRight());
        //System.out.println("Removed: " + deque.removeRight());
        System.out.println("Removed: " + deque.removeLeft());
        System.out.println("Removed: " + deque.removeRight());
        //System.out.println("Removed: " + deque.removeRight());
        System.out.println(deque);
    }
}
