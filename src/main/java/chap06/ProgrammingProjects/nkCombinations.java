package chap06.ProgrammingProjects;

/**
 * Created by user on 27.01.2015.
 */
public class nkCombinations {
    // A global variable that counts the number of combinations
    // as they are printed. (Just for debugging.)

    static int numCombinations = 0;

    // A global variable that counts the number of function calls.
    // (Just for debugging.)
    static int counter = 0;
    //
    // Print all substrings that take k of the remaining elements.
    // New stuff is appended to the given previous character(s)
    // for printing. Note that we don't explicitly pass the
    // value of n. It is implied by the length of str.

    static void printCombnk(String str, String previous, int k) {
        ++counter;
    // If we don't have enough to fill it out, just return.
        if (str.length() < k) { return; }
    // If we are down to zero, we have a winner! Print it out
        else if (k == 0) { ++numCombinations; System.out.println(previous); }
    // Otherwise, do the recursions
        else {
    // Pick off the first character of the given str and
    // append it to the previous string that will be printed.
    // Call the function recursively with the position in
    // the string being one to the right of the given string.
    //
    // First call it with k-1
    //
    // Then call it again with k;
    //
    // The effect is to call it with (n-1, k-1) and call
    // it again with (n-1, k).
    //
    //
    // Note that the new print character is appended to
    // the previous ones only when we call it with k-1,
    // since that's the one that will actually print.
    // (I think that's the only really tricky part...)
            printCombnk(str.substring(1), previous + str.charAt(0), k-1);
            printCombnk(str.substring(1), previous, k);
        }
    }
    // You can put your main function here

}
