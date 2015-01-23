package chap05.ProgramProjects;

import java.util.Random;

/**
 * Created by user on 23.01.2015.
 */
public class Josephus {
    private CycleDoublyLinkedList<Integer> list;

    public Josephus() {
        this.list = new CycleDoublyLinkedList<>();
    }

    public String getResult(int n, int m) {
        String result = "";
        populateList(n);
        list.displayForward();
        while(!list.isEmpty()) {
            for (int i = 0; i < m; i++) list.stepForward();
            System.out.println(list.delete());
        }

        return result;
    }

    private void populateList(int n) {
        for (int i = 1; i <= n; i++) {
            //list.insert(new Random().nextInt(70) + 7);
            list.insert(i);
        }
        list.stepForward();
    }

    public static void main (String[] args) {
        Josephus josephus = new Josephus();
        josephus.getResult(7, 3);
    }
}
