package chap12.heap;

/**
 * Created by Sergei Doroshenko on 11.02.2015.
 */
public class Person {
    private final int id;
    private final int age;

    public Person() {
        this.id = 0;
        this.age = 0;
    }

    public Person(int id, int age) {
        this.id = id;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    /*public void setId(int id) {
        this.id = id;
    }*/

    public int getAge() {
        return age;
    }

    /*public void setAge(int age) {
        this.age = age;
    }*/

    @Override
    public String toString() {
        return id + "/" + age;
    }
}
