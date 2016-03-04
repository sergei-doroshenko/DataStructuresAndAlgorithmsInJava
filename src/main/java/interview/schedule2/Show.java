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

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("H:m");
        return name + "(" + sdf.format(startTime) + "-" + sdf.format(endTime) + ")";
    }
}
