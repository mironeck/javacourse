package ru.mironenko.controls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mironenko.models.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Date;
import java.util.Properties;


public class DBActions {

    private static final Logger LOG = LoggerFactory.getLogger(DBActions.class);

    /**
     * instance of Properties;
     */
    private final Properties prop = new Properties();

    /**
     * Reference to Connection of database
     */
    private Connection conn;

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
            LOG.error(e.getMessage(), e);
        }
        createTablesIfNotExist();

    }

    private void createTablesIfNotExist() {

        try (
                PreparedStatement prUsers = this.conn.prepareStatement("create table if not exists users(id serial primary key, name varchar(200), " +
                        "login varchar(20), email varchar(30), createDate timestamp);" )
        )
        {
            prUsers.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Creates user.
     * @param name user's name
     * @param login user's login
     * @param email user's email
     */
    public void createUser(String name, String login, String email) {

        try(
                PreparedStatement pr = this.conn.prepareStatement("INSERT INTO users(name, login, email, createate) VALUES (?, ?, ?, ?)" +
                "WHERE NOT EXISTS (SELECT name, login FROM users " +
                        "WHERE name = ? AND login = ?)")
        )
        {
            pr.setString(1, name);
            pr.setString(2, login);
            pr.setString(3, email);
            pr.setTimestamp(4, new Timestamp(new Date().getTime()));
            pr.setString(5, name);
            pr.setString(6, login);
            pr.executeUpdate();
        }catch (SQLException e) {
            LOG.error(e.getMessage(), e);
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

        try(
                PreparedStatement pr = this.conn.prepareStatement("UPDATE users SET login = ?, email = ? " +
                        "WHERE name = ? AND login = ?")
           )
        {
            pr.setString(1 , newLogin);
            pr.setString(2 , newEmail);
            pr.setString(3 , name);
            pr.setString(4 , login);
            pr.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Gets user from the table.
     * @param name user's name to get
     * @param login user's login to get
     */
    public User getUser(String name, String login) {

        User user = null;
        try(
                PreparedStatement pr = this.conn.prepareStatement("SELECT FROM users WHERE name = ? AND login = ?")
           )
        {
            pr.setString(1, name);
            pr.setString(2, login);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setCreateDate(rs.getTimestamp("createdate"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
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
                LOG.error(e.getMessage(), e);
            }
        }
    }

}
