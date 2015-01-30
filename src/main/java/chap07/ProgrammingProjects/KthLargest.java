package chap07.ProgrammingProjects;

import libs.AppUtils;

import java.util.Random;

/**
 * Created by user on 29.01.2015.
 */
public class KthLargest {
    // define method header
    public static int findKthLargest(int[] nums, int k) {
        //firstly make sure k is valid between (1, nums.length)
        if (k < 1 || k > nums.length) {
            return -1; // let's assume -1 as the default bad signal though in reality we should improve
        }
        // otherwise, we are going to use quick sort to solve this problem
        //though compared to full quick sort we only need to take care of the kth value at position!
        //thus we need two support values to know the region of array we focus on
        return findKthLargest(nums, 0, nums.length -1, k);
    }

    public static int findKthLargest(int[] nums, int start, int end, int k) {
        // this is the key method, the basic idea is to borrow the quick sort algorithm
        // by picking a pivot and put in place and check if it is value we are looking for
        int pivot = start; // assume pivot position is 1st element
        int left = start;
        int right = end;  // we keep start/end untouched and copy values for processing in method

        while (left <= right) {
            // so we scan from left to right until we find a value which is larger than pivot value
            while (left <= right && nums[left] <= nums[pivot]) {
                System.out.print("num[" + left + "]=" + nums[left] + "<= nums[" + pivot + "]=" + nums[pivot]);
                left++; // after this loop, the value at left is larger than pivot position thus we swapping
                System.out.println(" so next left=" + left);
            }

            while (left <= right && nums[right] >= nums[pivot]) {
                System.out.print("num[" + right +"]=" + nums[right] + ">= nums[" + pivot + "]=" + nums[pivot]);
                right--; // similar for right one
                System.out.println(" so next right=" + right);
            }

            // now we swap if valid
            if (left < right) {
                swap(nums, left, right);
                System.out.println(AppUtils.arrToStr("Numbers: ", nums));
            }
        }
        // after the loop, the correct pivot position should rely on right's position
        swap(nums, pivot, right);
        System.out.println(AppUtils.arrToStr("Numbers: ", nums));

        // now different from quick sort, we firstly check if we can return from here
        if (k == right + 1) { // notice k is nth, start from 1 while index starts from 0
            return nums[right]; // we immediately return as right position value is set!
        } else if (k > right + 1) { // that's mean we have divided values to 2 groups, and kth largest can only exist in right half
            System.out.printf("k=%d start=%d end=%d left=%d right=%d (scan right half)\n", k, start, end, left, right);
            return findKthLargest(nums, right + 1, end, k);

        } else { // we only need focus on the left half
            System.out.printf("k=%d start=%d end=%d left=%d right=%d (scan left half)\n",k, start, end, left, right);
            return findKthLargest(nums, start, right - 1, k);
        }
    }

    // define a support method to swap
    public static void swap (int[] nums, int a, int b) {
        System.out.println("Swap nums[" + a +"]=" + nums[a] + " and nums[" + b + "]=" + nums[b]);
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    // let's create a test case
    public static void main (String[] args) {
        int[] nums = new int[10];
        Random random = new Random();

        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100); // each number is random from 0-100
        }

        System.out.println(AppUtils.arrToStr("Numbers :", nums));
        System.out.println("==================================================================");
        int k = 7;

        System.out.println(k + "th largest value is " + findKthLargest(nums, k));
        System.out.println("---------------------------------------------------------------------");
        // notice the whole array is not sorted after we identified kth largest value!
        System.out.println(AppUtils.arrToStr("After   :", nums));
    }
}
