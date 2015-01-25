package chap02.OrderedArray;

import java.util.Arrays;

/**
 * Created by Sergei on 09.01.2015.
 */
public class OrderedApp {

    public static long[] merge (long[] a, long[] b) {
        long[] res = new long[a.length + b.length]; // new resulting array include both a - and b - arrays
        int aIn = 0; // index for a - array
        int bIn = 0; // index for b - array

        for (int i = 0; i < res.length; i++) {
            if (aIn == a.length) { // have got an end of a - array
                res[i] = b[bIn++];
            } else if (bIn == b.length) { // have got an end of a - array
                res[i] = a[aIn++];
            } else {

                if (a[aIn] < b[bIn]) { // compare first elements of a- and b- arrays
                    res[i] = a[aIn++];
                } else {
                    res[i] = b[bIn++];
                }
            }
        }

        return res; // return the results
    }

    public static void main(String[] args) {
        int maxSize = 100;             // array size
        OrderedArray arr;                  // reference to array
        arr = new OrderedArray(maxSize);   // create the array

        arr.insert(77);                // insert 10 items
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);
        arr.display();                 // display items

        int searchKey = 22;            // search for item
        if( arr.findL(searchKey) != arr.size() )
            System.out.println("findL() " + searchKey + " at position: " + arr.findL(searchKey));
        else
            System.out.println("Can't findL " + searchKey);

        OrderedArray searched = new OrderedArray(arr.size());
        for (long i = 0; i < 150; i++) {
            int keyIndex = arr.findR(i);
            if (keyIndex != -1){
                System.out.println("findR() index of " + i + " = " + keyIndex);
                searched.insert(arr.get(keyIndex));
            }
        }
        System.out.print("Recursive search results: ");
        searched.display();

        arr.display();                 // display items

        arr.delete(00);                // delete 3 items
        arr.delete(55);
        arr.delete(99);

        arr.display();                 // display items again
        System.out.println(arr.insertB(15));
        arr.display();
        System.out.println("Arrays.binarySearch: " + Arrays.binarySearch(new int[]{0, 1, 2, 3}, 1));

        System.out.println("---------------------------------------------------------------------");
        long[]x = merge(new long[]{1, 5, 7, 20}, new long[]{2, 3, 4, 15, 22});
        System.out.println(x);

        System.out.println("---------------------------------------------------------------------");
        OrderedArray a = new OrderedArray(5);
        a.insert(1);
        a.insert(5);
        a.insert(7);
        a.insert(20);
        a.insert(31);
        a.display();

        OrderedArray b = new OrderedArray(3);
        b.insert(2);
        b.insert(3);
        b.insert(15);
        b.display();

        OrderedArray r = OrderedArray.merge(a, b);
        r.display();
    }  // end main()
}
