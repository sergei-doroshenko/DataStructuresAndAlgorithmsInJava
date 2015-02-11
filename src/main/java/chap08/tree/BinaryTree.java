package chap08.tree;

import java.util.Stack;

/**
 * Created by Sergei Doroshenko on 30.01.2015.
 */
public class BinaryTree<T extends Comparable<T>> {
    private Node root;             // first node of tree
    private int size;
    // -------------------------------------------------------------
    // no nodes in tree yet
    public BinaryTree() {                   // constructor
        root = null;
    }
    // -------------------------------------------------------------
    public int size() { return size; }

    public T find(T key) {     // find node with given key (assumes non-empty tree)
        Node current = root;               // start at root
        while(!current.data.equals(key)) {       // while no match,
            if(key.compareTo(current.data) < 0) {       // go left?
                current = current.leftChild;
            } else {                            // or go right?
                current = current.rightChild;
            }

            if(current == null) {             // if no child,
                return null;                 // didn't find it
            }
        }
        return current.data;                    // found it
    }  // end find()
    // -------------------------------------------------------------
    public void insert(T key) {
        Node newNode = new Node();    // make new node
        newNode.data = key;           // insert data
        size++;
        if(root == null) {              // no node in root
            root = newNode;
        } else {                      // root occupied
            Node current = root;      // start at root
            Node parent;
            while(true) {             // (exits internally)
                parent = current;
                if(key.compareTo(current.data) < 0) {  // go left?
                    current = current.leftChild;
                    if(current == null) {           // if end of the line,
                        parent.leftChild = newNode; // insert on left
                        return;
                    }
                } else {    // end if go left or go right?
                    current = current.rightChild;
                    if(current == null) {            // if end of the line
                        parent.rightChild = newNode; // insert on right
                        return;
                    }
                }  // end else go right
            }  // end while
        }  // end else not root
    }  // end insert()
    // -------------------------------------------------------------
    public boolean delete(T key) { // delete node with given key (assumes non-empty list)
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;

        while(!current.data.equals(key)) {       // search for node

            parent = current;
            if(key.compareTo(current.data) < 0) {        // go left?
                isLeftChild = true;
                current = current.leftChild;
            } else {                         // or go right?
                isLeftChild = false;
                current = current.rightChild;
            }
            if(current == null) {            // end of the line,
                return false;                // didn't find it
            }
        }  // end while
        // found node to delete

        // if no children, simply delete it
        if(current.leftChild == null && current.rightChild == null) {
            if(current == root) {            // if root,
                root = null;                 // tree is empty
            } else if (isLeftChild) {
                parent.leftChild = null;     // disconnect
            } else {                         // from parent
                parent.rightChild = null;
            }

        } else if(current.rightChild == null) { // if no right child, replace with left subtree
            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }

        } else if(current.leftChild == null) {  // if no left child, replace with right subtree
            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }

        } else {  // two children, so replace with inorder successor
            // get successor of node to delete (current)
            Node successor = getSuccessor(current);

            // connect parent of current to successor instead
            if(current == root) {
                root = successor;
            } else if(isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
            }

            // connect successor to current's left child
            successor.leftChild = current.leftChild;
        }  // end else two children
        // (successor cannot have a left child)
        size--;
        return true;                                // success
    }  // end delete()
    // -------------------------------------------------------------
    // returns node with next-highest value after delNode
    // goes to right child, then right child's left descendents
    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;   // go to right child
        while(current != null) {             // until no more left children,
            successorParent = successor;
            successor = current;
            current = current.leftChild;      // go to left child
        }

        if(successor != delNode.rightChild) { // if successor not right child, make connections
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }

    public T max() {
        Node current = root;
        while (current.rightChild != null) current = current.rightChild;
        return current.data;
    }

    public T removeMax() {
        T max = max();
        delete(max);
        return max;
    }
    // -------------------------------------------------------------
    public void traverse(int traverseType) {
        switch(traverseType) {
            case 1:
                preOrder(root);
                break;
            case 2:
                inOrder(root);
                break;
            case 3:
                postOrder(root);
                break;
        }
        System.out.println();
    }
    // -------------------------------------------------------------
    private void preOrder(Node localRoot) {
        if(localRoot != null) {
            System.out.print(localRoot.data + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }
    // -------------------------------------------------------------
    private void inOrder(Node localRoot) {
        if(localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.data + " ");
            inOrder(localRoot.rightChild);
        }
    }
    // -------------------------------------------------------------
    private void postOrder(Node localRoot) {
        if(localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.data + " ");
        }
    }
    // -------------------------------------------------------------
    public void displayTree() {
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
    // -------------------------------------------------------------
    ////////////////////////////////////////////////////////////////
    public class Node {
        T data;                  // data item (key)

        Node leftChild;         // this node's left child
        Node rightChild;        // this node's right child

        void displayNode() {      // display ourself
            System.out.print('{');
            System.out.print(data);
            System.out.print("} ");
        }
    }  // end class Node
    ////////////////////////////////////////////////////////////////
}
