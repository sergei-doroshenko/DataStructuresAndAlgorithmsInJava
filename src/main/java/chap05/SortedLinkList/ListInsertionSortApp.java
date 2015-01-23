package chap05.SortedLinkList;

/**
 * Created by user on 22.01.2015.
 */
public class ListInsertionSortApp {
    public static void main (String[] args) {
        int[] arr1 = new int[] {3, 7, 9, 33, 1, 15, 20, 8, 99, 4, 11};
        System.out.println("Before sorting:");
        displayArray(arr1);

        SortedSinglyLinkedList<Integer> list = new SortedSinglyLinkedList<>();
        for (int i = 0; i < arr1.length; i++) {
            list.insert(arr1[i]);
        }
        list.displayList();

        int j = 0;
        for (Integer i : list) {
            arr1[j++] = list.deleteFirst();
        }
        System.out.println("After sorting:");
        displayArray(arr1);
    }

    private static void displayArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }


}
