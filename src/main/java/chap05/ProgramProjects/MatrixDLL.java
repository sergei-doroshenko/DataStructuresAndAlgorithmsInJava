package chap05.ProgramProjects;

/**
 * Created by Sergei Doroshenko on 24.01.2015.
 */
public class MatrixDLL<T> {
    private Node first;
    private int xSize;
    private int ySize;

    public MatrixDLL(int x, int y) {
        this.xSize = x;
        this.ySize = y;
    }

    private Node stepRight(Node current) {
        if (current.right == null) {
            current.right = new Node();
            current.right.left = current;

            if (current.down != null && current.down.right != null) {
                current.down.right.up = current.right;
                current.right.down = current.down.right;
            }

            if (current.up != null && current.up.right != null) {
                current.up.right.down = current.right;
                current.right.up = current.up.right;
            }


        }
        current = current.right;
        return current;
    }

    private Node stepDown(Node current) {
        if (current.down == null) {
            current.down = new Node();

            if (current.left != null && current.left.down != null) {
                current.left.down.right = current.down;
                current.down.left = current.left.down;
            }

            if (current.right != null && current.right.down != null) {
                current.right.down.left = current.down;
                current.down.right = current.right.down;
            }
        }
        current = current.down;
        return current;
    }

    private Node getNode(int x, int y) {
        if (x >= xSize || y >= ySize) throw new IndexOutOfBoundsException("Index out of matrix bounds.");

        Node current = first;

        for (int i = 0; i < y; i++) {
            // move first --> right
            current = stepRight(current);
        }

        for (int j = 0; j < x; j++) {
            // move current --> down
            current = stepDown(current);
        }
        return current;
    }

    public void insert(T item, int x, int y) {
        Node current;

        if (first == null) {
            first = new Node();
            current = first;
        } else {
            current = getNode(x, y);
        }

        current.item = item;
    }

    public T delete(int x, int y) {
        Node current = getNode(x, y);
        T temp = current.item;
        current.item = null;
        return temp;
    }

    public T get(int x, int y) {
        return getNode(x, y).item;
    }

    public void displayMatrix() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        String string = "";
        Node x = first;

        while (x != null) {
            Node y = x;
            while (y != null) {
                string += y.item;
                if (y.right != null) {
                    string += " - ";
                }
                y = y.right;
            }
            if (x.down != null) {
                string += "\n";
            }

            x = x.down;
        }
        return string;
    }

    private class Node {
        T item;
        Node up;
        Node down;
        Node left;
        Node right;

        public Node() {}

        Node (T item) {
            this.item = item;
        }
    }
}
