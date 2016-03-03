package interview._01_test;

import java.util.*;

public class MagicForest01 {
    private Node[] nodeList;
    private int[][] adjMat;
    private Set<Edge> edges;
    /**
     * @param nodes Number of nodes in the magic forest. Nodes are numbered 0 .. nodes-1.
     * @param edges List of edges.
     */
    public MagicForest01(int nodes, List<Edge> edges) {
        nodeList = new Node[nodes];
        adjMat = new int[nodes][nodes];

        for(int y = 0; y < nodes; y++) {
            nodeList[y] = new Node(y);
            for (int x = 0; x < nodes; x++) {
                adjMat[x][y] = 0;
            }
        }
        this.edges = new HashSet(edges);

        for (Edge edge : edges) {
            adjMat[edge.getFrom()][edge.getTo()] = 1;
        }

    }

    public int dfs(int start) { // depth-first search
        System.out.println();
        int next = start;
        Stack<Integer> theStack = new Stack<>();
        Set<Node> temp = new HashSet<>();
        // begin at vertex 0
        nodeList[start].wasVisited = true;  // mark it
//        displayVertex(start);                 // display it
        temp.add(nodeList[start]);
        theStack.push(start);                 // push it

        while( !theStack.isEmpty() ) {    // until stack empty,

            // get an unvisited vertex adjacent to stack top
            int v = getAdjUnvisitedVertex( theStack.peek() );
            if(v == -1) {                    // if no such vertex,
                theStack.pop();

            } else {                              // if it exists

                nodeList[v].wasVisited = true;  // mark it
//                displayVertex(v);                 // display it
                temp.add(nodeList[v]);
                if (v > next) next = v;
                theStack.push(v);                 // push it
            }
        }  // end while

        // stack is empty, so we're done
        resetFlags();
        analyzeTree(temp);
        return next;
    }  // end dfs

    private boolean analyzeTree(Set<Node> tree) {
        System.out.println(tree);
        return true;
    }

    private int getAdjUnvisitedVertex(int v) {
        for(int j = 0; j < nodeList.length; j++) {
            if (adjMat[v][j] == 1 && nodeList[j].wasVisited == false) {
                return j;
            }
        }
        return -1;
    }

    private void resetFlags() {
        for(int j=0; j < nodeList.length; j++) {
            nodeList[j].wasVisited = false;
        }
    }

    public int countTrees() {
        for (int i = 0; i < nodeList.length; ) {
            int next = dfs(i);
            i = next+1;
        }
        return 1;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<Edge>();
        edges.add(new Edge(1, 2));
        edges.add(new Edge(3, 4));
        edges.add(new Edge(3, 5));
        edges.add(new Edge(4, 5));
        edges.add(new Edge(6, 7));
        edges.add(new Edge(6, 8));
        edges.add(new Edge(6, 9));

        MagicForest01 magicForest = new MagicForest01(10, edges);
//        System.out.println(magicForest.countTrees());
        magicForest.countTrees();
    }
}

class Edge {
    private int from;
    private int to;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (from != edge.from) return false;
        return to == edge.to;

    }

    @Override
    public int hashCode() {
        int result = from;
        result = 31 * result + to;
        return result;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}

class Node {
    int lab;
    boolean wasVisited;

    Node(int lab) {
        this.lab = lab;
        this.wasVisited = false;
    }

    public int getLab() { return lab; }

    public boolean wasVisited() {
        return wasVisited;
    }

    public void setVisited(boolean wasVisited) {
        this.wasVisited = wasVisited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return lab == node.lab;

    }

    @Override
    public int hashCode() {
        return lab;
    }

    @Override
    public String toString() {
        return "{" + lab + '}';
    }
}