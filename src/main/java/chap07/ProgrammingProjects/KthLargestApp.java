package chap07.ProgrammingProjects;

import chap03.ObjectSort.ArrayOG;

import java.util.Comparator;
import java.util.Random;

/**
 * Created by Sergei Doroshenko on 30.01.2015.
 */
public class KthLargestApp {
    public static void main (String[] args) {
        ArrayOG<Integer> arr = new ArrayOG<>(16);

        Random random = new Random();

        for (int i = 0; i < 16; i++) {
            arr.insert(random.nextInt(100)); // each number is random from 0-100
        }

        System.out.println(arr);
        System.out.println(arr.quickSelect(10, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        }));

    }

}
