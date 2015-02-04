package chap10.tree23;

import chap10.tree234.DataItem;
import chap10.tree234.TraverseHandler;

/**
 * Created by Sergei Doroshenko on 03.02.2015.
 */
public class Tree23 {
    private Node root = new Node();            // make root node
    // -------------------------------------------------------------
    public int find(long key) {
        Node curNode = root;
        int childNumber;

        while(true) {
            if(( childNumber = curNode.findItem(key) ) != -1) {
                return childNumber;               // found it
            } else if( curNode.isLeaf() ) {
                return -1;                        // can't find it
            } else {                                 // search deeper
                curNode = getNextChild(curNode, key);
            }
        }  // end while
    }
    // -------------------------------------------------------------
    // insert a DataItem
    public void insert(long dValue) {
        Node curNode = root;
        DataItem tempItem = new DataItem(dValue);

        while (!curNode.isLeaf()) {
            curNode = getNextChild(curNode, dValue); // get appropriate child
        }

        if (curNode.isFull()) {
            split(curNode, null, tempItem, 0); // split and insert
            //curNode = getNextChild(curNode, dValue); // again get appropriate child for insert
        } else {
            curNode.insertItem(tempItem);              // insert new DataItem
        }
    }  // end insert()
    // -------------------------------------------------------------
    public void split(Node thisNode, Node newChildNode, DataItem tempItem, int childSplitOrder) {    // split the node and insert new value

        // assumes node is full
        Node newNode = new Node();

        // item for insert in parent
        DataItem upItem = handleChanges(thisNode, newNode, tempItem);

        if(!thisNode.isLeaf()) {
            manageLinks(thisNode, newChildNode, childSplitOrder, newNode);
        }

        Node parent;
        if (thisNode == root) {
            parent = new Node();
            parent.connectChild(0, thisNode);
            //parent.connectChild(1, newNode);
            root = parent;
        } else {
            parent = thisNode.getParent();
        }

        if (parent != null) {
            if (parent.isFull()) {
                split(parent, newNode, upItem, thisNode.getOrder());
            } else {
                parent.insertItem(upItem);
                if (parent.getNumChilds() > 1) {
                    int or = thisNode.getOrder();
                    if (or == 0) {
                        Node temp = parent.disconnectChild(1);
                        parent.connectChild(1, newNode);
                        parent.connectChild(2, temp);
                    } else {
                        parent.connectChild(parent.getNumChilds(), newNode);
                    }
                } else {
                    parent.connectChild(parent.getNumChilds(), newNode);
                }

            }
        }

    }  // end split()

    private void manageLinks(Node thisNode, Node newChildNode, int childSplitOrder, Node newNode) {

            if (childSplitOrder == 0) {
                newNode.connectChild(0, thisNode.disconnectChild(1));
                newNode.connectChild(1, thisNode.disconnectChild(2));
                thisNode.connectChild(1, newChildNode);
            } else if (childSplitOrder == 1) {
                newNode.connectChild(0, newChildNode);
                newNode.connectChild(1, thisNode.disconnectChild(2));
            } else {
                newNode.connectChild(0, thisNode.disconnectChild(2));
                newNode.connectChild(1, newChildNode);
            }

    }

    private DataItem handleChanges(Node thisNode, Node newNode, DataItem tempItem) {

        DataItem itemA, itemB, upItem;
        itemB = thisNode.removeItem();        // remove items from
        itemA = thisNode.removeItem();        // this node

        long t = tempItem.dData;
        long a = itemA.dData;
        long b = itemB.dData;

        if (t < a) {
            upItem = itemA;
            thisNode.insertItem(tempItem);
            newNode.insertItem(itemB);
        } else if (t > b) {
            upItem = itemB;
            thisNode.insertItem(itemA);
            newNode.insertItem(tempItem);
        } else {
            upItem = tempItem;
            thisNode.insertItem(itemA);
            newNode.insertItem(itemB);
        }
        // here we have two nodes with item and one item to lift up
        return upItem;
    }

    // -------------------------------------------------------------
    // gets appropriate child of node during search for value
    public Node getNextChild(Node theNode, long theValue) {
        int j;
        // assumes node is not empty, not full, not a leaf
        int numItems = theNode.getNumItems();

        for(j = 0; j < numItems; j++) {          // for each item in node
            if( theValue < theNode.getItem(j).dData ) { // are we less?
                return theNode.getChild(j);           // return left child
            }

        }  // end for
        // we're greater, so return right child
        return theNode.getChild(j);
    }

    public long min() {
        Node curr = root;
        while (!curr.isLeaf()) {
            curr = curr.getChild(0);
        }

        return curr.getItem(0).dData;
    }

    public void traverse(TraverseHandler handler) {
        inOrder(handler, root);
    }

    private void inOrder(TraverseHandler handler, Node localRoot) {
        if (localRoot.isLeaf()) {
            for (int i = 0; i < localRoot.getNumItems(); i++) {
                //System.out.print(localRoot.getItem(i) + "/");
                handler.handle(localRoot.getItem(i));
            }
            //System.out.print(localRoot);

        } else {
            int i;
            for (i = 0; i < localRoot.getNumItems(); i++) {
                inOrder(handler, localRoot.getChild(i));
                //System.out.print(localRoot.getItem(i).dData);
                handler.handle(localRoot.getItem(i));
            }
            inOrder(handler, localRoot.getChild(i));
        }

        /*if(localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.iData + " ");
            inOrder(localRoot.rightChild);
        }*/
    }
    // -------------------------------------------------------------
    public void displayTree() {
        recDisplayTree(root, 0, 0);
    }
    // -------------------------------------------------------------
    private void recDisplayTree(Node thisNode, int level, int childNumber) {
        System.out.print("level=" + level + " child=" + childNumber + " ");
        thisNode.displayNode();               // display this node

        // call ourselves for each child of this node
        int numItems = thisNode.getNumItems();

        for(int j = 0; j < numItems+1; j++) {
            Node nextNode = thisNode.getChild(j);
            if(nextNode != null) {
                recDisplayTree(nextNode, level + 1, j);
            } else {
                return;
            }
        }
    }  // end recDisplayTree()
}
