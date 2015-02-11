package chap12.treeHeap;

import libs.AppUtils;
import libs.Hash;

/**
 * Created by Sergei on 11.02.2015.
 */
public class Test01 {
    public static void main (String[] args) {
        int n = 16;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(AppUtils.toBinaryStr(n));
        int[] a = AppUtils.toBinaryArr(n);
        System.out.println(AppUtils.arrToStr(a));
    }


}
