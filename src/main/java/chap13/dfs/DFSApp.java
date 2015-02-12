package chap13.dfs;

import chap13.graph.Graph;

/**
 * Created by Sergei Doroshenko on 12.02.2015.
 */
public class DFSApp {
    public static void main(String[] args) {
        Graph theGraph = new Graph();
        theGraph.addVertex('A');    // 0  (start for dfs)
        theGraph.addVertex('B');    // 1
        theGraph.addVertex('C');    // 2
        theGraph.addVertex('D');    // 3
        theGraph.addVertex('E');    // 4
        theGraph.addVertex('F');    // 5

        theGraph.addEdge(0, 1);     // AB
        theGraph.addEdge(1, 2);     // BC
        theGraph.addEdge(0, 3);     // AD
        theGraph.addEdge(0, 4);     //AE
        theGraph.addEdge(4, 5);     // EF

        System.out.print("Visits: ");
        theGraph.dfs();             // depth-first search
        System.out.println();
    }  // end main()
}
