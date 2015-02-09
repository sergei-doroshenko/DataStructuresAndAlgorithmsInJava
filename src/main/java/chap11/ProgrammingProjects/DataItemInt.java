package chap11.ProgrammingProjects;

/**
 * Created by Sergei Doroshenko on 09.02.2015.
 */
public class DataItemInt implements DataItem {
    private int iData;               // data item (key)

    public DataItemInt(int iData) {
        this.iData = iData;
    }

    @Override
    public int getKey() {
        return iData;
    }
}
