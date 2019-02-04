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
        int height;
    }

    public static void printTree(Node root, String offset) {
        System.out.println(offset +root.show.getName());
        offset += "   ";

        if (root.links.size() > 0) {
            System.out.println(offset +"|");
            for (Node n : root.links) {
                printTree(n, offset);
            }
        }
    }

    public static void makeTree(List<Node> results, List<Show> shows, int i, Node previous) {

        if (i == shows.size()) {
            results.add(previous);
            return;
        }

        Node current = new Node();
        current.show = shows.get(i);

        if ( previous.show.getEndTime().before(current.show.getStartTime()) ){
            previous.links.add(current);
            current.parent = previous;
            current.height = current.parent.height+1;
        } else {
            previous.parent.links.add(current);
            current.parent = previous.parent;
            current.height = current.parent.height+1;
            makeTree(results, shows, i+1, previous);
        }

        makeTree(results, shows, i+1, current);
    }

    public static List<Show> getBest(List<Node> results) {
        Node best = Collections.max(results, Comparator.comparingInt(n -> n.height));
        List<Show> result = new ArrayList<>();
        while (best.parent != null) {
            result.add(best.show);
            best = best.parent;
        }

        return result;
    }

    public static List<Show> findOptimalSchedule(Collection<Show> shows) {
        List<Show> showList = new ArrayList<>(shows);
        Collections.sort(showList, (o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime()));
        System.out.println(showList);

        Node root = new Node();
        root.show = new Show("Root", new Date(0), new Date(0));

        List<Node> results = new ArrayList<>();
        makeTree(results, showList, 0, root);
//        printTree(root, "                                  ");

        List<Show> best = getBest(results);
        Collections.sort(best, (o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime()) );

//        System.out.println(best);
        return best;
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
