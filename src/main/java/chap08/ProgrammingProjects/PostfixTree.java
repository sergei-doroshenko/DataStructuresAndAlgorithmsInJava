package chap08.ProgrammingProjects;

import chap04.Stack.StackXChar;
import chap04.Stack.StackXInt;

import java.util.Stack;

/**
 * Created by Sergei Doroshenko on 30.01.2015.
 */
public class PostfixTree {
    private LetterNode root;             // first node of tree

    // -------------------------------------------------------------
    private PostfixTree() {
    }

    private PostfixTree(char root, char left, char right) {
        this.root = new LetterNode(root);
        this.root.leftChild = new LetterNode(left);
        this.root.rightChild = new LetterNode(right);
    }

    public LetterNode getRoot() {
        return root;
    }

    public void setRoot(LetterNode root) {
        this.root = root;
    }

    public void setLeftChild(LetterNode node) {
        this.root.leftChild = node;
    }

    public void setRightChild(LetterNode node) {
        this.root.rightChild = node;
    }

    public static PostfixTree init(String input) {
        Stack<PostfixTree> treeStack = new Stack<>();

        for(int j = 0; j < input.length(); j++) {   // for each char,
            char ch = input.charAt(j);              // read from input

            if (ch >= '0' && ch <= '9') {           // if it's a number
                PostfixTree tt = new PostfixTree(); // create new tree
                tt.root = new LetterNode(ch);       // set operand (number) as root of this tree
                treeStack.push(tt);                 // push created tree in tree stack

            } else {                                // it's an operator
                PostfixTree tt = new PostfixTree(); // create new tree
                tt.root = new LetterNode(ch);       // set operator (e.g. +, - or *) as root node of this tree
                /*
                pop previously created operand tree from tree stack
                and set it as right child node of root node
                */
                tt.root.rightChild = treeStack.pop().root;
                /*
                pop NEXT previously created operand tree from tree stack
                and set it as left child node of root node
                 */
                tt.root.leftChild = treeStack.pop().root;
                treeStack.push(tt); // push created tree in tree stack
            }

        }
        return treeStack.pop();
    }

    public void traverse(int traverseType) {
        switch(traverseType) {
            case 1: System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2: System.out.print("\nInorder traversal:  ");
                inOrder(root);
                break;
            case 3: System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println();
    }
    // -------------------------------------------------------------
    private void preOrder(LetterNode localRoot) {
        if(localRoot != null) {
            System.out.print(localRoot.cData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }
    // -------------------------------------------------------------
    private void inOrder(LetterNode localRoot) {

        if(localRoot != null) {
            System.out.print("(");
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.cData + " ");
            inOrder(localRoot.rightChild);
            System.out.print(")");
        }

    }
    // -------------------------------------------------------------
    private void postOrder(LetterNode localRoot) {
        if(localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.cData + " ");
        }
    }
    // -------------------------------------------------------------
    public void displayTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("...............................................................");
        while(isRowEmpty==false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for(int j = 0; j < nBlanks; j++) {
                System.out.print(' ');
            }

            while(globalStack.isEmpty()==false) {
                LetterNode temp = (LetterNode)globalStack.pop();
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
        System.out.println("...............................................................");
    }  // end displayTree()

}
