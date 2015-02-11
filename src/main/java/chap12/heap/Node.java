package chap12.heap;

/**
 * Created by Sergei Doroshenko on 11.02.2015.
 */
public class Node<T> {
    private T key;

    public Node(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }
}
