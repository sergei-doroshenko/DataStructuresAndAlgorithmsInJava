package chap05.SortedLinkList;

/**
 * Created by user on 21.01.2015.
 */
public class SortedListApp {
    public static void main(String[] args)
    {                            // create new list
        SortedSinglyLinkedList<Integer> theSortedList = new SortedSinglyLinkedList<>();
        theSortedList.insert(20);    // insert 2 items
        theSortedList.insert(40);
        theSortedList.insert(10);    // insert 3 more items
        theSortedList.insert(50);
        theSortedList.insert(30);

        theSortedList.displayList(); // display list

        theSortedList.deleteFirst();      // remove an item

        theSortedList.displayList(); // display list

        theSortedList.insert(3);
        theSortedList.insert(150);
        theSortedList.displayList();

        /* Delete all items start --> end */
        while (!theSortedList.isEmpty()) {
            theSortedList.deleteLast();
            theSortedList.displayList();
        }

        theSortedList.insert(50);
        theSortedList.insert(33);
        theSortedList.insert(130);
        theSortedList.insert(77);
        theSortedList.insert(15);
        theSortedList.insert(23);
        theSortedList.insert(90);
        theSortedList.displayList();

        /* Delete all items start <-- end */
        while (!theSortedList.isEmpty()) {
            theSortedList.deleteFirst();
            theSortedList.displayList();
        }
    }  // end main()
}
