package chap12.treeHeap;

import chap12.heap.Node;
import libs.AppUtils;

import java.util.Stack;

/**
 * Created by Sergei Doroshenko on 11.02.2015.
 */
public class TreeHeap<T extends Comparable<T>> {
    private int size;
    private Node root;

    public TreeHeap() {
        root = null;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    private Node findNode (int index) {
        if (index <= 0) return null;

        Node current = root;
        int[] arr = AppUtils.toBinaryArr(index);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 0) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }

        return current;
    }

    public boolean insert(T key) {
        Node newNode = new Node();    // make new node
        newNode.data = key;           // insert data


        if(root == null) {              // no node in root
            root = newNode;
        } else {
            Node current = root;
            int[] arr = AppUtils.toBinaryArr(size+1);
            for (int i = 1; i < arr.length-1; i++) {
                if (arr[i] == 0) {
                    current = current.leftChild;
                } else {
                    current = current.rightChild;
                }
            }
            newNode.parent = current;

            if (current.leftChild == null) {
                current.leftChild = newNode;
            } else {
                current.rightChild = newNode;
            }
        }

        trickleUp(newNode);
        size++;
        return true;
    }  // end insert()

    /* Insert item without reordering */
    public boolean toss(T key) {
        Node newNode = new Node();    // make new node
        newNode.data = key;           // insert data


        if(root == null) {              // no node in root
            root = newNode;
        } else {
            Node current = root;
            int[] arr = AppUtils.toBinaryArr(size+1);
            for (int i = 1; i < arr.length-1; i++) {
                if (arr[i] == 0) {
                    current = current.leftChild;
                } else {
                    current = current.rightChild;
                }
            }
            newNode.parent = current;

            if (current.leftChild == null) {
                current.leftChild = newNode;
            } else {
                current.rightChild = newNode;
            }
        }

        size++;
        return true;
    }

    public void trickleUp(Node current) {
        Node parent = current.parent;

        while( current.parent != null && parent.data.compareTo(current.data) < 0) { // parent < current
            T temp = parent.data;
            parent.data = current.data;
            current.data = temp;
            current = parent;
            parent = current.parent;
        }  // end while

    }  // end trickleUp()

    public T remove() {          // delete item with max key
        // (assumes non-empty list)
        T rootValue = root.data;
        Node last = findNode(size--);
        root.data = last.data;
        if (last.isLeft()) {
            last.parent.leftChild = null;
        } else {
            last.parent.rightChild = null;
        }
        trickleDown(root);

        return rootValue;
    }  // end remove()

    public void trickleDown(Node current) {

        Node largerChild;

        while(current.leftChild != null || current.rightChild != null) {    // while node has at least one child,

            Node leftChild = current.leftChild;
            Node rightChild = current.rightChild;

            // find larger child
            if (leftChild == null) { // (rightChild exists?)
                largerChild = rightChild;
            } else if (rightChild == null) {
                largerChild = leftChild;
            } else {
                if(leftChild.data.compareTo(rightChild.data)  < 0) { // leftChild < rightChild
                    largerChild = rightChild;
                } else {
                    largerChild = leftChild;
                }
            }

            // top >= largerChild?
            if( current.data.compareTo(largerChild.data) >= 0 ) // current >= largeChild
                break;
            /******* shift child up **********/
            T temp = current.data;
            current.data = largerChild.data;
            largerChild.data = temp;

            current = largerChild;            // go down
        }  // end while

    }  // end trickleDown()

    public boolean change(int index, T newValue) {

        if(index < 0 || index > size) {
            return false;
        }

        Node current = findNode(index); // find node
        T oldValue = current.data;      // remember old
        current.data = newValue;

        if(oldValue.compareTo(newValue) < 0) { // if raised, oldValue.compareTo(newValue)
            trickleUp(current);                // trickle it up
        } else {                               // if lowered,
            trickleDown(current);              // trickle it down
        }

        return true;
    }  // end change()

    /**
     * size/2 points to first node from last (without children) nodes
     * We can start at node N/2-1, the rightmost node with children, instead of N-1,
     * the last node. Thus, we need only half as many trickle operations as we would using
     * insert() N times.
     */
    public void restoreHeap() {
        for(int j = size/2; j > 0; j--) { // make random array into heap
            this.trickleDown(findNode(j));
        }
    }

    // -------------------------------------------------------------
    public void displayHeap() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("......................................................");
        while(isRowEmpty==false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for(int j = 0; j < nBlanks; j++) {
                System.out.print(' ');
            }

            while(globalStack.isEmpty()==false) {
                Node temp = (Node)globalStack.pop();
                if(temp != null) {
                    System.out.print(temp.data);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if(temp.leftChild != null || temp.rightChild != null) {
                        isRowEmpty = false;
                    }

                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }

                for(int j = 0; j < nBlanks*2-2; j++) {
                    System.out.print(' ');
                }
            }  // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while(localStack.isEmpty()==false) {
                globalStack.push(localStack.pop());
            }
        }  // end while isRowEmpty is false
        System.out.println("......................................................");
    }  // end displayTree()

    ////////////////////////////////////////////////////////////////
    public class Node {
        T data;                  // data item (key)
        Node parent;
        Node leftChild;         // this node's left child
        Node rightChild;        // this node's right child

        boolean isLeft() {
            if (parent.leftChild == this) return true;
            return false;
        }

        void displayNode() {      // display ourself
            System.out.print('{');
            System.out.print(data);
            System.out.print("} ");
        }
    }  // end class Node
    ////////////////////////////////////////////////////////////////

}
