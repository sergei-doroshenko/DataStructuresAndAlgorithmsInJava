package chap05.ProgrammingProjects;

import chap05.SortedLinkList.SortedSinglyLinkedList;

/**
 * Created by user on 23.01.2015.
 */
public class PriorityQueueSSLL<T extends Comparable> {
    private SortedSinglyLinkedList<T> list;

    public PriorityQueueSSLL() {
        list = new SortedSinglyLinkedList<>();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public void insert (T item) {
        list.insert(item);
    }

    public T remove() {
        return list.deleteFirst();
    }

    @Override
    public String toString() {
        return String.format("PriorityQueueSLL (size: %2d){ %s}", size(), list);
    }
}
