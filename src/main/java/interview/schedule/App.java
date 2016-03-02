package interview.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sergei on 3/2/16.
 */
public class App {

    public static List<Show> removeOwerlaps(List<Show> shows) {
        List<Endpoint> endpoints = new ArrayList<>();
        for (Show show : shows) {
            endpoints.add(show.dates[0]);
            endpoints.add(show.dates[1]);
        }
        System.out.println(endpoints);
        Collections.sort(endpoints, (e1, e2) -> e1.date.compareTo(e2.date));
        System.out.println(endpoints);

        for (int i = 0; i < endpoints.size()-1; i++) {
            if (endpoints.get(i).postfix == endpoints.get(i+1).postfix) {
                System.out.println("Find overlap: " + endpoints.get(i) + " -> " + endpoints.get(i+1));
            }
        }


        return shows;
    }

    public static void main(String[] args) throws ParseException {
        List<Show> shows = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        shows.add(new Show(new Endpoint[]{new Endpoint(sdf.parse("15-03-2016 18:00"), "S"), new Endpoint(sdf.parse("15-03-2016 20:00"), "E")}, "Show 1"));
        shows.add(new Show(new Endpoint[]{new Endpoint(sdf.parse("15-03-2016 19:00"), "S"), new Endpoint(sdf.parse("15-03-2016 21:00"), "E")}, "Show 2"));
        shows.add(new Show(new Endpoint[]{new Endpoint(sdf.parse("15-03-2016 21:00"), "S"), new Endpoint(sdf.parse("15-03-2016 22:00"), "E")}, "Show 3"));
        System.out.println(shows);

        List<Show> filtered = removeOwerlaps(shows);
        System.out.println(filtered);
    }
}
