package chap03;

import chap03.BubbleSort.ArrayBub;
import chap03.InsertSort.ArrayIns;
import chap03.SelectSort.ArraySel;
import libs.AppUtils;

import java.io.*;

/**
 * Created by Sergei_Doroshenko on 1/12/2015.
 */
public class CompareSortApp1 {
    public static void main(String[] args) {
        int maxSize = 100_000;            // array size
        ArrayBub arr1 = new ArrayBub(maxSize);
        ArrayIns arr2 = new ArrayIns(maxSize);
        ArraySel arr3 = new ArraySel(maxSize);

        /* Create files with test data */
        File outFile = new File("res/longs1M.txt");
        PrintWriter writer = null;
        /*
        try {
            writer = new PrintWriter(outFile);
            for (int i = 0; i < maxSize; i++) {
                long n = (long) (Math.random() * (maxSize - 1));
                writer.write(n + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
        */

        File inFile = AppUtils.getInstance().getFileFromResources("longs100K.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(inFile));
            String line = null;
            /*
            while ((line = reader.readLine()) != null) {
                //process each line in some way
                log(line);
            }
            */

            for (int i = 0; i < maxSize; i++) {
                line = reader.readLine();

                if (line != null) {
                    long n = Long.parseLong(line);
                    arr1.insert(n);
                    arr2.insert(n);
                    arr3.insert(n);
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

        //arr.display();                // display items
        long start = System.currentTimeMillis();
        arr1.bubbleSort();             // bubble sort
        long finish = System.currentTimeMillis();
        System.out.println("Elapsed time: " + ((finish - start)/1000.0));
        //arr.display();                // display them again

        start = System.currentTimeMillis();
        arr2.insertionSort();             // insertion sort
        System.out.println("Elapsed time: " + ((System.currentTimeMillis() - start)/1000.0));

        start = System.currentTimeMillis();
        arr3.selectionSort();             // selection sort
        System.out.println("Elapsed time: " + ((System.currentTimeMillis() - start)/1000.0));

    }  // end main()

    public static void writeResultsToFile(String fileName) {
        File outFile = new File(fileName);
        PrintWriter writer = null;
    }
}
