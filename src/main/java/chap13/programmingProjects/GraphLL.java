package chap13.programmingProjects;

import chap04.Queue.QueueO;
import chap04.Stack.StackO;
import chap05.SingleLinked.SinglyLinkedList;
import chap13.graph.AbstractGraph;
import chap13.graph.GraphN;

import java.util.Iterator;

/**
 * Created by Sergei Doroshenko on 12.02.2015.
 */
public class GraphLL extends GraphN {
    private SinglyLinkedList<Character> adjList[];      // adjacency list

    // ------------------------------------------------------------
    public GraphLL() {
        vertexList = new Vertex[MAX_VERTS];
        // adjacency matrix
        adjList = new SinglyLinkedList[MAX_VERTS];
        nVerts = 0;
    }  // end constructor

    // (from AbstractGraph)
    @Override
    public void addEdge(int start, int end) {
        if (adjList[start] == null) {
            adjList[start] = new SinglyLinkedList<>();
        }
        adjList[start].insertLast(vertexList[end].getLabel());

        if (adjList[end] == null) {
            adjList[end] = new SinglyLinkedList<>();
        }
        adjList[end].insertLast(vertexList[start].getLabel());
    }

    // returns an unvisited vertex adj to v (from AbstractGraph)
    @Override
    protected int getAdjUnvisitedVertex(int v) {
        for(int j = 0; j < nVerts; j++) {
            Vertex curr = vertexList[j];
            if (adjList[v].find(curr.getLabel()) != -1 && curr.wasVisited() == false) {
                return j;
            }
        }
        return -1;
    }  // end getAdjUnvisitedVertex()

    // (from AbstractGraph)
    @Override
    public void displayAdjacencyMatrix(){
        disblayAdjacencyList();
    }

    public void disblayAdjacencyList(){
        for(int y = 0; y < nVerts; y++) {     // set adjacency
           System.out.print(vertexList[y].getLabel() + ": ");
           for (Iterator<Character> iter = adjList[y].iterator(); iter.hasNext();) {
               System.out.print(iter.next());
               if (iter.hasNext()) System.out.print(" --> ");
           }
           System.out.println();
        }
    }
}
