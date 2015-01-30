package chap07.QuickSort;

import chap03.ObjectSort.ArrayOG;

import java.util.Comparator;

/**
 * Created by Sergei Doroshenko on 29.01.2015.
 */
public class ShellSortApp {
    public static void main (String[] args) {
        int maxSize = 12;
        ArrayOG<Integer> arr = new ArrayOG<>(maxSize);

        for (int i = 0; i < maxSize; i++) {
            arr.insert((int) (Math.random() * 199));
            //arr.insert(99);
        }

        System.out.println(arr);

        System.out.println("Size: " + arr.size());

        arr.shellSort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println(arr);
    }
}
