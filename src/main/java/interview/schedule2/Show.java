package interview.schedule2;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sergei_Admin on 03.03.2016.
 */
public class Show {
    private String name;
    private Date startTime;
    private Date endTime;
    private int overlaps;

    public Show(String name, Date startTime, Date endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getOverlaps() {
        return overlaps;
    }

    public void setOverlaps(int overlaps) {
        this.overlaps = overlaps;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("H:m");
        return name + "(" + sdf.format(startTime) + "-" + sdf.format(endTime) + ")" + overlaps;
    }
}
