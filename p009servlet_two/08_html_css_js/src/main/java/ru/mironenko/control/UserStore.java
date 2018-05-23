package ru.mironenko.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mironenko.models.Role;
import ru.mironenko.models.User;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class UserStore {

    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class);

    private static final String CREATE_USERS_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS usershtml(id serial PRIMARY KEY," +
            "name VARCHAR(20), login VARCHAR(20), password VARCHAR(20), email VARCHAR(20),  country VARCHAR(20),  city VARCHAR(20), createdate TIMESTAMP);";

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
        createTablesIfNotExist(CREATE_USERS_TABLE_QUERY);

    }

    private void createTablesIfNotExist(String query) {

        try (
                PreparedStatement prUsers = this.conn.prepareStatement(query)
        )
        {
            prUsers.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Creates user.
     * @param user user
     */
    public void createUser(User user) {

        try(
                PreparedStatement pr = this.conn.prepareStatement("INSERT INTO usershtml(name, login, password, email, country,  city, createdate) VALUES (?, ?, ?, ?, ?, ?, ?)")
        )
        {
            pr.setString(1, user.getName());
            pr.setString(2, user.getLogin());
            pr.setString(3, user.getPassword());
            pr.setString(4, user.getEmail());
            pr.setString(5, user.getCountry());
            pr.setString(6, user.getCity());
            pr.setTimestamp(7, (Timestamp) user.getCreateDate());
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
                PreparedStatement pr = this.conn.prepareStatement("UPDATE usershtml SET login = ?, email = ? " +
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
     * Deletes user from table.
     * @param name user's name to delete.
     * @param login user's login to delete.
     */
    public void deleteUser(String name, String login) {

        try (
                PreparedStatement pr = this.conn.prepareStatement("DELETE FROM usershtml WHERE name = ? AND login = ?");
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
                PreparedStatement pr = this.conn.prepareStatement("SELECT * FROM usershtml;")
        )
        {
            ResultSet rs = pr.executeQuery();
            while(rs.next()) {
                User user =  new User(rs.getString("name"), rs.getString("login"),
                        rs.getString("password"), rs.getString("email"), rs.getTimestamp("createdate"));
                user.setCountry(rs.getString("country"));
                user.setCity(rs.getString("city"));
                result.add(user);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public boolean isCredential(String login, String password) {

        boolean exist = false;
        List<User> userList = UserStore.getInstance().getUserList();
        for(User user : userList) {
            if(user.getLogin().equals(login) && user.getPassword().equals(password)) {
                exist = true;
                break;
            }
        }
        return exist;
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


    public User getUser(String login, String password) {
        User result = null;

        try (
                PreparedStatement pr = this.conn.prepareStatement("select u.name, u.login, u.password, u.email, u.createdate from \n" +
                        "usershtml as u "+
                        "where u.login = ? and u.password = ?;")
        )
        {
            pr.setString(1, login);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            while(rs.next()) {
                result =  new User(rs.getString("name"), rs.getString("login"),
                        rs.getString("password"), rs.getString("email"), rs.getTimestamp("createdate"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        return result;
    }

//    public static void main(String[] args) {

//        User user = new User("admin", "admin", "admin", "admin@admin",new Timestamp(new Date().getTime()));
//        user.setCountry("Canada");
//        user.setCity("Montreal");
//        User user2 = new User("user", "user", "user", "user@user",new Timestamp(new Date().getTime()));
//        user2.setCountry("USA");
//        user2.setCity("NY");
//        UserStore.getInstance().createUser(user);
//        UserStore.getInstance().createUser(user2);

//        List<User> list = UserStore.getInstance().getUserList();
//
//        for(User tmp : list) {
//            System.out.println(tmp);
//        }
//
//        boolean test = UserStore.getInstance().isCredential("admin", "admin");
//        System.out.println(test);
//
//
//        User ggg = UserStore.getInstance().getUser("admin", "admin");
//        System.out.println(ggg);
//
//    }
}
