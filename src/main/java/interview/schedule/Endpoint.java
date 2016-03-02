package interview.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sergei on 3/2/16.
 */
public class Endpoint {
    public Date date;
    public String postfix;
    public Show show;

    public Endpoint(Date date, String postfix) {
        this.date = date;
        this.postfix = postfix;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return "{" + sdf.format(date) + postfix + '}';
    }
}
