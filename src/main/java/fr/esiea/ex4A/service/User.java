package fr.esiea.ex4A.service;


public class User {
    public final String name;
    public final int age;
    public final int count;
    public final String country_id;

    public User(String name, int age, int count, String country_id) {
        this.name = name;
        this.age = age;
        this.count = count;
        this.country_id = country_id;
    }

}