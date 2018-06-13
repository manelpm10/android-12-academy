package es.pue.android.academy.model;

public class Student {
    private String name;
    private int course;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getCourse() {
        return course;
    }

    public int getAge() {
        return age;
    }
}
