package chap12.heap;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Sergei on 11.02.2015.
 */
public class Test01 {
    public static void main (String[] args) {
        Map<Person, String> map = new TreeMap<>();
        map.put(new Person(1, 30), "one");
        map.put(new Person(2, 36), "two");
        System.out.println(map);
    }
}
