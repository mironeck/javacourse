package ru.mironenko.tasktwohttpprotocol;

//Создать модель User c полями name, login, email, createDate.
//Необходимо создать сервлет UsersServlet и определить там методы do Get Post Put Delete.
//Каждый метод сервлета должен выполнять только одно действие -
//создать пользователя, редактировать, получить. Удалить.
//Для хранения данных использовать базу данных Postgresql.
//То есть при старте сервлета вам нужно создать коннект к базе данных и записывать туда данные.

import java.sql.Timestamp;
import java.util.Date;

/**
 * User class.
 */
public class User {

    /**
     * User's name.
     */
    private String name;

    /**
     * User's login.
     */
    private String login;

    /**
     * User's email.
     */
    private String email;

    /**
     * Date of creation.
     */
    private Date createDate;

    /**
     * Constructor.
     * @param name user's name
     * @param login user's login
     * @param email user's email.
     */
    public User(String name, String login, String email) {
        this.name = name;
        this. login = login;
        this.email = email;
        this.createDate = new Date();
    }

    /**
     * Constructor.
     * @param name user's name.
     * @param login user's login.
     * @param email user's email.
     * @param createDate date of creation.
     */
    public User(String name, String login, String email, Timestamp createDate) {
        this.name = name;
        this. login = login;
        this.email = email;
        this.createDate = createDate;
    }

    /**
     * Getter of name.
     * @return user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter of login.
     * @return user's login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Getter of email.
     * @return user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter of create date.
     * @return user's create date.
     */
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return "name :" + this.name + " login: " + this.login + " email: " + this.email;
    }
}
