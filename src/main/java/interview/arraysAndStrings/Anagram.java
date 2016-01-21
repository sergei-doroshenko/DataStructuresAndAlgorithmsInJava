package interview.arraysAndStrings;

import java.util.Arrays;

/**
 * Created by sergei on 1/21/16.
 */
public class Anagram {
    public static void main(String[] args) {
        System.out.println(anagram("hello", "loleh"));
        System.out.println(anagram2("hello", "lolen"));
    }

    public static boolean anagram(String s, String t) {
        char[] sChars = s.toCharArray();
        Arrays.sort(sChars);

        char[] tChars = t.toCharArray();
        Arrays.sort(tChars);
        return Arrays.equals(sChars, tChars);
    }

    public static boolean anagram2(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        int[] letters = new int[256];

        int num_unique_chars = 0;
        int num_completed_t = 0;

        char[] s_array = s.toCharArray();

        for (char c : s_array) { // count number of each char in s.
            if (letters[c] == 0) {
                ++num_unique_chars;
            }
            ++letters[c];
        }

        for (int i = 0; i < t.length(); ++i) {
            int c = (int) t.charAt(i);
            if (letters[c] == 0) { // Found more of char c in t than in s.
                return false;
            }

            --letters[c];

            if (letters[c] == 0) {
                ++num_completed_t;
                if (num_completed_t == num_unique_chars) {
                    // it’s a match if t has been processed completely
                    return i == t.length() - 1;
                }
            }
        }

        return false;
    }

}
