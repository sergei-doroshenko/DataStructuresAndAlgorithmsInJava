package chap13.programmingProjects;

import chap13.graph.GraphN;

/**
 * Created by Sergei Doroshenko on 13.02.2015.
 */
public class GraphLLApp {
    public static void main(String[] args) {
        GraphLL theGraph = new GraphLL();
        theGraph.addVertex('A');    // 0  (start for dfs)
        theGraph.addVertex('B');    // 1
        theGraph.addVertex('C');    // 2
        theGraph.addVertex('D');    // 3
        theGraph.addVertex('E');    // 4
        theGraph.addVertex('F');    // 5
        theGraph.addVertex('G');    // 6
        theGraph.addVertex('H');    // 7
        theGraph.addVertex('I');    // 8

        theGraph.addEdge(0, 1);     // AB
        theGraph.addEdge(1, 5);     // BF
        theGraph.addEdge(5, 7);     // FH
        theGraph.addEdge(0, 2);     // AC
        theGraph.addEdge(0, 3);     // AD
        theGraph.addEdge(3, 6);     // DG
        theGraph.addEdge(6, 8);     // GI
        theGraph.addEdge(0, 4);     // AE
        theGraph.addEdge(5, 6);     // FG


        System.out.print("DFS: ");
        theGraph.dfs();             // depth-first search
        System.out.println();
        theGraph.displayAdjacencyMatrix();
        System.out.print("BFS: ");
        theGraph.bfs();             // breadth-first search
        System.out.println();
        System.out.print("Minimum spanning tree(v.1): ");
        theGraph.mst1();             // minimum spanning tree v.1
        System.out.println();
        System.out.print("Minimum spanning tree(v.1): ");
        theGraph.mst2();             // minimum spanning tree v.2
        System.out.println();
    }  // end main()
}
