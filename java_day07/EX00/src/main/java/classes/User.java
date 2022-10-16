package classes;

import java.time.Month;

public class User {
    private final String name;
    private final String lastName;
    private final int age;

    public User(){
        this.name = "name none";
        this.lastName = "lastName none";
        this.age = 0;
    }

    public User(String name, String lastName, int age){
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public int yearBirthday(){
        return 2022 - age;
    }

    public String addMonth(int mont){
        return Month.of(mont) + "/" + 2022;
    }

    @Override
    public String toString() {
        return "User[" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ']';
    }

}
