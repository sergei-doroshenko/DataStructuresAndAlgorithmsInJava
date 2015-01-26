package chap03;

import chap03.ObjectSort.ArrayOG;
import libs.AppUtils;

import java.io.*;
import java.util.Comparator;

/**
 * Created by Sergei_Doroshenko on 1/12/2015.
 */
public class CompareSortApp2 {
    public static String ABSOLUTE_PATH = new File(".").getAbsolutePath();
    public static String RESOURCES_DIR = "/src/main/resources/";
    public static int MAX_SIZE = 100_000; // array size

    public static void main(String[] args) {
        //generateTestDataReverse("longs10Reverse.txt", 10);
        //generateTestDataSorted("longs100KSorted.txt", 99_999);
        //generateTestData("longs100-4.txt", 100);
        runTests("longs100K.txt", MAX_SIZE);
    }  // end main()

    public static <T> void writeResultsToFile(ArrayOG<T> arr, String fileName) {
        File outFile = new File(fileName);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(outFile);
            for (T item : arr) {
                writer.write(item + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    public static void generateTestData(String fileName, int quantity) { // "res/longs1M.txt"
        /* Create files with test data */
        File outFile = new File(ABSOLUTE_PATH + RESOURCES_DIR + fileName);
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(outFile);
            for (int i = 0; i < quantity; i++) {
                long n = (long) (Math.random() * (quantity - 1));
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

    }

    public static void generateTestDataReverse(String fileName, int start) { // Generates test data from 99999..0
        File outFile = new File(ABSOLUTE_PATH + RESOURCES_DIR + fileName); //
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(outFile);
            for (int i = start - 1; i >= 0; i--) {
                writer.write(i + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    public static void generateTestDataSorted(String fileName, int quantity) { // Generates test data from 99999..0
        File outFile = new File(ABSOLUTE_PATH + RESOURCES_DIR + fileName); //
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(outFile);
            for (int i = 0; i <= quantity; i++) {
                writer.write(i + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    public static void runTests(String dataFileName, int size) {
        System.out.println("Application started....");
        ArrayOG<Integer> arr1 = new ArrayOG<>(size);
        ArrayOG<Integer> arr2 = new ArrayOG<>(size);
        ArrayOG<Integer> arr3 = new ArrayOG<>(size);
        ArrayOG<Integer> arr4 = new ArrayOG<>(size);

        System.out.println("Create arrays...");
        File inFile = AppUtils.getInstance().getFileFromResources(dataFileName); // "res/longs100K.txt"
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

            for (int i = 0; i < size; i++) {
                line = reader.readLine();
                if (line != null) {
                    arr1.insert(Integer.valueOf(line));
                    arr2.insert(Integer.valueOf(line));
                    arr3.insert(Integer.valueOf(line));
                    arr4.insert(Integer.valueOf(line));
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
        Comparator<Integer> integerComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        System.out.println("Run tests..");

        long start;

        start = System.currentTimeMillis();
        arr2.insertionSort(integerComparator);             // insertion sort
        System.out.println("Elapsed time (insertion sort): " + ((System.currentTimeMillis() - start)/1000.0));
        writeResultsToFile(arr2, "res/resultsInsertion.txt");


        start = System.currentTimeMillis();
        //arr1.bubbleSort(integerComparator);             // bubble sort
        arr1.bubbleSort2(integerComparator);             // bubble sort with two ends moving
        long finish = System.currentTimeMillis();
        System.out.println("Elapsed time (bubble sort): " + ((finish - start)/1000.0));
        writeResultsToFile(arr1, "res/resultsBubble.txt");

        start = System.currentTimeMillis();
        arr3.mergeSort(integerComparator);             // selection sort
        System.out.println("Elapsed time (merge sort): " + ((System.currentTimeMillis() - start)/1000.0));
        writeResultsToFile(arr3, "res/resultsMerge.txt");

        start = System.currentTimeMillis();
        arr3.selectionSort(integerComparator);             // selection sort
        System.out.println("Elapsed time (selection sort): " + ((System.currentTimeMillis() - start)/1000.0));
        writeResultsToFile(arr3, "res/resultsSelection.txt");


    }
}
