package chap10.tree234;

/**
 * Created by Sergei Doroshenko on 03.02.2015.
 */
public class DataItem {
    public long dData;          // one data item

    public DataItem(long dd) { dData = dd; }

    @Override
    public String toString() {
        return "/"+dData;
    }

    public void displayItem() {  // display item, format "/27"
        System.out.print(this);
    }
}
