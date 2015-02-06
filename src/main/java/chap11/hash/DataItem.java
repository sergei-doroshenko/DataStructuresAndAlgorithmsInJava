package chap11.hash;

/**
 * Created by Sergei Doroshenko on 06.02.2015.
 */
public class DataItem {  // (could have more data)
    private int iData;               // data item (key)
    //--------------------------------------------------------------
    public DataItem(int ii)          // constructor
    { iData = ii; }
    //--------------------------------------------------------------
    public int getKey()  { return iData; }
    //--------------------------------------------------------------
}
