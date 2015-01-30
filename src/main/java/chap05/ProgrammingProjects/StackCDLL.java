package chap05.ProgrammingProjects;

/**
 * Created by Sergei Doroshenko on 23.01.2015.
 */
public class StackCDLL<T> {
    private CycleDoublyLinkedList<T> list = new CycleDoublyLinkedList<>();

    public StackCDLL() {
    }

    public void push(T item) {
        list.insert(item);
    }

    public T pop() {
        T t = list.delete();
        if (!list.isEmpty()) list.stepBackward();
        return t;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void displayStack() {
        System.out.println("StackCDLL (top --> bottom): " + list);
    }
}
