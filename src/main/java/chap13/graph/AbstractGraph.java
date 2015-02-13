package chap13.graph;

import chap04.Queue.QueueO;
import chap04.Stack.StackO;

/**
 * Created by Sergei Doroshenko on 13.02.2015.
 */
public abstract class AbstractGraph {
    protected final int MAX_VERTS = 20;
    protected Vertex[] vertexList; // list of vertices
    protected int[][] adjMat;      // adjacency matrix
    protected int nVerts;          // current number of vertices

    // ------------------------------------------------------------
    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }
    // ------------------------------------------------------------
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    protected int noSuccessors() { // returns vert with no successors
        // (or -1 if no such verts)
        boolean isEdge;  // edge from row to column in adjMat

        for(int row = 0; row < nVerts; row++) {  // for each vertex,

            isEdge = false;
            /************   check edges ******************/
            for(int col = 0; col < nVerts; col++) {
                if( adjMat[row][col] > 0 ) {  // if edge to another (has successor),
                    isEdge = true;
                    break;                    // this vertex has a successor
                }
            }                                 //    try another

            if( !isEdge ) {                  // if no edges,
                return row;                  //    has no successors
            }
        }
        return -1;                         // no such vertex
    }  // end noSuccessors()

    // ------------------------------------------------------------
    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }
    // ------------------------------------------------------------
    // returns an unvisited vertex adj to v
    protected int getAdjUnvisitedVertex(int v) {
        for(int j = 0; j < nVerts; j++) {
            if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false) {
                return j;
            }
        }
        return -1;
    }  // end getAdjUnvisitedVertex()

    protected void resetFlags() {
        for(int j=0; j<nVerts; j++) {         // reset flags
            vertexList[j].wasVisited = false;
        }
    }

    public void displayAdjacencyMatrix(){
        for(int y = 0; y < nVerts; y++) {     // set adjacency
            for (int x = 0; x < nVerts; x++) {   //    matrix to 0
                System.out.print(adjMat[x][y] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Vertex class
     * An instance of InnerClass can exist only within an instance of OuterClass
     * and has direct access to the methods and fields of its enclosing instance.
     */
    protected class Vertex {
        char label;        // label (e.g. 'A')
        boolean wasVisited;

        Vertex(char lab) {
            label = lab;
            wasVisited = false;
        }

        public char getLabel() {
            return label;
        }

        public boolean wasVisited() {
            return wasVisited;
        }
    }
}
