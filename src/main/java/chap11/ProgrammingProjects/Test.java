package chap11.ProgrammingProjects;

/**
 * Created by Sergei on 09.02.2015.
 */
public class Test {
    public static void main (String... args) {

        for (int i = 1; i < 10;) {
            System.out.print((i * i++) + " ");
        }
        System.out.println();
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' '};
        for (char ch : alphabet) System.out.print(ch + " ");
        System.out.println();
        for (char i = 'a'; i <= 'z'; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(' ' - 96);
        // System.out.println(hashFunc3("camasutra", 10));

        int key = 123456789;
        int size = 100;
        int counter = 0;
        //key /= size;
        //for(;key > size; key /= size, counter++) System.out.println(counter);
        while (key  > size) {
            key /= size;
            counter++;
        }
        System.out.println(counter);
        System.out.println(key);
    }

    public static int hashFunc3(String key, int arraySize) {
        int hashVal = 0;

        for(int j = 0; j < key.length(); j++) {// left to right
            char ch = key.charAt(j);
            System.out.print(ch + " ");
            int letter;
            if (ch == ' ') letter = 0; // space char code
            else letter = ch - 96; // get char code
            System.out.print(letter + " ");

            hashVal = (hashVal * 27 + letter) % arraySize; // mod
            System.out.println(hashVal);
        }

        return hashVal; // no mod
    } // end hashFunc3()
}
