package libs;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by user on 20.01.2015.
 */
public final class AppUtils {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private static AppUtils instance;

    private AppUtils() {}

    public static AppUtils getInstance() {
        if (instance == null) {
            instance = new AppUtils();
        }
        return instance;
    }

    //-------------------------------------------------------------
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        /*br.close();
        isr.close();*/
        return s;
    }
    //-------------------------------------------------------------
    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
    //-------------------------------------------------------------

    public static char getChar() throws IOException {
        String s = getString();
        if (s.isEmpty()) return '0';
        return s.charAt(0);
    }
    //-------------------------------------------------------------
    /**
     * //Get file from resources folder
     * @param fileName
     * @return
     */
    public File getFileFromResources (String fileName) {
        //ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

    public static String arrToStr(String head, int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append(head);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                sb.append(arr[i]);
                if (i < arr.length - 1) {
                    sb.append(", ");
                }
            }
        }
        return sb.toString();
    }

    public static String arrToStr(int[] arr) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {

                sb.append(arr[i]);


        }
        return sb.toString();
    }

    public static String arrToStr(String head, long[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append(head);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                sb.append(arr[i]);
                if (i < arr.length - 1) {
                    sb.append(", ");
                }
            }
        }
        return sb.toString();
    }



    public static String arrToStr(String head, Object[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append(head);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                sb.append(arr[i]);
                if (i < arr.length - 1) {
                    sb.append(", ");
                }
            }
        }
        return sb.toString();
    }

    public static int getPrime(int min) {     // returns 1st prime > min
        for(int j = min+1; true; j++) { // for all j > min
            if (isPrime(j)) {           // is j prime?
                return j;               // yes, return it
            }
        }
    }
    // -------------------------------------------------------------
    static public boolean isPrime(int n) {      // is n prime?
        for(int j = 2; (j*j <= n); j++) { // for all j
            if (n % j == 0) {             // divides evenly by j?
                return false;             // yes, so not prime
            }
        }
        return true; // no, so prime
    }

    public static String toBinaryStr (int n) {
        StringBuilder path = new StringBuilder();
        while(n >= 1) {
            path.append(n % 2);
            n >>= 1;
        }
        return path.reverse().toString();
    }

    public static String toFormatBinaryStr(int n, int length) {
        String result = Integer.toBinaryString(n);
        for (int i = length; i > result.length(); ) {
            result = "0" + result;
        }

        return result;
    }

    public static int[] toBinaryArr (int n) {
        int pos = Hash.bitCount(n);
        int[] arr = new int[pos];
        while(n >= 1) {
            arr[--pos] = n % 2;
            n >>= 1;
        }

        return arr;
    }
}
