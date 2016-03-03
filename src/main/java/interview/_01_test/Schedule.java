package interview._01_test;

import java.text.SimpleDateFormat;
import java.util.*;

public class Schedule {
    public static List<Show> findOptimalSchedule(Collection<Show> shows)
    {
        List<Show> showList = new ArrayList(shows);
        String[] arr0 = new String[showList.size()*2];

        Map<String, Show> m1 = new HashMap<>();
        Map<String, Show> m2 = new HashMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("H:m");
        for (int i =0, j = 0; i < showList.size(); i++) {
            String s1 = sdf.format(showList.get(i).getStartTime()) + "S";
            arr0[j++] = s1;
            String s2 = sdf.format(showList.get(i).getEndTime()) + "E";
            arr0[j++] = s2;
            m1.put(s1, showList.get(i));
            m2.put(s2, showList.get(i));
        }

        System.out.println(Arrays.toString(arr0));

        Arrays.sort(arr0, new Comparator<String>(){

            @Override
            public int compare(String o1, String o2) {
                o1 = o1.substring(0, o1.length()-1);
                o2 = o2.substring(0, o2.length()-1);
                return o1.compareTo(o2);
            }
        });

        System.out.println(Arrays.toString(arr0));
        Collections.sort(showList, new Comparator<Show>(){
            @Override
            public int compare(Show o1, Show o2) {
                return o1.getStartTime().compareTo(o2.getStartTime());
            }
        });

        List<String> inter = new ArrayList<>();

        for (int i = 0; i < arr0.length-1; i++) {
            String f = arr0[i];
            inter.add(f);

            String n = arr0[i+1];

            if ( (f.endsWith("S") && n.endsWith("S")) || (f.endsWith("E") && n.endsWith("E")) ) {
                inter.add(n);
            } else {
                if (inter.size() > 1) {
                    // mark intervals
                    for (String s : inter) {
                        if (m1.get(s) != null) {
                            m1.get(s).setOverlaps(m1.get(s).getOverlaps() + 1);
                        }

                        if (m2.get(s) != null) {
                            m2.get(s).setOverlaps(m2.get(s).getOverlaps() + 1);
                        }
                    }
                }
                inter.clear();
            }
        }

        System.out.println(m1);
        System.out.println(m2);

        return showList;
//        return new ArrayList(shows);
    }

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("y-M-d H:m");

        ArrayList<Show> shows = new ArrayList<Show>();
        shows.add(new Show("Show1", sdf.parse("2013-08-06 18:00"), sdf.parse("2013-08-06 20:00")));
        shows.add(new Show("Show3", sdf.parse("2013-08-06 21:00"), sdf.parse("2013-08-06 23:00")));
        shows.add(new Show("Show2", sdf.parse("2013-08-06 19:00"), sdf.parse("2013-08-06 22:00")));


        List<Show> list = Schedule.findOptimalSchedule(shows);
        for (Show show : list) {
            System.out.println(show);
        }
    }
}

class Show {
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