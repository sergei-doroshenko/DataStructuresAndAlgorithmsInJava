package chap14.mstw;

/**
 * Created by Sergei Doroshenko on 17.02.2015.
 */
public class Vertex {
    public char label;        // label (e.g. 'A')
    public boolean isInTree;
    // -------------------------------------------------------------
    public Vertex(char lab) {  // constructor
        label = lab;
        isInTree = false;
    }

    @Override
    public String toString() {
        return label + ", " + isInTree;
    }
}
