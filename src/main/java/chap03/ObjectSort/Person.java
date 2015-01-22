package chap03.ObjectSort;

/**
 * Created by Sergei_Doroshenko on 1/12/2015.
 */
public class Person {
    private String lastName;
    private String firstName;
    private int age;

    public Person(String lastName, String firstName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (!firstName.equals(person.firstName)) return false;
        if (!lastName.equals(person.lastName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        /*
        return "{" +
                "lastName=" + lastName +
                ", firstName=" + firstName +
                ", age=" + age +
                '}';
        */
        return String.format("Last name: %-10s First name: %-10s Age: %2d", lastName, firstName, age);
    }
}
