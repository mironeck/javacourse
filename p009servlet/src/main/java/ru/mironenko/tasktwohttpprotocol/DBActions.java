package ru.mironenko.tasktwohttpprotocol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Date;
import java.util.Properties;

/**
 * Creates connections to database, performs some actions with data: create user, edit user, get and delete.
 */
public class DBActions {

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(DBActions.class);

    /**
     * instance of Properties;
     */
    private final Properties prop = new Properties();

    /**
     * Reference to Connection of database
     */
    private Connection conn;

    /**
     * Constructor. Starts init() method.
     */
    public DBActions() {
        this.init();
    }

    /**
     * Connects to database
     */
    public void init() {

        InputStream io = getClass().getClassLoader().getResourceAsStream("resources.properties");
        try {
            prop.load(io);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String url = prop.getProperty("db.host");
//        String username = prop.getProperty("db.login");
//        String password = prop.getProperty("db.password");
        try {
//            this.conn = DriverManager.getConnection(url, username, password);
            this.conn = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        createTablesIfNotExist();

    }

    /**
     * Creates table if it not exists.
     */
    private void createTablesIfNotExist() {

        try (
            PreparedStatement prUsers = this.conn.prepareStatement("create table if not exists users(id serial primary key, name varchar(200), " +
                    "login varchar(20), email varchar(30), createDate timestamp);" )
        )
        {
            prUsers.execute();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }


    }


    /**
     * Creates user.
     * @param name user's name
     * @param login user's login
     * @param email user's email
     */
    public void createUser(String name, String login, String email) {

        try (
            PreparedStatement pr = this.conn.prepareStatement("INSERT INTO users(name, login, email, createdate) values(?, ?, ?, ?) " +
                    "WHERE NOT EXISTS (SELECT name, login FROM users " +
                    "WHERE name = ? AND login = ?)");
            )
        {
            pr.setString(1, name);
            pr.setString(2, login);
            pr.setString(3, email);
            pr.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
            pr.setString(5, name);
            pr.setString(6, login);
            pr.executeUpdate();

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Edits users.
     * @param name user's name to edit
     * @param login user's login to edit
     * @param newLogin user's new login
     * @param newEmail user's new email
     */
    public void editUser(String name, String login, String newLogin, String newEmail) {

        try (
            PreparedStatement pr = this.conn.prepareStatement("UPDATE users SET login = ?, email = ? " +
                    "WHERE name = ? AND login = ?")
        )
        {
            pr.setString(1, newLogin);
            pr.setString(2, newEmail);
            pr.setString(3, name);
            pr.setString(4, login);
            pr.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Gets user from the table.
     * @param name user's name to get
     * @param login user's login to get
     */
    public User getUser(String name, String login) {

        User user = null;
        try (
            PreparedStatement pr = this.conn.prepareStatement("SELECT from users WHERE name = ? AND login = ?")
        )
        {
            pr.setString(1, name);
            pr.setString(2, login);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                user = new User(rs.getString("name"), rs.getString("login"), rs.getString("email"), rs.getTimestamp("createdate"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return user;
    }


    /**
     * Deletes user from table.
     * @param name user's name to delete.
     * @param login user's login to delete.
     */
    public void deleteUser(String name, String login) {

        try (
            PreparedStatement pr = this.conn.prepareStatement("DELETE from users WHERE name = ? AND login = ?");
        )
        {
            pr.setString(1, name);
            pr.setString(2, login);
            pr.executeUpdate();

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Closes connection to database.
     */
    public void closeConnection() {

        if(this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
