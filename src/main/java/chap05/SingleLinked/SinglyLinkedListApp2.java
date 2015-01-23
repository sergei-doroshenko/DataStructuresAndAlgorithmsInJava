package chap05.SingleLinked;

/**
 * Created by user on 23.01.2015.
 */
public class SinglyLinkedListApp2 {
    public static void main (String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        ins(list, 10);
        ins(list, 20);
        ins(list, 15);
        ins(list, 70);
        ins(list, 22);
        list.displayList();
        System.out.println("Size: " + list.size());

        while (!list.isEmpty()) {
            System.out.println("Removed: " + list.deleteFirst());
            list.displayList();
            System.out.println("Size: " + list.size());
        }

    }

    private static void ins(SinglyLinkedList<Integer> list, int k) {
        if (list.isEmpty()) {
            list.insertFirst(k);
        } else {
            for (SinglyLinkedList.SLLIterator<Integer> iter = list.sllIterator(); iter.hasNext();) {
                Integer i = iter.next();

                if (i >= k) {
                    iter.insertBefore(k);
                    break;
                } else {
                    if (iter.atEnd()) {
                        iter.insertAfter(k);
                    }
                }

            }
        }
    }

}
