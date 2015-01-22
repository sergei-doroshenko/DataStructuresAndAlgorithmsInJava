package chap05.LinkQueue;

import chap05.SingleLinked.SinglyLlinkedList;

/**
 * Created by user on 21.01.2015.
 */
public class QueueSLL<T> {
    SinglyLlinkedList<T> list;

    public QueueSLL() {
        list = new SinglyLlinkedList<>();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void insert (T item) {
        list.insertLast(item);
    }

    public T remove() {
        return list.deleteFirst();
    }

    public void displayQueue() {
        System.out.println("Queue (front-->rear): " + list);
    }
}
