package chap12.programmingProjects;

/**
 * Created by Sergei Doroshenko on 11.02.2015.
 */
public class PriorityQBTreeApp {
    public static void main (String[] args) {
        PriorityQBTree<Integer> q = new PriorityQBTree<>();

        q.insert(10);
        q.insert(40);
        q.insert(55);
        q.insert(97);
        q.insert(13);
        q.insert(25);
        q.insert(8);
        q.insert(33);
        q.insert(27);
        q.insert(11);
        q.insert(104);
        q.insert(85);
        q.insert(90);
        q.insert(60);
        q.insert(42);

        int i = q.size();
        for (; i > 0 ; i--) {
            System.out.println(q.remove());
        }

    }
}
