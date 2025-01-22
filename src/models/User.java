package models;

public class User {
    private int id;
    private String name;
    private int age;
    private boolean gender;

    public User() {
    }

    public User(String name, int age, boolean gender) {
        setName(name);
        setAge(age);
        setGender(gender);
    }

    public User(int id, String name, int age, boolean gender) {
        this(name, age, gender);
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
