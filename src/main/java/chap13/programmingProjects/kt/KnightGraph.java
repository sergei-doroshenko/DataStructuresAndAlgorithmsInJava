package chap13.programmingProjects.kt;

import chap04.Stack.StackO;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergei Doroshenko on 17.02.2015.
 * Knight's tour implementation with Graph
 */
public class KnightGraph {
    private int bdSize; // border size
    private Graph graph; // graph
    private int[][] moveOffsets = { // move variants
            {-1,-2},{-1,2},{-2,-1},{-2,1},
            { 1,-2},{ 1,2},{ 2,-1},{ 2,1}
    };
    private int[][] board; // board matrix with numbers from 0 to n = border size
    private int[][] result; // result matrix
    private StackO<Vertex> path; // stack to hold tour vertexes one by one

    /* Constructor */
    public KnightGraph (int bdSize) {
        this.bdSize = bdSize; // border size
        this.graph = new Graph(bdSize * bdSize); // graph will contains all possible vertexes (e.g. for n = 5 --> 25)
        /* Init graph: vertex is possible position, edges are possible moves from position
         * so every position has it's own set of moves */
        for (int i = 0; i < bdSize; i++) {
            for (int j = 0; j < bdSize; j++) {
                int nodeId = posToNodeId(i, j);
                graph.addVertex(i, j, nodeId); // add vertex

                List<Vertex> newPositions = genLegalMoves(i, j); // get all possible moves from current position
                for (Vertex vertex : newPositions) {
                    int nid = posToNodeId(vertex.getX(), vertex.getY());
                    graph.addEdge(nodeId, nid); // and adds edges
                }

            }
        }

        path = new StackO<>(bdSize * bdSize);
        result = new int[bdSize][bdSize];
    }

    /**
     * Helper method converts a location on the board in terms
     * of a row and a column into a linear vertex number
     * @param i - row
     * @param j - column
     * @return - vertex id number
     */
    private int posToNodeId(int i, int j) {
        if (board == null) {
            board = new int[bdSize][bdSize];
            int k = 0;
            for (int x = 0; x < bdSize; x++) {
                for (int y = 0; y < bdSize; y++) {
                    board[x][y] = k++;
                }
            }
        }
        return board[i][j];
    }

    /**
     * Helper method to create a list of legal moves for that position on the board
     * @param x - row
     * @param y - column
     * @return ArrayList of possible vertexes
     */
    public List<Vertex> genLegalMoves(int x, int y) {
        List<Vertex> newMoves = new ArrayList<>();

        for (int i = 0; i < moveOffsets.length; i++) {
            int newX = x + moveOffsets[i][0];
            int newY = y + moveOffsets[i][1];
            if (legalCoord(newX) && legalCoord(newY)) {
                newMoves.add(new Vertex(newX, newY, posToNodeId(newX, newY)));
            }
        }

        return newMoves;
    }

    /**
     * Helper method checks is coordinate legal.
     * @param x - coordinate
     * @return true or false
     */
    private boolean legalCoord(int x) {
        if (x >= 0 && x < bdSize) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Recursive method
     * @param n - the current depth in the search tree (number of move)
     * @param path - stack of vertices visited up to this point
     * @param vertex - the vertex in the graph we wish to explore
     * @param limit - the number of nodes in the path (e.g. for n = 5 max moves = 25)
     * @return
     */
    public boolean knightTour(int n, StackO<Vertex> path, Vertex vertex, int limit) {
        boolean done = false;

        vertex.setVisited(true);
        path.push(vertex);
        result[vertex.getY()][vertex.getX()] = n;

        /*
        Base condition.
        If we have a path that contains 64 vertices, we return from knightTour with a status of True,
        indicating that we have found a successful tour. If the path is not long enough
        we continue to explore one level deeper by choosing a new vertex to explore and calling
        knightTour recursively for that vertex.
         */
        if (n < limit) {
            List<Vertex> nbrList = graph.getConnections(vertex);
            int i = 0;
            while (i < nbrList.size() && done == false) { // loop through all vertexes near the exploring
                Vertex ver = nbrList.get(i);
                if (ver.wasVisited() == false) {
                    done = knightTour(n + 1, path, ver, limit);
                }
                i++;
            }
            /* Backtracking happens when we return from knightTour with false result
            * that's mean that we explore the vertex and found out dead end */
            if (done == false) {
                vertex.setVisited(false);
                path.pop();
                result[vertex.getY()][vertex.getX()] = 0;
            }

        } else {
            done = true;
        }

        return done;
    }

    /**
     * Display results matrix
     */
    public void display() {
        for (int i = 0; i < bdSize; i++) {
            for(int j = 0; j < bdSize; j++) {
                System.out.printf("%2d ", result[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Starts the knight's tour
     */
    public void go() {
        if ( knightTour(1, this.path, graph.getVertexList()[0], bdSize * bdSize) ) {
            path.displayStack("");
            display();
        } else {
            System.out.println("No solution.");
        }

    }

    public static void main (String[] args) {
        KnightGraph kg = new KnightGraph(5);
        //kg.graph.displayAdjacencyMatrix();
        kg.go();
    }

}
