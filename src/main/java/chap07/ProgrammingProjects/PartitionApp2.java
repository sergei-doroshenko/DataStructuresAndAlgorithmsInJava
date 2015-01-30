package chap07.ProgrammingProjects;

import java.util.Comparator;

/**
 * Created by Sergei Doroshenko on 29.01.2015.
 */
public class PartitionApp2 {
    public static void main (String[] args) {
        int maxSize = 3;
        ArrayPart<Integer> arr = new ArrayPart<>(maxSize);

        for (int i = 0; i < maxSize; i++) {
            arr.insert((int) (Math.random() * 199));
            //arr.insert(99);
        }

        System.out.println(arr);

        System.out.println("Size: " + arr.size());

        int partDex = arr.partitionIt(0, arr.size() -1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println("Partition is at index: " + partDex);
        System.out.println(arr);
    }
}
