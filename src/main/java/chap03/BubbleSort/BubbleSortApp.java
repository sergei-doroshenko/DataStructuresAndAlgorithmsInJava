package chap03.BubbleSort;

import libs.AppUtils;

import java.io.*;

/**
 * Created by Sergei_Doroshenko on 1/12/2015.
 */
public class BubbleSortApp {
    public static int MAX_SIZE = 1_000;
    public static void main(String[] args) {
        /*
        ArrayBub arr = new ArrayBub(MAX_SIZE);  // create the array
        arr.insert(77);               // insert 10 items
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);
        */

        ArrayBub arr1 = createArrayBubFromFile("longs1K.txt");
        ArrayBub arr2 = createArrayBubFromFile("longs1K.txt");
        ArrayBub arr3 = createArrayBubFromFile("longs1K.txt");
        arr1.display();                // display items
        System.out.println("--------------------------------------------------");
        System.out.println("Shaker sort  - " + arr2.shakerSort());
        System.out.println("OddEven sort - " + arr3.oddEvenSort());
        System.out.println("Bubble sort  - " + arr1.bubbleSort());             // bubble sort them


        System.out.println("--------------------------------------------------");

        arr1.display();                // display them again
    }  // end main()

    public static ArrayBub createArrayBubFromFile(String fileName) {
        File inFile = AppUtils.getInstance().getFileFromResources(fileName);

        ArrayBub arr = new ArrayBub(MAX_SIZE);

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(inFile));
            String line = null;

            for (int i = 0; i < MAX_SIZE; i++) {
                line = reader.readLine();

                if (line != null) {
                    long n = Long.parseLong(line);
                    arr.insert(n);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return arr;
    }
}
