package chap13.graph;

import chap04.Queue.QueueO;
import chap04.Stack.StackO;
import sun.security.provider.certpath.Vertex;

/**
 * Created by Sergei Doroshenko on 13.02.2015.
 */
public class GraphN extends AbstractGraph {

    public GraphN() {
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

    public void dfs() { // depth-first search
        StackO<Integer> theStack = new StackO<>(MAX_VERTS);
        // begin at vertex 0
        vertexList[0].wasVisited = true;  // mark it
        displayVertex(0);                 // display it
        theStack.push(0);                 // push it

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

        // stack is empty, so we're done
        resetFlags();
    }  // end dfs

    public void mst1() { // minimum spanning tree (depth first)
        StackO<Integer> theStack = new StackO<>(MAX_VERTS);
        // start at 0
        vertexList[0].wasVisited = true;   // mark it
        theStack.push(0);                  // push it

        while( !theStack.isEmpty() ) {      // until stack empty
            // get stack top
            int currentVertex = theStack.peek();
            // get next unvisited neighbor
            int v = getAdjUnvisitedVertex(currentVertex);
            if(v == -1) {                     // if no more neighbors
                theStack.pop();               // pop it away
            } else {                          // got a neighbor

                vertexList[v].wasVisited = true;  // mark it
                theStack.push(v);                 // push it
                // display (or save) edge
                displayVertex(currentVertex);     // from currentV
                displayVertex(v);                 // to v
                System.out.print(" ");
            }
        }  // end while(stack not empty)

        // stack is empty, so we're done
        resetFlags();
    }  // end mst1()

    public void bfs() {                  // breadth-first search

        QueueO<Integer> theQueue = new QueueO<>(MAX_VERTS);
        // begin at vertex 0
        vertexList[0].wasVisited = true; // mark it
        displayVertex(0);                // display it
        theQueue.insert(0);              // insert at tail
        int v2;

        while( !theQueue.isEmpty() ) {    // until queue empty,
            int v1 = theQueue.remove();   // remove vertex at head
            // until it has no unvisited neighbors
            while( (v2 = getAdjUnvisitedVertex(v1)) != -1 ) {
                // get one,
                vertexList[v2].wasVisited = true;  // mark it
                displayVertex(v2);                 // display it
                theQueue.insert(v2);               // insert it
            }   // end while
        }  // end while(queue not empty)

        // queue is empty, so we're done
        resetFlags();
    }  // end bfs()

    public void mst2() {                  // minimum spanning tree (breadth first)
        QueueO<Integer> theQueue = new QueueO<>(MAX_VERTS);

        // begin at vertex 0
        vertexList[0].wasVisited = true; // mark it

        theQueue.insert(0);              // insert at tail
        int v2;

        while( !theQueue.isEmpty() ) {    // until queue empty,
            int v1 = theQueue.remove();   // remove vertex at head
            // until it has no unvisited neighbors
            while( (v2 = getAdjUnvisitedVertex(v1)) != -1 ) {
                // get one,
                vertexList[v2].wasVisited = true;  // mark it
                displayVertex(v1);                  // display currentV
                displayVertex(v2);                 // display v
                theQueue.insert(v2);               // insert it
                System.out.print(" ");
            }   // end while
        }  // end while(queue not empty)

        // queue is empty, so we're done
        resetFlags();
    }  // end mts2()

}
