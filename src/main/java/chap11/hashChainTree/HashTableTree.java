package chap11.hashChainTree;

import chap05.SortedLinkList.SortedSinglyLinkedList;
import chap08.tree.Tree;
import chap08.tree.TreeG;

/**
 * Created by Sergei Doroshenko on 09.02.2015.
 */
public class HashTableTree<T extends Comparable<T>> {
    private TreeG<T>[] hashArray;   // array of lists
    private int arraySize;
    // -------------------------------------------------------------
    public HashTableTree(int size) {          // constructor
        arraySize = size;
        hashArray = new TreeG[arraySize];      // create array
        for(int j = 0; j<arraySize; j++) {    // fill array
            hashArray[j] = new TreeG<>();        // with lists
        }
    }
    // -------------------------------------------------------------
    public void displayTable() {
        for(int j = 0; j < arraySize; j++) {  // for each cell,
            System.out.print(j + ". ");       // display cell number
            hashArray[j].traverse(2);       // display list
        }
    }
    // -------------------------------------------------------------
    public int hashFunc(T key) {         // hash function
        return key.hashCode() % arraySize;
    }
    // -------------------------------------------------------------
    public void insert(T key) { // insert a link

        int hashVal = hashFunc(key);   // hash the key
        hashArray[hashVal].insert(key); // insert at hashVal
    }  // end insert()
    // -------------------------------------------------------------
    public void delete(T key) {      // delete a link
        int hashVal = hashFunc(key);   // hash the key
        hashArray[hashVal].delete(key); // delete link
    }  // end delete()
    // -------------------------------------------------------------
    public T find(T key) {                     // find link

        int hashVal = hashFunc(key);           // hash the key
        T link = hashArray[hashVal].find(key); // get link

        return link;                           // return link
    }
}
