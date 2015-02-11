package chap12.heap;

/**
 * Created by Sergei Doroshenko on 11.02.2015.
 */
public class Person {
    private int id;
    private int age;

    public Person() {
    }

    public Person(int id, int age) {
        this.id = id;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return id + "/" + age;
    }
}
