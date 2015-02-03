package chap08.ProgrammingProjects;

import java.util.Stack;

/**
 * Created by Sergei Doroshenko on 30.01.2015.
 */
public class LetterTree2 {
    private LetterNode root;             // first node of tree

    // -------------------------------------------------------------
    public LetterTree2() {                   // constructor
        root = null;  // no nodes in tree yet
    }

    public void init(char[] chars) {
        root = ins(chars, 0);
    }

    /**
     * If you think of the nodes as being numbered in the same order the letters are arranged, with 1 at the root,
     * then any node numbered n has a left child numbered 2*n and a right child numbered 2*n+1.
     * You might use a recursive routine that makes two children and then calls itself for each child.
     *
     * @param chars - array of char
     * @param ind - start index of char array
     * @return - LetterNode instance
     */

    public LetterNode ins(char[] chars, int ind) {
        if (ind > chars.length -1) return null;

        LetterNode newNode = new LetterNode(chars[ind]);
        newNode.leftChild = ins(chars, ind * 2 + 1);
        newNode.rightChild = ins(chars, ind *2 + 2);
        return newNode;
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
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.cData + " ");
            inOrder(localRoot.rightChild);
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
