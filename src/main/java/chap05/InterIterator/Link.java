package chap05.InterIterator;

/**
 * Created by user on 22.01.2015.
 */
public class Link {
    public long dData;             // data item
    public Link next;              // next link in list
    // -------------------------------------------------------------
    public Link(long dd)           // constructor
    { dData = dd; }
    // -------------------------------------------------------------
    public void displayLink()      // display ourself
    { System.out.print(dData + " "); }
}
