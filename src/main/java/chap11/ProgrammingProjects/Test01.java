package chap11.ProgrammingProjects;

/**
 * Created by Sergei on 09.02.2015.
 */
public class Test01 {
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
        int size = 10;
        int counter = 0;
        //key /= size;
        //for(;key > size; key /= size, counter++) System.out.println(counter);
        while (key  >= size) {
            key /= size;
            counter++;
        }
        System.out.println("counter = " + counter);
        System.out.println("key = " + key);
        System.out.println("decimal count from function = " + getDecimalCount(123456789));
        System.out.println("Math: " + ((int) Math.log10(123456789) + 1)); // decimal numbers counter
        int k1 = 15;
        int k2 = 12345;
        System.out.println("k1 = " + Integer.toBinaryString(k1));
        System.out.println("k2 = " + Integer.toBinaryString(k2));
        System.out.println("k1 k2 = " + Integer.toBinaryString(k1 | k2));
        System.out.println(Integer.toBinaryString(123456789));
        System.out.println("log2 = " + ( (int)(Math.log(17) / Math.log(2)) + 1));
        System.out.println("-------------------------------------------------");
        int k1Dc = (int) Math.log10(k1) + 1;
        System.out.println("k1 decimal count = " + k1Dc);
        int k2Dc = (int) Math.log10(k2) + 1;
        System.out.println("k1 decimal count = " + k2Dc);
        int groupCount = (int) Math.ceil((double)k2Dc / k1Dc);
        System.out.println(groupCount);
        System.out.println("2 last numbers = " + k2 %  (int)Math.pow(10, k1Dc) );
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

    public static int getDecimalCount(int key) {
        if (key < 0) return 0; // Only for positive numbers

        /************************ 1st variant ******************/
        /*int count = 1;
        while ((key /= 10) > 0 && ++count > 0) {
            System.out.println(key);
        }*/

        /*int count = 0;
        while (key > 0) {
            System.out.println(key);
            key /= 10; // you can comment
            count++; // you can comment
        }*/

        /****************** 2nd variant ************************/
        int count;
        for(count = 1;(key /= 10) > 0; count++) System.out.println(key);
        return count;
    }
}
