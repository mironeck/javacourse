package ru.mironenko.models;

import java.sql.Timestamp;

public class User {

    private final String name;
    private final String login;
    private final String email;
    private Timestamp createDate;
    private String password;
    private Role role;
    private int roleId;



    public User(String name, String login, String email, String password, Timestamp createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return this.name;
    }

    public String getLogin() {
        return this.login;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return String.format("User : name - %s, login - %s, email - %s, password - %s, create - %s",
                name, login, email, password, createDate);
    }
}
