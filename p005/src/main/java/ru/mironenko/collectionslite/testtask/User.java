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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getName().equals(user.getName());

    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
