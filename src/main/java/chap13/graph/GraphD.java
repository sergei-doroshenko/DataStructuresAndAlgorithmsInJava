package chap13.graph;

import chap04.Stack.StackO;

/**
 * Created by Sergei Doroshenko on 13.02.2015.
 * Directed graph implementation
 */
public class GraphD extends AbstractGraph {

    public GraphD() {
        vertexList = new Vertex[MAX_VERTS];
        // adjacency matrix
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;

        for(int y=0; y<MAX_VERTS; y++) {     // set adjacency
            for (int x = 0; x < MAX_VERTS; x++) {   //    matrix to 0
                adjMat[x][y] = 0;
            }
        }
    }  // end constructor

    public GraphD(int maxVerts) {
        vertexList = new Vertex[maxVerts];
        // adjacency matrix
        adjMat = new int[maxVerts][maxVerts];
        nVerts = 0;

        for(int y = 0; y < maxVerts; y++) {     // set adjacency
            for (int x = 0; x < maxVerts; x++) {   //    matrix to 0
                adjMat[x][y] = 0;
            }
        }
    }  // end constructor

    @Override
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
    }

    public void addVertex(int lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }


    public void topo() { // toplogical sort
        char sortedArray[] = new char[MAX_VERTS];  // sorted vert labels
        int orig_nVerts = nVerts;  // remember how many verts

        while(nVerts > 0) { // while vertices remain,

            // get a vertex with no successors, or -1
            int currentVertex = noSuccessors();

            if(currentVertex == -1) {      // must be a cycle
                System.out.println("ERROR: Graph has cycles");
                return;
            }
            // insert vertex label in sorted array (start at end)
            sortedArray[nVerts - 1] = vertexList[currentVertex].label;

            deleteVertex(currentVertex);  // delete vertex
        }  // end while

        // vertices all gone; display sortedArray
        System.out.print("Topologically sorted order: ");
        for(int j = 0; j < orig_nVerts; j++) {
            System.out.print(sortedArray[j]);
        }
        System.out.println("");
    }  // end topo
    // -------------------------------------------------------------
    public void deleteVertex(int delVert) {
        /*
        if not last vertex,
        (if it last we don't need to shift elements (vertexes) in vertex array and to move rows and columns;
        so we just decrement nVerts.
         */
        if(delVert != nVerts - 1) {      //

            // delete from vertexList
            for(int j = delVert; j < nVerts-1; j++) {
                vertexList[j] = vertexList[j + 1];
            }

            // delete row from adjMat
            for(int row = delVert; row < nVerts-1; row++) {
                moveRowUp(row, nVerts);
            }

            // delete col from adjMat
            for(int col = delVert; col < nVerts-1; col++) {
                moveColLeft(col, nVerts - 1);
            }
        }
        nVerts--;                    // one less vertex
    }  // end deleteVertex
    // -------------------------------------------------------------
    private void moveRowUp(int row, int length) {
        for(int col=0; col<length; col++) {
            adjMat[row][col] = adjMat[row + 1][col];
        }
    }
    // -------------------------------------------------------------
    private void moveColLeft(int col, int length) {
        for(int row=0; row<length; row++) {
            adjMat[row][col] = adjMat[row][col + 1];
        }
    }

    public void displayConnectivityTable() {
        StackO<Integer> theStack = new StackO<>(MAX_VERTS);
        // begin at vertex 0

        for (int i = 0; i < nVerts; i++) {
            vertexList[i].wasVisited = true;  // mark it
            displayVertex(i);                 // display it
            theStack.push(i);                 // push it

            while( !theStack.isEmpty() ) {    // until stack empty,

                // get an unvisited vertex adjacent to stack top
                int v = getAdjUnvisitedVertex( theStack.peek() );
                if(v == -1) {                    // if no such vertex,
                    theStack.pop();
                } else {                              // if it exists,
                    vertexList[v].wasVisited = true;  // mark it
                    displayVertex(v);                 // display it
                    theStack.push(v);                 // push it
                }
            }  // end while
            System.out.println();
            // stack is empty, so we're done
            resetFlags();
        }
    }

    /**
     * Warshall’s Algorithm implementation
     */
    public void createTransitiveClosure() {
        for (int y = 0; y < nVerts; y++) {         // loop through each row 'y' became A, B, C ... etc.
            for (int x = 0; x < nVerts; x++) {     // than loop through each cell in the row
                if (adjMat[x][y] == 1) {           // find edge?
                    for (int z = 0; z < nVerts; z++) { // check appropriate column for edges ended at vertex 'y'
                        if (adjMat[y][z] == 1 && adjMat[x][z] != 1) { // find edge? adjMat[x][z] - new vertex
                            addEdge(x, z);                            // create new vertex
                        }
                    }
                }
            }
        }

    }
}
