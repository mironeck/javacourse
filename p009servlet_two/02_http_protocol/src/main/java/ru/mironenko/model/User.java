package ru.mironenko.model;

import java.util.Date;

public class User {

    private final String name;
    private final String login;
    private final String email;
    private final Date createDate;

    public User(String name, String login, String email, Date createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreateDate() {
        return createDate;
    }


    @Override
    public String toString() {
        return String.format("User : name - %s, login - %s, email - %s, date of creation - %s",
                name, login, email, createDate);
    }
}
