package chap07.Partition;

import chap03.ObjectSort.ArrayOG;
import libs.AppUtils;

import java.util.Comparator;

/**
 * Created by Sergei Doroshenko on 28.01.2015.
 */
public class PartitionApp {
    public static void main (String[] args) {
        int maxSize = 16;
        ArrayOG<Integer> arr = new ArrayOG<>(maxSize);

        for (int i = 0; i < maxSize; i++) {
            arr.insert((int) (Math.random() * 199));
            //arr.insert(99);
        }

        System.out.println(arr);

        int pivot = 99;

        System.out.println("Size: " + arr.size());

        int partDex = arr.partitionIt(0, arr.size() -1, pivot, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println("Pivot is " + pivot + ", Partition is at index: " + partDex);
        System.out.println(arr);
    }
}
