package chap05.SortedLinkList;

import java.util.Iterator;

/**
 * Created by Sergei Doroshenko on 21.01.2015.
 * Implementation of Sorted Singly Linked list
 * Space and time Big-O complexities:
 * Average:
 * Indexing	Search	Insertion	Deletion
 *   O(n)	 O(n)	  O(1)	      O(1)
 * Worst:
 * Indexing	Search	Insertion	Deletion
 *   O(n)	 O(n)	   O(1)	      O(1)
 */
public class SortedSinglyLinkedList<T extends Comparable> implements Iterable<T> {
    private Link first;
    private Link last;
    private int nItems;

    // -------------------------------------------------------------
    public SortedSinglyLinkedList() {             // constructor
        first = null;               // no links on list yet
        last = null;
        nItems = 0;
    }

    public T getFirst() {
        return first == null ? null : first.element;
    }

    public T getLast() {
        return last == null ? null : last.element;
    }

    // -------------------------------------------------------------
    public boolean isEmpty() {       // true if list is empty
        return (first == null);
    }
    // -------------------------------------------------------------
    /**
     * insert item in the list, Time Complexity - O(N)
     * average is O(N/2)
     */
    public void insert(T key) {
        Link newLink = new Link(key, null); // make new link
        Link previous = null;               // start at first
        Link current = first;
        // until end of list,
        while(current != null && key.compareTo(current.element) > 0) { // or key > current,
            previous = current;
            current = current.next;       // go to next item
        }
        /* Change last */
        if (isEmpty()) {                // list is empty
            last = newLink;
        } else if (current == null) {  // insert at the end of list
            last = newLink;
        }

        if (previous == null) {             // at beginning of list
            first = newLink;               // first --> newLink
        } else {                          // not at beginning
            previous.next = newLink;      // old prev --> newLink
        }

        newLink.next = current;           // newLink --> old currnt

        nItems++;
    }

    /**
     * @param key - element
     * @return - element index in the list
     * Time Complexity = O(N)
     */
    public int find(T key) {         // findL index of element with given key
        int index = -1;              // (assumes non-empty list)

        for (T elem : this) {
            index++;
            if (elem.equals(key)) {
                return index;
            }
        }

        return -1;                     // not found it
    }

    /**
     * @param key - searching list element
     * @return Link object
     * Time Complexity = O(N)
     */
    private Link findLink(T key) {            // findL link with given key (assumes non-empty list)
        Link current = first;              // start at 'first'
        while(!current.element.equals(key)) {       // while no match,
            if(current.next == null) {       // if end of list,
                return null;                 // didn't findL it
            } else {                            // not end of list,
                current = current.next;      // go to next link
            }
        }
        return current;                    // found it
    }

    /** (assumes non-empty list)
     * Delete first element from the list
     * @return deleted element
     * Time Complexity = O(1)
     */
    public T deleteFirst() {
        // (assumes list not empty)
        Link temp = first;        // save reference to link
        if (first.next == null) { // if only one element
            last = null;
        }
        first = first.next;      // delete it: first-->old next
        nItems--;
        return temp.element;     // return deleted link element
    }

    /** (assumes non-empty list)
     * Delete last element from the list
     * @return deleted element
     * Time Complexity = O(N) -- BAD
     */
    public T deleteLast() {    // (assumes list not empty)
        Link temp = last;
        Link previous = first;
        Link current = first;

        while (current != last) {
            previous = current;
            current = current.next;
        }

        if (first == last) {
            first = null;
        }
        previous.next = null;
        last = previous;
        nItems--;
        return temp.element;
    }

    /** (assumes non-empty list)
     * Delete from list link with given key
     * @param key - element data
     * @return date of deleted elements
     * Time Complexity = O(N)
     */
    public T delete(int key) {
        Link current = first;              // search for link
        Link previous = first;

        while(!(current.element).equals(key)) {
            if (current.next == null) {      // reach last link, but element not equal key
                return null;                 // didn't findL it
            } else {
                previous = current;          // go to next link
                current = current.next;
            }
        }
                                           // found it
        if(current == first) {              // if first link,
            first = first.next;             //    change first
        } else {                               // otherwise,
            previous.next = current.next;   //    bypass it
        }
        nItems--;
        return current.element;
    }

    @Override
    public String toString() {
        String string = "";
        Link current = first;                        // start at beginning of list
        while(current != null) {                     // until end of list,
            string += current.element + " ";         // append string representation of data
            current = current.next;                  // move to next link
        }

        return string;
    }

    public void displayList() {
        System.out.println("Sorted List (first-->last): " + this.toString());
    }

    public int size() {
        return nItems;
    }

    /**
     * Inner class for list elements (has access to all outer (LinkList.class) class
     * instance members even private members.
     * ??? Maybe it makes sense to make it static (nested) class ???
     */
    private class Link {
        T element;
        Link next;

        Link(){}

        Link(T element, Link next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DirectIterator();
    }

    /**
     * Private iterator class
     */
    private class DirectIterator implements Iterator<T> {
        Link current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T retVal = current.element;
            current = current.next;
            return retVal;
        }

        @Override
        public void remove() {

        }
    }

}

