package interview.arraysAndStrings;

/**
 * Created by sergei on 1/21/16.
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        char[] arr = "hello".toCharArray();
        removeDuplicates(arr);
        System.out.println(new String(arr));
    }

    public static void removeDuplicates(char[] str) {

        if (str == null) return;

        int len = str.length;

        if (len < 2) return;

        int tail = 1;

        for (int i = 1; i < len; ++i) {
            int j;

            for (j = 0; j < tail; ++j) {
                if (str[i] == str[j]) break;
            }

            if (j == tail) {
                str[tail] = str[i];
                ++tail;
            }
        }

        str[tail] = 0;
    }

}
