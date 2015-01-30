package chap05.ProgrammingProjects;

import java.util.Iterator;

/**
 * Created by Sergei Doroshenko on 22.01.2015.
 */
public class CycleDoublyLinkedList<T> implements Iterable<T> {
    private Link current;
    private Link start;
    private int nItems;
    // -------------------------------------------------------------
    public CycleDoublyLinkedList() {
    }

    public T getCurrent() {
        return current != null ? current.element : null;
    }

    public boolean isEmpty() {       // true if list is empty
        return (current == null);
    }

    public int size() { return nItems; };

    public T stepForward() {
        return (current = current.next).element;
    }

    public T stepBackward() { return (current = current.previous).element; }

    /**
     * insert item next to the current, Time Complexity - O(1)
     */
    public void insert(T element) {
        Link newLink = new Link(); // make new link
        newLink.element = element;

        if (current == null) {
            newLink.next = newLink;
            newLink.previous = newLink;
        } else {
            Link next = current.next;   // save link to next item
            newLink.next = next;        // new link next --> old next

            newLink.previous = current; // new link previous --> current

            current.next = newLink;     // current next --> new link
            next.previous = newLink;    // old next previous --> new link
        }

        current = newLink;
        nItems++;
    }

    /** (assumes non-empty list)
     * @param key - searching list element
     * @return Link object
     * Time Complexity = O(N)
     */
    private Link findLink(T key) {              // findL link with given key
        Link temp = current;                    // save link to current at search start

        while(!temp.element.equals(key)) {      // while no match,
            if(temp.next == current) {          // if end of list,
                return null;                    // didn't findL it

            } else {                            // not end of list,
                temp = temp.next;                  // go to next link
            }
        }
        return temp;                    // found it
    }

    private Link deleteLink(Link link) {
        if (link == null) return null;
        if (link.next == link && link.previous == link) {
            current = null;
        } else {
            link.previous.next = link.next;
            link.next.previous = link.previous;
        }
        nItems--;
        return link;
    }

    public T delete() {
        Link temp = current;
        current = current.next;
        Link del = deleteLink(temp);
        return del != null ? del.element : null;
    }

    public T deleteKey(T key) {
        Link link = findLink(key);
        if (link == current) {
            current = current.next;
        }
        Link del = deleteLink(link);
        return del != null ? del.element : null;
    }

    @Override
    public String toString() {
        String string = "";
        for (T elem : this) {
            string += elem + " ";
        }
        return string;
    }

    public void displayForward() {

        System.out.println("List -->: " + this + ", size: " + size());
    }

    public void displayBackward() {
        String string = "";

        System.out.println("List <--: " + string + ", size: " + size());
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
        Link iterStart = current;
        int iter = nItems;

        @Override
        public boolean hasNext() {
            return iterStart != null && iter > 0;
        }

        @Override
        public T next() {
            Link temp = iterStart;
            iterStart = iterStart.next;
            iter--;
            return temp.element;
        }

        @Override
        public void remove() {
            Link temp = iterStart;        // save reference to link
            iterStart.previous.next = iterStart.next;
            iterStart.next.previous = iterStart.previous;
            iterStart = start.next;
            iter--;
            nItems--;
        }
    }
}
