package chap13.graph;

import chap04.Queue.QueueO;
import chap04.Stack.StackO;
import libs.AppUtils;
import sun.security.provider.certpath.Vertex;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sergei Doroshenko on 13.02.2015.
 */
public class GraphN extends AbstractGraph {
    private int kn = 1;
    public GraphN() {
        vertexList = new Vertex[MAX_VERTS];
        // adjacency matrix
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;

        for(int y = 0; y < MAX_VERTS; y++) {     // set adjacency
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

    public void chessPuzzle() throws IOException {
        StackO<Knight> theStack = new StackO<>(25);
        StackO<Knight> fails = new StackO<>(25);
        theStack.push(new Knight(2,2, kn));                 // push it
        adjMat[2][2] = kn++;
        int retSteps = 0;

        while( theStack.size() < 25 ) {    // until stack empty,

            // get an unvisited vertex adjacent to stack top
            Knight knight = makeMove(theStack.peek(), fails);

            if(knight == null) {                    // if no such vertex,
                knight = theStack.pop();
                retSteps++;
                adjMat[knight.x][knight.y] = 0;
                fails.push(knight);
                kn--;
            } else {                              // if it exists,
                theStack.push(knight);                 // push it
                adjMat[knight.x][knight.y] = kn++;
                for (int i = retSteps; i > 0; i--) {
                    theStack.pop();
                }

            }

            System.out.print("Press any key to continue... ");
            AppUtils.getChar();
            displayAdjacencyMatrix();
        }  // end while
    }

    public Knight makeMove(Knight base, StackO<Knight> fail) { // position coordinates
        int[] ktmov1 = { -2, -1, 1, 2, 2, 1, -1, -2 };
        int[] ktmov2 = { 1, 2, 2, 1, -1, -2, -2, -1 };


        int i = base.x;
        int j = base.y;
        int b, e;

        for (int a = 0 ; a <= 7 ; a++ ) {

            b = i + ktmov1[a];
            e = j + ktmov2[a];

            if ( b < 0 || b > 4 || e < 0 || e > 4 )
                continue;

            if ( adjMat[b][e] > 0 )
                continue;

            if (fail != null) {
                boolean isFail = false;
                if (fail.size() > 0) {
                    for (Knight k : fail) {
                        if (k.x == b && k.y == e) {
                            isFail = true;
                        }
                    }

                    if (isFail == true) {
                        continue;
                    }
                }
            }


            i = b; j = e;
            return new Knight(i, j, ++base.id);
        }

        return null;
    }

    class Knight {
        int x;
        int y;
        int id;

        public Knight(int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }
    }
}


