package chap05.linkList;

/**
 * Created by user on 21.01.2015.
 */
public class SinglyLinkedListApp {
    private static SinglyLlinkedList<Integer> LIST = new SinglyLlinkedList();  // make new list

    public static void main(String[] args) {


        LIST.insertFirst(22);      // insert four items
        LIST.insertFirst(44);
        LIST.insertFirst(66);
        LIST.insertFirst(88);
        check();

        while( !LIST.isEmpty() ) {         // until it's empty,
            System.out.println("Delete first: " + LIST.deleteFirst());         // display it
            check();
        }

        LIST.insertFirst(15);      // insert four items
        LIST.insertFirst(37);
        LIST.insertFirst(74);
        LIST.insertFirst(55);
        check();


        int elem1 = 15;
        System.out.println("Index of " + elem1 + ": " + LIST.find(elem1));
        int elem2 = 55;
        System.out.println("Delete " + elem2 + ": " + LIST.delete(elem2));
        check();

        int elem3 = 37;
        System.out.println("Delete " + elem3 + ": " + LIST.delete(elem3));
        check();

        LIST.insertAfter(74, 129);
        LIST.displayList();

        LIST.insertAfter(129, 3);
        LIST.displayList();

        LIST.insertAfter(15, 37);
        check();

        LIST.insertFirst(100);
        LIST.displayList();
        System.out.println("Delete last: " + LIST.deleteLast());
        check();

    }  // end main()

    private static void check() {
        LIST.displayList();
        System.out.printf("First: %3d, Last: %3d\n", LIST.getFirst(), LIST.getLast());
    }
}
