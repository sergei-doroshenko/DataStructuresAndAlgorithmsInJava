package interview.schedule;

import java.text.SimpleDateFormat;

/**
 * Created by sergei on 3/2/16.
 */
public class Show {
    public Endpoint[] dates;
    public String name;

    public Show(Endpoint[] dates, String name) {
        this.dates = dates;
        this.name = name;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return "{dates:" + sdf.format(dates[0].date) + " : " + sdf.format(dates[1].date) + ", name:" + name + '}';
    }
}
