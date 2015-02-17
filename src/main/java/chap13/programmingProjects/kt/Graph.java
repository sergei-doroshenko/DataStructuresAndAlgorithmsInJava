package chap13.programmingProjects.kt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergei Doroshenko on 17.02.2015.
 */
public class Graph {
    private int size;
    private Vertex vertexList[]; // list of vertices
    private int adjMat[][];      // adjacency matrix
    private int nVerts;          // current number of vertices

    public Graph(int size) {
        this.size = size;
        this.vertexList = new Vertex[size];
        this.adjMat = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                adjMat[i][j] = 0;
            }
        }
    }

    public void addVertex(int x, int y, int lab) {
        vertexList[nVerts++] = new Vertex(x, y, lab);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
    }

    public List<Vertex> getConnections(Vertex v) {
        List<Vertex> list = new ArrayList<>();

        for(int j = 0; j < nVerts; j++) {
            if (adjMat[v.getLabel()][j] == 1) {
                list.add(vertexList[j]);
            }
        }

        return list;
    }

    public Vertex[] getVertexList() {
        return vertexList;
    }

    public void displayAdjacencyMatrix(){
        System.out.print(" | ");
        for (int i = 0; i < nVerts; i++) {
            System.out.printf(" %2s ", vertexList[i].getLabel());
        }
        System.out.println("");

        for(int y = 0; y < nVerts; y++) {     // set adjacency
            System.out.printf("%2d|", vertexList[y].getLabel());
            for (int x = 0; x < nVerts; x++) {   //    matrix to 0
                System.out.printf(" %2d ", adjMat[y][x]);
            }
            System.out.println();
        }
    }
}
