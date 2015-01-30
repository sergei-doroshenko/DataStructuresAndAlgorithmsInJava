package chap07.ProgrammingProjects;

import chap05.LinkQueue.QueueSLL;
import libs.AppUtils;

import java.util.Random;

/**
 * Created by Sergei Doroshenko on 29.01.2015.
 * @author Sergie Doroshenko
 *
 */
public class RadixSort {
    // sorting array
    private int[] nums;
    private int digits;
    /*Support array of linked list queues for storing element from nums.
    You need to use the right kind of linked list. To keep the
    sub-sorts stable, you need the data to come out of each list in the same order it went in.*/
    private QueueSLL<Integer>[] rads = new QueueSLL[10];

    public RadixSort(int[] nums, int digits) {        // constructor
        this.nums = nums;
        this.digits = digits;
        for (int i = 0; i < rads.length; i++) {
            rads[i] = new QueueSLL<>();
        }
    }

    public int[] getNums() {
        return nums;
    }

    public void radixSort() {
        String _0s = "";
        for (int j = 1; j <= digits; j++) { // repeat this loop 3 times for sorting three-digit number for every digit
            /* This loop takes the data from the array and puts it on the lists (queues) */
            for (int i = 0; i < nums.length; i++) { // loop for each element
                int num = nums[i];                  // get element from nums
                int n = calcN(num, j); // get digit number of element (e.g. 421 - 1st digit = 1, 2nd = 2, 3rd = 4)
                rads[n].insert(num);  // insert element in appropriate list (queue) according with number of digit
            }

            /* The second loop copies data from the lists (queues) back to the array */
            int k = 0; // starts from 0-index (nums)
            for (int i = 0; i < rads.length; i++) {
                QueueSLL<Integer> q = rads[i]; // get queue in which stored elements with specific digits (e.g. 0)

                while(!q.isEmpty()) {         // loop through the queue,

                    nums[k++] = q.remove();   // remove each element and insert it into array
                }
            }

            /*System.out.println(AppUtils.arrToStr("Sorted on 1" + _0s + "s digit: ", nums));*/

            System.out.print(AppUtils.arrToStr("", nums));
            System.out.println("  Sorted on 1" + _0s + "s digit");
            _0s += "0";
        }

    }

    /**
     * Calculate digit of specific level of the number
     * @param key - number
     * @param radNum - needed digit level
     * @return - digit
     */
    private int calcN(int key, int radNum) {
        int n; // digit
        int z = 0; // number of digits (e.g. 421 = 3, 35 = 2, 7 = 1)
        int num = key; // keep number for further handle

        int invalid = key;

        while(++z > 0 && (num /=10) > 0); // calculate number of digits

        if (radNum == 1) { // calculate digit for 1st level
            if (key < 10) {
                n = key;
            } else {
                n = key % 10;
            }

        } else {  // calculate digit for 2nd and so on


            if (z < radNum) { // If some keys have fewer digits than others,
                n = 0;        // their higher-order digits are considered to be 0
            } else {
                for (int i = 1; i < radNum; i++) {
                    key /= 10;
                }

                if (key > 10) {
                     key %= 10;
                }

                n = key;
            }

        }
        if (n == 10) {
            System.out.println("Invalid key = " + invalid + " digits=" + z);
        }
        return n;
    }

    public static void main (String[] args) {
        int[] nums = new int[16];
        Random random = new Random();

        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(9999); // each number is random from 0-999
        }

        // new int[] {815, 100, 31, 421, 240, 35, 532, 43, 705, 305, 430, 124}

        RadixSort r = new RadixSort(nums, 4);

        System.out.print(AppUtils.arrToStr("", r.getNums()));
        System.out.println("  Unsorted array:");

        r.radixSort();
        System.out.print(AppUtils.arrToStr("", r.getNums()));
        System.out.println("  Sorted array:");

//        System.out.println(421 / 10 /10);

    }

}
