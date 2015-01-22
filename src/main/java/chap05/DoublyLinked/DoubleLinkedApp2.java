package chap05.DoublyLinked;

import chap05.Person;

/**
 * Created by user on 22.01.2015.
 */
public class DoubleLinkedApp2 {
    public static void main (String[] args) {
        DoublyLinkedList<Person> pList = new DoublyLinkedList<>();
        pList.insertFirst(new Person("Jhon", 35));
        pList.insertFirst(new Person("Karl", 53));
        pList.insertFirst(new Person("Mike", 73));
        pList.displayForward();

        for (Person p : pList) {
            if (p.getAge() < 70) {
                System.out.println(p);
                p.setAge(p.getAge() * 2);
            }
        }
        pList.displayForward();

        for(DoublyLinkedList.DLLIterator<Person> iterator = pList.dllIterator(); iterator.hasNext(); ) {
            Person person  = iterator.next();
            if (person.getAge() > 100) {
                person.setAge(person.getAge()/2);
            }
        }
        pList.displayForward();

        for(DoublyLinkedList.DLLIterator<Person> iterator = pList.dllIterator(); iterator.hasNext(); ) {
            Person person  = iterator.next();
            System.out.println(person);
            if (person.getAge() == 70) {
                //iterator.insertAfter(new Person("Debs", 24));
                //iterator.insertBefore(new Person("Debs", 24));
                iterator.remove();
            }
        }
        pList.displayForward();
    }

}
