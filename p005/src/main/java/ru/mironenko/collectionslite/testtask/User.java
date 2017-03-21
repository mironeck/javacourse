package ru.mironenko.collectionslite.testtask;

/**
 * Created by nikita on 20.03.2017.
 */


public class User implements Comparable<User>{

    private String name;
    private String passport;

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    @Override
    public int compareTo(User o) {
        return name.compareTo(o.getName());
    }
}
