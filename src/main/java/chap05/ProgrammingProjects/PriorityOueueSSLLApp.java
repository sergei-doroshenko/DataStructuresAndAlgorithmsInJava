package chap05.ProgrammingProjects;

import java.util.Random;

/**
 * Created by user on 23.01.2015.
 */
public class PriorityOueueSSLLApp {
    public static void main (String[] args) {
        PriorityQueueSSLL<Integer> queue = new PriorityQueueSSLL<>();

        for (int i = 0; i < 10; i++) {
            int j = new Random().nextInt(20) + 4;
            queue.insert(j);
            System.out.println("Insert: " + j);
        }
        System.out.println(queue);

        while (!queue.isEmpty()) {
            queue.remove();
            System.out.println(queue);
        }
    }
}
