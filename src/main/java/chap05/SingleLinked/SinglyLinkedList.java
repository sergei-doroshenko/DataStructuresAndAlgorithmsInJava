package chap05.SingleLinked;

import java.util.Iterator;

/**
 * Created by Sergei Doroshenko on 21.01.2015.
 * Implementation of Singly Linked list
 * Space and time Big-O complexities:
 * Average:
 * Indexing	Search	Insertion	Deletion
 *   O(n)	 O(n)	  O(1)	      O(1)
 * Worst:
 * Indexing	Search	Insertion	Deletion
 *   O(n)	 O(n)	   O(1)	      O(1)
 */
public class SinglyLinkedList<T> implements Iterable<T> {
    private Link first;
    private Link last;
    private int nItems;

    // -------------------------------------------------------------
    public SinglyLinkedList() {             // constructor
        this.first = null;               // no links on list yet
        this.last = null;
        this.nItems = 0;

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
     * insert at start of list, Time Complexity - O(1)
     */
    public void insertFirst(T element) {
        Link newLink = new Link(); // make new link
        /* alternative approach create link and sets element and next */
        // Link newLink = new Link(element, fist);

        if (isEmpty()) {
            last = newLink;
        }
        newLink.element = element; // set link element
        newLink.next = first;      // newLink --> old first

        first = newLink;           // first --> newLink
        nItems++;
    }

    /**
     * Insert at the end of list, Time Complexity = O(1)
     * @param element
     */
    public void insertLast(T element) {
        Link newLink = new Link(element, null); // make new link

        if (isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
        }

        last = newLink;           // last --> newLink
        nItems++;
    }

    public void insertAfter(T elemAfter, T elem) {
        Link previous = findLink(elemAfter);
        if (previous != null) {
            /*
            Link previous = findLink(element);
            Link next = previous.next;
            Link current = new Link(element, next);
            previous.next = current;
            */
            /* More short variant */
            previous.next = new Link(elem, previous.next);
            if (previous == last) {
                last = previous.next;
            }
            nItems++;
        }
    }
    // -------------------------------------------------------------

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
    public T delete(T key) {
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
        System.out.println("List (first-->last): " + this.toString());
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
        Link current = null;
        Link previous = null;

        @Override
        public boolean hasNext() {
            if (current == null) return first != null;
            return current.next != null;
        }

        @Override
        public T next() {
            if (current == null) {
                current = first;
            } else {
                previous = current;
                current = current.next;
            }
            return current.element;
        }

        @Override
        public void remove() {
            if(current == first) {                    // found it; first item?
                first = current.next;                 // first --> old next
            } else {                                  // not first
                previous.next = current.next;         // old previous --> old next
            }

            if(current == last) {                     // last item?
                last = previous;                      // old previous <-- last
            }
            nItems--;
        }
    }

    public SLLIterator<T> sllIterator() { return new SLLIteratorImpl(); }

    public interface SLLIterator<T> extends Iterator<T> {
        void reset();                    // start at 'first'
        boolean atEnd();                 // true if last link
        void insertAfter(T element);     // insert after
        void insertBefore(T element);    // insert before
    }

    private class SLLIteratorImpl extends DirectIterator implements SLLIterator<T> {

        @Override
        public void reset() {
            current = null;
            previous = null;
        }

        @Override
        public boolean atEnd() {
            return current.next == null;
        }

        @Override
        public void insertAfter(T element) {
            if (current == null) throw new IllegalStateException("Iterator hasn't been moved.");

            Link newLink = new Link(element, current.next);
            current.next = newLink;

            if (current == last) {
                last = newLink;
            }
            current = newLink;      // move iterator pointer to inserted item
            nItems++;
        }

        @Override
        public void insertBefore(T element) {
            if (current == null) throw new IllegalStateException("Iterator hasn't been moved.");

            Link newLink = new Link(element, current);

            if (current == first) {
                first = newLink;
            } {
                previous.next = newLink;
            }
            nItems++;
        }
    }

}

