package chap12.programmingProjects;

import chap12.heap.Heap;

/**
 * Created by Sergei Doroshenko on 11.02.2015.
 */
public class PriorityQHeap<T> {
    private Heap<T> heap;

    public PriorityQHeap(int size) {
        this.heap = new Heap<>(size);
    }
    public int size() {return heap.size(); }
    public void insert(T item) { heap.insert(item); }
    public T remove() { return heap.remove(); }
}
