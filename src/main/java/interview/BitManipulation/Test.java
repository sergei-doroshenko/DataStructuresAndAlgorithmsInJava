package interview.BitManipulation;

import libs.AppUtils;

/**
 * Created by Sergei on 21.01.2016.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(AppUtils.toFormatBinaryStr(0b1010 << 1, 4));
        System.out.println(AppUtils.toFormatBinaryStr(0b1010 >> 1, 4));
        System.out.println(AppUtils.toFormatBinaryStr(0b1001 ^ 0b1001, 4));
        System.out.println(AppUtils.toFormatBinaryStr(0b1100 ^ 0b1010, 4));
        System.out.println(AppUtils.toFormatBinaryStr(0b1001 & 0b1100, 4));
        System.out.println("0x" + Integer.toHexString(0xFF - 1).toUpperCase());
        System.out.println("0x" + Integer.toHexString(0xAB + 0x11).toUpperCase());

    }
}
