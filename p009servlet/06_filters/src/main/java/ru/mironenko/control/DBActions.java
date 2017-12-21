package ru.mironenko.control;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mironenko.models.Role;
import ru.mironenko.models.User;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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

    private static final DBActions instance = new DBActions();

    public static DBActions getInstance() {
        return instance;
    }

    public DBActions() {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Connects to database
     */
    public void init() throws Exception {

        InputStream io = getClass().getClassLoader().getResourceAsStream("resources.properties");
        ConnectionPoolClass connectionPoolClass = new ConnectionPoolClass();
        DataSource dataSource = connectionPoolClass.setUp();
        try {
            prop.load(io);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = prop.getProperty("db.host");
        String username = prop.getProperty("db.login");
        String password = prop.getProperty("db.password");
        try {
            Class.forName("org.postgresql.Driver");
//            this.conn = DriverManager.getConnection(url, username, password);
            this.conn = dataSource.getConnection();
        } catch (Exception e) {
//            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
        createTablesIfNotExist();
    }

    private void createTablesIfNotExist() {

        try (
                PreparedStatement pr = this.conn.prepareStatement("create table if not exists " +
                        "userspas(id serial primary key, name varchar(200), " +
                        "login varchar(20), email varchar(30), password VARCHAR(30), role VARCHAR(30), createdate timestamp);" )
        )
        {
            pr.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

//        createUser("admin", "admin", "admin", "admin", "admin");

    }

    /**
     * Creates user.
     * @param name user's name
     * @param login user's login
     * @param email user's email
     */
    public void createUser(String name, String login, String email, String password, String role) {

        try(
                PreparedStatement pr = this.conn.prepareStatement("INSERT INTO " +
                        "userspas(name, login, email, password, role, createdate) VALUES (?, ?, ?, ?, ?, ?)"
                )
        )
        {
            pr.setString(1, name);
            pr.setString(2, login);
            pr.setString(3, email);
            pr.setString(4, password);
            pr.setString(5, role);
            pr.setTimestamp(6, new Timestamp(new Date().getTime()));

            pr.executeUpdate();
        }catch (SQLException e) {
//            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }

    }


    /**
     * Edits users.
     * @param name user's name to edit
     * @param login user's login to edit
     * @param newLogin user's new login
     * @param newEmail user's new email
     */
    public void editUser(String name, String login, String newLogin, String newEmail, String newRole) {

        try(
                PreparedStatement pr = this.conn.prepareStatement("UPDATE userspas SET login = ?, email = ?, role = ? " +
                        "WHERE name = ? AND login = ?")
           )
        {
            pr.setString(1 , newLogin);
            pr.setString(2 , newEmail);
            pr.setString(3 , newRole);
            pr.setString(4 , name);
            pr.setString(5 , login);
            pr.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    public void editUsersRole(String name, String login, String newRole) {

        try(
                PreparedStatement pr = this.conn.prepareStatement("UPDATE userspas SET role = ? " +
                        "WHERE name = ? AND login = ?")
        )
        {
            pr.setString(1 , newRole);
            pr.setString(2 , name);
            pr.setString(3 , login);
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
                PreparedStatement pr = this.conn.prepareStatement("SELECT FROM userspas WHERE name = ? AND login = ?")
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
                user.setEmail(rs.getString("password"));
                user.setEmail(rs.getString("role"));
                user.setCreateDate(rs.getTimestamp("createdate"));
                System.out.println(user.getName());
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        return user;
    }

    public User getUserByLogin(String login) {

        User user = null;
        try(
                PreparedStatement pr = this.conn.prepareStatement("SELECT FROM userspas WHERE login = ?")
        )
        {
            pr.setString(1, login);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setEmail(rs.getString("password"));
                user.setEmail(rs.getString("role"));
                user.setCreateDate(rs.getTimestamp("createdate"));
                System.out.println(user.getName());
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
                PreparedStatement pr = this.conn.prepareStatement("DELETE from userspas WHERE name = ? AND login = ?")
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

        List<User> userList = new LinkedList<>();

        try (
                PreparedStatement pr = this.conn.prepareStatement("SELECT * FROM userspas")
                )
        {
            ResultSet rs = pr.executeQuery();
            while(rs.next()) {
               User user = new User(rs.getString("name"), rs.getString("login"),
                       rs.getString("email"), rs.getString("password"),
                       new Role(rs.getString("role")), rs.getTimestamp("createdate") );

                userList.add(user);
            }
        } catch (SQLException e){
            LOG.error(e.getMessage(), e);
        }
        return userList;
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


    public boolean isCredential(String login, String password) {

        boolean exists = false;
        for(User user : getUserList()) {
            if(user.getLogin().equals(login) && user.getPassword().equals(password)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

}
