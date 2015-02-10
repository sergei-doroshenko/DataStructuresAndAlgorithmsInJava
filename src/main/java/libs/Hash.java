package libs;

import java.util.Random;

/**
 * Created by Sergei Doroshenko on 10.02.2015.
 */
public class Hash {
    /**
     * Calculate and return how many bits are required for a specific integer
     * e.g. 25 in binary representation is 11001, bits number is 5
     * @param n - decimal integer
     * @return - number of bits
     */
    public static int bitNumber(int n) {
        return (int) Math.floor(Math.log(n)/Math.log(2)) + 1;
    }

    /**
     * Create and return binary mask for getting value represented by
     * specific bits of integer number
     * e.g. binary representation of 25 = 11001, so bit count = 5 and mask = 11111 (31)
     * if we apply this mask to 12307, in binary = 110000 00010011, --> mask & number
     * we get 10011 (last 5 bits of 12307) or 19. So we can get specific bits of integer number.
     * @param bitNumber - number of required bits
     * @return - binary mask as integer
     */
    public static int createBitMask (int bitNumber) {
        int mask = 1;
        for (int i = 1; i < bitNumber; i++) {
            mask = mask << 1 ^ 1;
        }
        return mask;
    }

    /**
     * Calculate hash value to map integer numbers on fixed size array.
     * Uses bitwise mask operations.
     * @param key - number for hashing
     * @param n - size for mapping of numbers
     * @return - hash value (array index) to place key value
     */
    public static int hash(int key, int n) {

        System.out.println("n=" + n + " " + Integer.toBinaryString(n));
        int bitNumber = bitNumber(n);
        System.out.println("bits number=" + bitNumber);
        int mask = createBitMask(bitNumber);
        System.out.println("Mask=" + mask + " " + Integer.toBinaryString(mask));

        System.out.println("key=" + key + " " + Integer.toBinaryString(key));
        int hash = 0;
        while (key > 0) {
            int result = key & mask;
            System.out.println("Result=" + result);
            key >>= bitNumber;
            hash += result;
        }
        hash %= n;
        System.out.println("hash=" + hash);
        return hash;
    }

    public static boolean testBitNumber(int iterations) {

        System.out.println("Test start...");
        Random random = new Random();
        for (int i = 0; i < iterations; i++) {
            int k = random.nextInt(999) + 1;
            int num1 = bitNumber(k);
            int num2 = Integer.toBinaryString(k).length();
            if (num1 != num2) {
                System.out.println("Test failed!");
                System.out.printf("key=%3d, result=%s\n", k, num1 == num2);
                return false;
            }

        }
        System.out.println("Test passed!");
        return true;
    }

    public static boolean testCreateBitMask(int iterations) {

        System.out.println("Test start...");
        Random random = new Random();


        for (int i = 0; i < iterations; i++) {

            int n = random.nextInt(20) + 1;
            //System.out.println("n=" + n + " " + Integer.toBinaryString(n));

            int bitNumber = bitNumber(n);
            //System.out.println("bits number=" + bitNumber);

            int mask = createBitMask(bitNumber);
            //System.out.println("Mask=" + mask + " " + Integer.toBinaryString(mask));

            int result = n & mask;
            if (result != n) {
                System.out.println("Result=" + result);
                System.out.println(Integer.toBinaryString(mask << bitNumber));
                return false;
            }
        }

        System.out.println("Test passed!");
        return true;
    }

    public static boolean testHash(int iterations) {

        System.out.println("Test start...");
        Random random = new Random();
        int n = random.nextInt(20) + 1;
        for (int i = 0; i < iterations; i++) {
            int k = random.nextInt(9999999) + 1;
            int hash = hash(k, n);
            if (hash > n) {
                System.out.println("Test failed!");

                return false;
            }

        }
        System.out.println("Test passed!");
        return true;
    }

    public static void main (String[] args) {
        int n = 25;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(bitNumber(n));
        int k = 12307;
        int mask = createBitMask(bitNumber(n));
        System.out.println(Integer.toBinaryString(mask));
        System.out.println(mask);
        System.out.println(k);
        System.out.println(Integer.toBinaryString(k));
        System.out.println(mask & k);
        System.out.println(Integer.toBinaryString(mask & k));

        /*System.out.println("------------------------------------------------------");
        Hash.testBitNumber(1_000_000);
        System.out.println("------------------------------------------------------");*/

        /*int hash = Hash.hash(1238393, 17);
        System.out.println(hash);*/

        /*System.out.println("------------------------------------------------------");
        Hash.testCreateBitMask(1_000_000);
        System.out.println("------------------------------------------------------");*/

        /*System.out.println("------------------------------------------------------");
        Hash.testHash(1_000_000);
        System.out.println("------------------------------------------------------");*/

    }
}
