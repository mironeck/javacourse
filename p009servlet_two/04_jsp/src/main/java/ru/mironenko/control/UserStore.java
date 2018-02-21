package ru.mironenko.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mironenko.model.User;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class UserStore {


    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class);

    //Eager initialization singleton
    private static final UserStore instance = new UserStore();

    private UserStore(){
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserStore getInstance() {
        return instance;
    }


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
    public void init() throws Exception{

        InputStream io = getClass().getClassLoader().getResourceAsStream("resources.properties");
        ConnectionPoolClass connectionPoolClass = new ConnectionPoolClass();
        DataSource dataSource = connectionPoolClass.setUp();

        try {

            prop.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String url = prop.getProperty("db.host");
//        String username = prop.getProperty("db.login");
//        String password = prop.getProperty("db.password");
        try {
            Class.forName("org.postgresql.Driver");
//            this.conn = DriverManager.getConnection(url, username, password);
            this.conn = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        createTablesIfNotExist();

//        createUser("admin", "admin", "admin@admin");
//        createUser("user", "user", "user@user");
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
                PreparedStatement pr = this.conn.prepareStatement("INSERT INTO users(name, login, email, createdate) VALUES (?, ?, ?, ?)")
        )
        {
            pr.setString(1, name);
            pr.setString(2, login);
            pr.setString(3, email);
            pr.setTimestamp(4, new Timestamp(new Date().getTime()));
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

        User result = null;
        try(
                PreparedStatement pr = this.conn.prepareStatement("SELECT * FROM users WHERE name = ? AND login = ?")
        )
        {
            pr.setString(1, name);
            pr.setString(2, login);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                result = new User(rs.getString("name"), rs.getString("login"),
                        rs.getString("email"), rs.getTimestamp("createdate"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * Deletes user from table.
     * @param name user's name to delete.
     * @param login user's login to delete.
     */
    public void deleteUser(String name, String login) {

        try (
                PreparedStatement pr = this.conn.prepareStatement("DELETE FROM users WHERE name = ? AND login = ?");
        )
        {
            pr.setString(1, name);
            pr.setString(2, login);
            pr.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    public List<User> getUserList() {

        List<User> result = new ArrayList<>();

        try (
                PreparedStatement pr = this.conn.prepareStatement("SELECT * FROM users;")
        )
        {
            ResultSet rs = pr.executeQuery();
            while(rs.next()) {
                User user =  new User(rs.getString("name"), rs.getString("login"),
                        rs.getString("email"), rs.getTimestamp("createdate"));
                result.add(user);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
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
