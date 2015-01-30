package chap05.ProgrammingProjects;

import java.util.Random;

/**
 * Created by user on 23.01.2015.
 */
public class CycleDoublyLinkedListApp {
    public static void main (String[] args) {
        CycleDoublyLinkedList<Integer> list = new CycleDoublyLinkedList<>();

        System.out.println("List is empty: " + list.isEmpty());
        list.insert(15);
        list.insert(10);
        list.insert(5);
        list.insert(30);
        System.out.print("Current: " + list.getCurrent() + " ");
        list.displayForward();

        for (int i = 0; i < 10; i++) {
            list.stepForward();
            System.out.print("Current: " + list.getCurrent() + " ");
            list.displayForward();
        }
        System.out.println("------------ Insert -------------------");
        System.out.println("Step forward. Current: " + list.stepForward());
        System.out.print("Current: " + list.getCurrent() + " ");
        list.displayForward();

        System.out.println("Step backward. Current: " + list.stepBackward());
        System.out.println("Step backward. Current: " + list.stepBackward());

        System.out.print("Current: " + list.getCurrent() + " ");
        list.displayForward();
        list.insert(100);
        System.out.print("Current: " + list.getCurrent() + " ");
        list.displayForward();

        System.out.println("-------------- Delete One --------------------");
        System.out.println("Step forward. Current: " + list.stepForward());
        System.out.println("Step forward. Current: " + list.stepForward());
        System.out.println("Delete: " + list.delete());
        System.out.print("Current: " + list.getCurrent() + " ");
        list.displayForward();

        System.out.println("--------------- Delete All --------------------");

        while (!list.isEmpty()) {
            System.out.println("Delete: " + list.delete());
        }
        System.out.print("Current: " + list.getCurrent() + " ");
        list.displayForward();

        System.out.println("--------- Insert in loop -----------------");

        for (int i = 0; i < 10; i++) {
            int value = new Random().nextInt(30) + 7;
            if (i == 5) list.insert(17);
            if (i > 0 && i % 2 == 0) {
                System.out.println("Step forward. Current: " + list.stepForward());
            } else if (i > 0 && i % 2 == 1) {
                System.out.println("Step backward. Current: " + list.stepBackward());
            }

            list.insert(value);
        }
        System.out.print("Current: " + list.getCurrent() + " ");
        list.displayForward();

        System.out.println("--------------- Delete key --------------------");

        System.out.println("Delete key: " + list.deleteKey(list.getCurrent()));
        System.out.print("Current: " + list.getCurrent() + " ");
        list.displayForward();

        System.out.println("Delete key: " + list.deleteKey(17));
        System.out.print("Current: " + list.getCurrent() + " ");
        list.displayForward();
    }
}
