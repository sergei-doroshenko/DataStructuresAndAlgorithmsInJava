package chap04.PriorityQ;

import java.util.Comparator;

/**
 * Created by user on 02.02.2015.
 */
public class PriorityQOApp {
    public static void main (String[] args) {
        PriorityQO<Integer> q = new PriorityQO<>(10, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        q.insert(10);
        q.insert(30);
        q.insert(15);
        q.insert(3);
        q.insert(20);
        q.insert(18);
        System.out.println(q);
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
    }
}
