package chap05.DoublyLinked;

import java.util.Iterator;

/**
 * Created by Sergei Doroshenko on 22.01.2015.
 * In this class we have doubled methods:
 * - in outer class and
 * - in inner class DLLIterator
 * That methods are: insertAfter, insertBefore... you could replace in iterator
 * such methods like deleteAfter, deleteBefore.
 */
public class DoublyLinkedList<T> implements Iterable<T> {
    private Link first;
    private Link last;
    private int nItems;
    // -------------------------------------------------------------
    public DoublyLinkedList() {             // constructor
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

    public int size() { return nItems; };
    // -------------------------------------------------------------
    /**
     * insert at start of list, Time Complexity - O(1)
     */
    public void insertFirst(T element) {
        Link newLink = new Link(); // make new link
        newLink.element = element; // set link element

        if (isEmpty()) {
            last = newLink;
        } else {
            first.previous = newLink;
        }

        newLink.next = first;      // newLink --> old first
        first = newLink;           // first --> newLink
        nItems++;
    }

    /**
     * Insert at the end of list, Time Complexity = O(1)
     * @param element
     */
    public void insertLast(T element) {
        Link newLink = new Link(element, null, null); // make new link

        if (isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
            newLink.previous = last;
        }

        last = newLink;           // last --> newLink
        nItems++;
    }

    public void insertAfter(T elemAfter, T elem) {
        Link currentLink = findLink(elemAfter);

        if (currentLink != null) {
            Link currentNextLink = currentLink.next;
            Link newLink = new Link(elem, currentNextLink, currentLink);

            if (currentLink == last) {
                last = newLink;
            } else {
                Link currentPreviousLink = currentLink.previous;
                currentNextLink.previous = newLink;
            }

            currentLink.next = newLink;
            nItems++;
        }
    }
    // -------------------------------------------------------------

    /**
     * @param key - element
     * @return - element index in the list
     * Time Complexity = O(N)
     */
    public int find(T key) {         // find index of element with given key
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
    private Link findLink(T key) {            // find link with given key (assumes non-empty list)
        Link current = first;              // start at 'first'
        while(!current.element.equals(key)) {       // while no match,
            if(current.next == null) {       // if end of list,
                return null;                 // didn't find it
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
        } else {
            first.next.previous = null;
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

        if (first.next == null) {
            first = null;
        } else {
            last.previous.next = null;
        }

        last = last.previous;
        nItems--;
        return temp.element;
    }

    /** (assumes non-empty list)
     * Delete from list link with given key
     * @param key - element data
     * @return date of deleted elements
     * Time Complexity = O(N)
     */
    public T deleteKey(T key) {
        Link current = first;              // search for link

        while(!(current.element).equals(key)) {
            current = current.next;   // go to next link
            if (current == null) {
                return null;
            }
        }
        // found it
        if(current == first) {                    // found it; first item?
            first = current.next;                 // first --> old next
        } else {                                  // not first
            current.previous.next = current.next; // old previous --> old next
        }

        if(current == last) {                         // last item?
            last = current.previous;                  // old previous <-- last
        } else {                                      // not last
            current.next.previous = current.previous; // old previous <-- old next
        }
        nItems--;
        return current.element;                // return value
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

    public void displayForward() {
        System.out.println("List (first --> last): " + this.toString() + ", size: " + size());
    }

    public void displayBackward() {
        String string = "";
        Link curr = last;
        while (curr != first) {
            string += curr.element + " ";
            curr = curr.previous;
        }
        string += curr.element;
        System.out.println("List (last --> first): " + string + ", size: " + size());
    }

    /**
     * Inner class for list elements (has access to all outer (LinkList.class) class
     * instance members even private members.
     * ??? Maybe it makes sense to make it static (nested) class ???
     */
    private class Link {
        T element;
        Link next;
        Link previous;

        Link(){}

        Link(T element, Link next, Link previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
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
                current = current.next;
            }
            return current.element;
        }

        @Override
        public void remove() {
            if(current == first) {                    // found it; first item?
                first = current.next;                 // first --> old next
            } else {                                  // not first
                current.previous.next = current.next; // old previous --> old next
            }

            if(current == last) {                         // last item?
                last = current.previous;                  // old previous <-- last
                //current = null;                           // return iterator to start
            } else {                                      // not last
                current.next.previous = current.previous; // old previous <-- old next
            }
            nItems--;
        }
    }

    /**
     * This method is custom iterator
     * @return
     */
    public DLLIterator<T> dllIterator() {
        return new DLLIteratorImpl();
    }

    public interface DLLIterator<T> extends Iterator<T> {
        void reset();                    // start at 'first'
        boolean atEnd();                 // true if last link
        void insertAfter(T element);     // insert after
        void insertBefore(T element);    // insert before
        void change(T newValue);
    }

    private class DLLIteratorImpl extends DirectIterator implements DLLIterator<T> {

        @Override
        public void reset() {
            current = null;
            // or
            // current = first;
        }

        @Override
        public boolean atEnd() {
            return current.next == null;
        }

        @Override
        public void insertAfter(T element) {
            if (current == null) throw new IllegalStateException("Iterator hasn't been moved");

            Link currentNextLink = current.next;
            Link newLink = new Link(element, currentNextLink, current);

            if (current == last) {
                last = newLink;
            } else {
                Link currentPreviousLink = current.previous;
                currentNextLink.previous = newLink;
            }

            current.next = newLink;
            nItems++;
        }

        @Override
        public void insertBefore(T element) {
            if (current == null) throw new IllegalStateException("Iterator hasn't been moved.");

            Link currentPreviousLink = current.previous;
            Link newLink = new Link(element, current, currentPreviousLink);

            if (current == first) {
                first = newLink;
            } else {
                currentPreviousLink.next = newLink;
            }

            current.previous = newLink;
            nItems++;
        }

        @Override
        public void change(T element) {
            if (current == null) { // current is first element
                last.element = element;
            } else {
                current.previous.element = element;
            }
        }
    }
}
