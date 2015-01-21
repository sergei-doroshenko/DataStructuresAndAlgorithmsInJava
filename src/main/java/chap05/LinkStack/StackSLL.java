package chap05.LinkStack;

import chap05.linkList.SinglyLlinkedList;

/**
 * Created by user on 21.01.2015.
 */
public class StackSLL<T> {
    private SinglyLlinkedList<T> list;

    public StackSLL() {
        list = new SinglyLlinkedList<>();
    }

    public void push(T item) {
        list.insertFirst(item);
    }

    public T pop() {
        return list.deleteFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void displayStack() {
        System.out.println("Stack (top --> bottom): " + list);
    }
}
