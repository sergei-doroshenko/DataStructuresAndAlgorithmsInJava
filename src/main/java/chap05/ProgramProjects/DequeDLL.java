package chap05.ProgramProjects;

import chap05.DoublyLinked.DoublyLinkedList;

/**
 * Created by Sergei Doroshenko on 23.01.2015.
 */
public class DequeDLL<T> {
    private DoublyLinkedList<T> list = new DoublyLinkedList<>();

    public DequeDLL() {
    }

    public void insertLeft(T item) {
        list.insertFirst(item);
    }

    public void insertRight(T item) {
        list.insertLast(item);
    }

    public T removeLeft() {
        return list.deleteFirst();
    }

    public T removeRight() {
        return list.deleteLast();
    }

    public T peekFront() {
        return list.getFirst();
    }

    public T peekRight() {
        return list.getLast();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return "DequeDLL(size:" + size() + "){ " + list + '}';
    }
}
