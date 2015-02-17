package chap13.programmingProjects.kt;

/**
 * Created by Sergei Doroshenko on 17.02.2015.
 */
public class Vertex {
    private int x;
    private int y;
    private int label;
    private boolean wasVisited;;

    public Vertex(int x, int y, int label) {
        this.x = x;
        this.y = y;
        this.label = label;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public boolean wasVisited() {
        return wasVisited;
    }

    public void setVisited(boolean wasVisited) {
        this.wasVisited = wasVisited;
    }

    @Override
    public String toString() {
        return label + "";
    }
}
