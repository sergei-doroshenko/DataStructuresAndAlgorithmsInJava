package chap08.ProgrammingProjects;

/**
 * Created by Sergei Doroshenko on 02.02.2015.
 */
public class HuffmanNode {
    char character;         // data item (key)
    int frequency;          // frequency in the message
    HuffmanNode leftChild;         // this node's left child
    HuffmanNode rightChild;        // this node's right child

    public HuffmanNode() {
    }

    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "{" + character +", " + frequency + "}";
    }
}