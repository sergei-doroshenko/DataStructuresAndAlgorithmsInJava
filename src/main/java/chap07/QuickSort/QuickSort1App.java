package chap07.QuickSort;

import chap03.ObjectSort.ArrayOG;

import java.util.Comparator;

/**
 * Created by Sergei Doroshenko on 28.01.2015.
 */
public class QuickSort1App {
    public static void main (String[] args) {
        int maxSize = 12;
        ArrayOG<Integer> arr = new ArrayOG<>(maxSize);

        /*for (int i = 0; i < maxSize; i++) {
            arr.insert((int) (Math.random() * 199));
            //arr.insert(99);
        }*/

        for (int j = 11; j >= 0; j--) {
            arr.insert(j);
        }

        System.out.println(arr);

        int pivot = 99;

        System.out.println("Size: " + arr.size());

        arr.quickSort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });


        System.out.println(arr);
        System.out.println("Copy: " + arr.getCopyCounter() + ", Compare: " + arr.getCompareCounter());
    }
}
