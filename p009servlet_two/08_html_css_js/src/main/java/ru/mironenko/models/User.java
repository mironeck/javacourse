package ru.mironenko.models;

import java.util.Date;

public class User {


    private final String name;
    private final String login;
    private String password;
    private String email;
    private final Date createDate;
    private String country;
    private String city;


    public User(String name, String login, String password, String email, Date createDate) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("User : name - %s, login - %s, password - %s, email - %s, date of creation - %s," +
                        "city - %s, country - %s",
                name, login, password, email, createDate, city, country);
    }
}
