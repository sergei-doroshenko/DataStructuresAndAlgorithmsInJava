package chap07.ProgrammingProjects;

import chap03.ObjectSort.ArrayOG;

import java.util.Comparator;

/**
 * Created by user on 29.01.2015.
 */
public class MadianApp {
    public static void main (String[] args) {
        int maxSize = 7;
        ArrayOG<Integer> arr = new ArrayOG<>(maxSize);

        /*for (int i = 0; i < maxSize; i++) {
            arr.insert(i);
        }*/

        arr.insert(2);
        arr.insert(6);
        arr.insert(3);
        arr.insert(1);
        arr.insert(0);
        arr.insert(5);
        arr.insert(4);

        System.out.println(arr);

        int average = 3;

        System.out.println("Size: " + arr.size());

        Integer median = arr.medianPartition(0, arr.size() -1, average, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("Average is " + average + ", Median is at index: " + median);

        /*int partDex = arr.partitionIt2(0, arr.size() -1, average, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println("Pivot is " + average + ", Partition is at index: " + partDex);*/


        System.out.println(arr);
    }
}
