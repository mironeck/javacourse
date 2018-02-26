package ru.mironenko.models;

import java.util.Date;

public class User {

    private final String name;
    private final String login;
    private String password;
    private String email;
    private int role_id;
    private final Date createDate;

    public User(String name, String login, String password, String email, int role_id, Date createDate) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role_id = role_id;
        this.createDate = createDate;
    }

    public String getPassword() {
        return password;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
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
