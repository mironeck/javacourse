package ru.mironenka.models;

import java.sql.Timestamp;

public class User {

    private final String name;
    private final String login;
    private final String email;
    private final String password;
    private Timestamp createDate;
    private int roleId;
    private Role role;

    public User(String name, String login, String email, String password, Timestamp createDate, int roleId) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.createDate = createDate;
        this.roleId = roleId;
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

    public String getPassword() {
        return password;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("User : name - %s, login - %s, email - %s, password - %s, create - %s ",
                name, login, email, password, createDate);
    }
}
