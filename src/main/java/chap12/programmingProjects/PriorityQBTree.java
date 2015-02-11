package chap12.programmingProjects;

import chap08.tree.BinaryTree;
import chap08.tree.Tree;
import chap12.heap.Heap;

/**
 * Created by Sergei Doroshenko on 11.02.2015.
 */
public class PriorityQBTree<T extends Comparable<T>> {
    private BinaryTree<T> tree = new BinaryTree<>();

    public PriorityQBTree() { }

    public int size() {return tree.size(); }
    public void insert(T data) { tree.insert(data); }
    public T remove() { return tree.removeMax(); }
}
