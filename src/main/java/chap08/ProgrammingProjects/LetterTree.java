package chap08.ProgrammingProjects;

import java.util.Stack;

/**
 * Created by Sergei Doroshenko on 30.01.2015.
 */
public class LetterTree {
    private Node root;      // first node of tree

    private LetterTree() {  // default constructor
        root = new Node('+');
    }

    public static LetterTree getInstance(char[] chars) {
        LetterTree[] trees;
        if (chars.length % 2 == 0) {
            trees = new LetterTree[chars.length/2];
            for (int i = 0, j = 0; i < chars.length; i += 2, j++) {
                trees[j] = new LetterTree();
                trees[j].root.leftChild = new Node(chars[i]);
                trees[j].root.rightChild = new Node(chars[i + 1]);
            }
        } else {
            trees = new LetterTree[chars.length/2 + 1];
            int j = 0;
            for (int i = 0; i < chars.length - 1; i += 2, j++) {
                trees[j] = new LetterTree();
                trees[j].root.leftChild = new Node(chars[i]);
                trees[j].root.rightChild  = new Node(chars[i + 1]);
            }
            trees[j] = new LetterTree();
            trees[j].root.leftChild = new Node(chars[chars.length - 1]);
        }

        while (trees.length > 1) {
            LetterTree[] temp;
            if (trees.length % 2 == 0) {
                temp = new LetterTree[trees.length/2];
                for (int i = 0, j = 0; i < trees.length; i += 2, j++) {
                    temp[j] = trees[i].add(trees[i + 1]);
                }
            } else {
                temp = new LetterTree[trees.length/2 + 1];
                int j = 0;
                for (int i = 0; i < trees.length - 1; i += 2) {
                    temp[j] = trees[i].add(trees[i + 1]);
                }
                temp[j] = trees[trees.length - 1]; // add last tree
            }
            trees = temp;
        }
        return trees[0];
    }

    public LetterTree add(LetterTree tree) {
        if (this.root.leftChild == null && this.root.rightChild == null) {
            return tree;
        } else {
            LetterTree temp = new LetterTree();
            temp.root.leftChild = this.root;
            temp.root.rightChild = tree.root;
            return temp;
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
                    System.out.print(temp.cData);
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
    public static class Node {
        char cData;           // data item (key)
        Node leftChild;         // this node's left child
        Node rightChild;        // this node's right child

        public Node() {
        }

        public Node(char cData) {
            this.cData = cData;
        }

        @Override
        public String toString() {
            return "'" + cData + '\'';
        }
    }  // end class Node
    ////////////////////////////////////////////////////////////////
}
