package chap03.ObjectSort;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by Sergei_Doroshenko on 1/12/2015.
 */
public class ObjectSortAppWithArrayOG {
    public static void main(String[] args) {
        int maxSize = 100;             // array size
        ArrayOG<Person> arr = new ArrayOG(maxSize);

        arr.insert(new Person("Evans", "Patty", 24));
        arr.insert(new Person("Smith", "Doc", 59));
        arr.insert(new Person("Smith", "Lorraine", 37));
        arr.insert(new Person("Smith", "Paul", 37));
        arr.insert(new Person("Yee", "Tom", 43));
        arr.insert(new Person("Hashimoto", "Sato", 21));
        arr.insert(new Person("Stimson", "Henry", 29));
        arr.insert(new Person("Velasquez", "Jose", 72));
        arr.insert(new Person("Vang", "Minh", 22));
        arr.insert(new Person("Creswell", "Lucinda", 18));

        System.out.println("Before sorting:");
        arr.display();                 // display items
        System.out.println("-----------------------------------------------------------");
        //---------------------------------------------------------------------
        Comparator<Person> lastNameComparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        };

        Comparator<Person> firstNameComparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        };

        Comparator<Person> ageComparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                int p1Age = o1.getAge();
                int p2Age = o2.getAge();

                if (p1Age == p2Age) return 0;
                if (p1Age < p2Age) return -1;

                return 1;
            }
        };
        //---------------------------------------------------------------------

//        arr.insertionSort(lastNameComparator);           // insertion-sort them
//        arr.bubbleSort2(lastNameComparator);
        arr.mergeSort(lastNameComparator);

        System.out.println("After sorting:");
        arr.display();                 // display them again
        System.out.println("-----------------------------------------------------------");
        System.out.println("For each loop:");
        for (Person p : arr) {
            System.out.println(p);
        }

        /* Alternative implementation
        Iterator<Person> iter = arr.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        */
    }  // end main()
}
