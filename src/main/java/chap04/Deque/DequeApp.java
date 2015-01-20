package chap04.Deque;

/**
 * Created by user on 19.01.2015.
 */
public class DequeApp {
    public static void main (String[] args) {
        Deque deque = new Deque(5);
        System.out.println(deque);

        deque.insertRight(10);
        deque.insertRight(20);
        deque.insertRight(30);
        deque.insertRight(40);
        System.out.println(deque);

        deque.remove();
        deque.remove();
        deque.remove();
        System.out.println(deque);

        deque.insertLeft(11);
        deque.insertLeft(12);
        deque.insertLeft(13);
        System.out.println(deque);

    }
}
