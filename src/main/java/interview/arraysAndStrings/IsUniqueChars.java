package interview.arraysAndStrings;

import libs.AppUtils;

/**
 * Created by sergei on 1/21/16.
 */
public class IsUniqueChars {
    public static void main(String[] args) {
        System.out.println(isUniqueChars("abca"));
        System.out.println(isUniqueChars2("abcb"));
    }

    public static boolean isUniqueChars(String str) {

        int checker = 0;

        for (int i = 0; i < str.length(); ++i) {
            System.out.printf(" checker: %s %n", AppUtils.toFormatBinaryStr(checker, 3));
            int val = str.charAt(i) - 'a';


            System.out.printf("1 << val = 1 << %d: %s %n", val, AppUtils.toFormatBinaryStr(1 << val, 3));
            System.out.printf("checker & (1 << val): %s %n", AppUtils.toFormatBinaryStr(checker & (1 << val), 3));
            if ((checker & (1 << val)) > 0) {
                return false;
            }

            checker |= (1 << val);
            System.out.println();
        }

        return true;
    }

    public static boolean isUniqueChars2(String str) {

        boolean[] char_set = new boolean[256];

        for (int i = 0; i < str.length(); i++) {

            int val = str.charAt(i);
            if (char_set[val]) {
                return false;
            }

            char_set[val] = true;
        }

        return true;
    }
}
