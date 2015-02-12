package chap12.heap;

import java.util.*;

/**
 * Created by Sergei on 11.02.2015.
 */
public class Test01 {
    public static void main (String[] args) {
        Map<Integer, String> map = new TreeMap<>();
        map.put(1, "one");
        map.put(2, "two");
        System.out.println(map);
        map.put(2, "next two");
        System.out.println(map);
        System.out.println("----------------------------------------------");
        Set<Key> set = new HashSet<>();
        set.add(new Key(1, "one"));
        set.add(new Key(1, "two"));
        set.add(new Key(1, "three"));

        System.out.println(set);
        System.out.println("Contains Key(1, one): " + set.contains(new Key(1, "one")));


        for (Iterator<Key> iter = set.iterator(); iter.hasNext();){
            Key k = iter.next();
            k.id += 200;
            System.out.println(k);
        }
        System.out.println("----------------------------------------------");
        System.out.println(set);
        System.out.println("Contains Key(1, one): " + set.contains(new Key(1, "one")));
    }
}

class Key {
    int id;
    String name;

    public Key(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key = (Key) o;

        if (id != key.id) return false;
        if (name != null ? !name.equals(key.name) : key.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "Key{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
