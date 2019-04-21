package com.google.cloud.solutions.d2d;

public class User {

    public  String name;
    public String bday;
    public int age;


    //required default constructor
    public User() {
    }

    public User(int age, String name, String bday) {
        this.age = age;
        this.name = name;
        this.bday = bday;
    }

    public double getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void getBday(String name) {
        this.name = name;
    }
}
