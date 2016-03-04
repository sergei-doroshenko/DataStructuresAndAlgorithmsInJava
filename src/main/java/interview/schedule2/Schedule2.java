package interview.schedule2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Sergei_Admin on 03.03.2016.
 */
public class Schedule2 {
    static class Node {
        Node parent;
        Show show;
        List<Node> links = new ArrayList<>();
        boolean visited;
    }

    public static void print(Node root) {
        for (Node n : root.links) {
            System.out.print(n.show.getName() + " -> ");
            print(n);
        }
        System.out.print("|\n");
    }

    public static Node makeTree(List<Show> shows, int i, Node previous) {

        if (i == shows.size()) {
            return previous;
        }

        Node node = new Node();
        node.show = shows.get(i);
        node.parent = previous;

        if (previous.show.getEndTime().getTime() < shows.get(i).getStartTime().getTime()) {
            previous.links.add(node);
        } else {
            previous.parent.links.add(node);
            makeTree(shows, i+1, previous);
        }

        makeTree(shows, i+1, node);

        return previous;
    }

    public static List<Show> findOptimalSchedule(Collection<Show> shows) {
        List<Show> showList = new ArrayList<>(shows);
        Collections.sort(showList, (o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime()));
        System.out.println(showList);

        Node root = new Node();
        root.show = new Show("Root", new Date(0), new Date(0));

        Node result = makeTree(showList, 0, root);
        print(result);
        return new ArrayList(shows);
    }

    public static void main(String[] args) throws Exception {

        List<Show> shows = testCases02();

        List<Show> list = Schedule2.findOptimalSchedule(shows);
        for (Show show : list) {
            System.out.println(show);
        }
    }

    public static List<Show> testCases01() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("y-M-d H:m");
        ArrayList<Show> shows = new ArrayList<>();
        shows.add(new Show("Show1", sdf.parse("2013-08-06 18:00"), sdf.parse("2013-08-06 20:00")));
        shows.add(new Show("Show3", sdf.parse("2013-08-06 21:00"), sdf.parse("2013-08-06 23:00")));
        shows.add(new Show("Show2", sdf.parse("2013-08-06 19:00"), sdf.parse("2013-08-06 22:00")));
        return shows;
    }

    public static List<Show> testCases02() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("y-M-d H:m");
        ArrayList<Show> shows = new ArrayList<>();
        shows.add(new Show("Show1", sdf.parse("2013-08-06 13:00"), sdf.parse("2013-08-06 15:00")));
        shows.add(new Show("Show2", sdf.parse("2013-08-06 16:00"), sdf.parse("2013-08-06 18:00")));
        shows.add(new Show("Show3", sdf.parse("2013-08-06 14:00"), sdf.parse("2013-08-06 16:00")));
        shows.add(new Show("Show4", sdf.parse("2013-08-06 19:00"), sdf.parse("2013-08-06 21:00")));
        shows.add(new Show("Show5", sdf.parse("2013-08-06 18:00"), sdf.parse("2013-08-06 20:00")));
        shows.add(new Show("Show6", sdf.parse("2013-08-06 22:00"), sdf.parse("2013-08-06 23:00")));
        return shows;
    }
}
