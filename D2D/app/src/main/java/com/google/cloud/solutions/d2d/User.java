package com.google.cloud.solutions.d2d;

public class User {

    public  String name;
    public String bday;
    public int age;
    public double weight;


    //required default constructor
    public User() {
    }

    public User(String name, String bday, int age, double weight) {
        this.age = age;
        this.name = name;
        this.bday = bday;
        this.weight = weight;
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
