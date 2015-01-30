package chap05.ProgrammingProjects;

/**
 * Created by Sergei Doroshenko on 23.01.2015.
 */
public class MatrixSLL<T> {
    private Node first;
    private int n; // vertical size
    private int m; // horizontal size

    public MatrixSLL(int n, int m) {
        this.n = n;
        this.m = m;
        populate();
    }

    public void insert(T item, int x, int y) {
        if (x > m || y > n) throw new IndexOutOfBoundsException("Out of bounds.");

        Node current = first;
        Node right = first;
        int i = x;
        while (i > 0) {
            current = current.left;
            i--;
        }

        Node up = current;
        current = first;

        int j = y;
        while (j > 0) {
            current = current.down;

            j--;
        }

        Node newNode = new Node(item);
        right.left = newNode;
        newNode.left = current.left;
        up.down = newNode;
        newNode.down = current.down;
    }

    private void populate() {
        first = new Node();
        Node current = first;

        for (int i = 0; i < n; i++) {
            current.down = new Node();
            current = current.down;
            Node y = current;
            for (int j = 0; j < m; j++) {
                y.left = new Node();
                y = y.left;
            }
        }
    }

    public void displayMatrix() {

        Node x = first;

        while (x != null) {
            Node y = x;
            while (y.left != null) {
                System.out.print(y.item + " - ");
                if (y.left != null) {
                    y = y.left;
                }
            }
            System.out.println("");
            x = x.down;
        }
    }

    private class Node {
        T item;
        Node left;
        Node down;

        Node() {}

        Node(T item) {
            this.item = item;
        }
    }
}
