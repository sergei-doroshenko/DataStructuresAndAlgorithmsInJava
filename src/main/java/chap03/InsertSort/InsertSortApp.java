package chap03.InsertSort;

import libs.AppUtils;

import java.io.*;

/**
 * Created by Sergei_Doroshenko on 1/12/2015.
 */
public class InsertSortApp {
    public static void main(String[] args) {
        int maxSize = 100;            // array size

        ArrayIns arr = createArrayInsFromFile("longs100KReverse.txt", 100);
        /*
        arr = new ArrayIns(maxSize);  // create the array

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
        arr.insert(4);
        arr.insert(4);
        */
        arr.display();                // display items

        System.out.println(arr.insertionSort());          // insertion-sort them

        arr.display();                // display them again

        System.out.println("Median: " + arr.median());

        arr.noDups();

        arr.display();
    }  // end main()

    public static ArrayIns createArrayInsFromFile (String fileName, int size) {
        ArrayIns arr = arr = new ArrayIns(size);;
        File inFile = AppUtils.getInstance().getFileFromResources(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(inFile));
            String line = null;
            /*
            while ((line = reader.readLine()) != null) {
                arr.insert(Long.parseLong(line));
            }
            */

            for (int i = 0; i < size; i++) {
                line = reader.readLine();
                arr.insert(Long.parseLong(line));
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
