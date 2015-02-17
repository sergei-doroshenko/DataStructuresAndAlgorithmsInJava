package chap14.mstw;

/**
 * Created by Sergei Doroshenko on 17.02.2015.
 */
public class Edge {
    public int srcVert;   // index of a vertex starting edge
    public int destVert;  // index of a vertex ending edge
    public int distance;  // distance from src to dest
    // -------------------------------------------------------------
    public Edge(int sv, int dv, int d) { // constructor
        srcVert = sv;
        destVert = dv;
        distance = d;
    }
}
