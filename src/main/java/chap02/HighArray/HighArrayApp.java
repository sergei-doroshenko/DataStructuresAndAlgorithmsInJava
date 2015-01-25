package chap02.HighArray;

/**
 * Created by Sergei on 09.01.2015.
 */
public class HighArrayApp {
    public static void main(String[] args) {
        int maxSize = 100;            // array size
        HighArray arr;                // reference to array
        arr = new HighArray(maxSize); // create the array

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

        arr.display();                // display items

        int searchKey = 35;           // search for item
        if( arr.find(searchKey) )
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't findL " + searchKey);

        arr.delete(00);               // delete 3 items
        arr.delete(55);
        arr.delete(99);

        arr.display();                // display items again

        System.out.println("Max: " + arr.get(arr.getMax()));
        System.out.println("Remove Max: " + arr.removeMax());
        arr.display();

        HighArray sorted = new HighArray(arr.size());

        while (arr.size() > 0) {
            sorted.insert(arr.removeMax());
        }
        sorted.display();

        System.out.println("-----------------------------------------------------------");
        arr.insert(77);              // insert 10 items
        arr.insert(22);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(33);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(22);
        arr.insert(66);
        arr.insert(33);

        arr.display();
        arr.noDups();
        arr.display();
    }  // end main()
}
