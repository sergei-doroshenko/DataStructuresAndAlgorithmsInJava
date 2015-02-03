package chap08.ProgrammingProjects;

/**
 * Created by Sergei Doroshenko on 02.02.2015.
 */
public class LetterNode {
    char cData;           // data item (key)
    LetterNode leftChild;         // this node's left child
    LetterNode rightChild;        // this node's right child

    public LetterNode() {
    }

    public LetterNode(char cData) {
        this.cData = cData;
    }

    @Override
    public String toString() {
        return "'" + cData + '\'';
    }
}  // end class Node
