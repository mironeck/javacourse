package ru.mironenko.control;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mironenko.models.Role;
import ru.mironenko.models.User;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The class operates with database: create tables if it are not exists, add users and roles, edits, deletes, gets.
 */
public class DBController {

    private static final Logger LOG = LoggerFactory.getLogger(DBController.class);


    private String createUsersTableIfNotExistsQuery = "CREATE TABLE if not exists " +
            "userstable(id serial PRIMARY KEY , name VARCHAR(200), " +
            "login VARCHAR(20), email VARCHAR(30), password VARCHAR(30), createdate TIMESTAMP, role_id INTEGER);";

    private String createRolesTableIfNotExistsQuery = "CREATE TABLE if not exists " +
            "rolestable(id serial PRIMARY KEY, name VARCHAR(200));";
    /**
     * instance of Properties;
     */
    private final Properties prop = new Properties();

    private Connection conn;

    private static final DBController instance = new DBController();

    public static DBController getInstance() {
        return instance;
    }

    public DBController() {

        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void init() throws Exception {

        InputStream io = getClass().getClassLoader().getResourceAsStream("resources.properties");
        ConnectionPoolClass connectionPoolClass = new ConnectionPoolClass();
        DataSource dataSource = connectionPoolClass.setUp();
        try {
            prop.load(io);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("org.postgresql.Driver");
            this.conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        createTableIfNotExist(createUsersTableIfNotExistsQuery);
        createTableIfNotExist(createRolesTableIfNotExistsQuery);


//        Role roleAdmin = new Role("admin");
//        Role roleUser =  new Role("user");
//        roleAdmin.setId(1);
//        roleUser.setId(2);
//        User admin = new User("admin", "admin", "admin@admin", "admin", new Timestamp(new Date().getTime()), 1);
//        User user = new User("user", "user", "user@user", "user", new Timestamp(new Date().getTime()), 2);
//        admin.setRole(roleAdmin);
//        user.setRole(roleUser);
//        createUser(admin);
//        createUser(user);
//        createRole("admin");
//        createRole("user");
    }


    private void createTableIfNotExist(String query) {

        try (
                PreparedStatement pr = this.conn.prepareStatement(query)
        )
        {
            pr.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }


    public void createUser(User user) {

        try(
                PreparedStatement pr = this.conn.prepareStatement("INSERT INTO " +
                        "userstable(name, login, email, password, createdate, role_id) VALUES (?, ?, ?, ?, ?, ?)"
                )
        )
        {
            pr.setString(1, user.getName());
            pr.setString(2, user.getLogin());
            pr.setString(3, user.getEmail());
            pr.setString(4, user.getPassword());
            pr.setTimestamp(5, user.getCreateDate());
            pr.setInt(6, user.getRole().getId());

            pr.executeUpdate();
        }catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }


    private void createRole(int id, String name) {

        try(
                PreparedStatement pr = this.conn.prepareStatement("INSERT INTO " +
                        "rolestable(id, name) VALUES (?, ?)"
                )
        )
        {
            pr.setInt(1, id);
            pr.setString(2, name);
            pr.executeUpdate();
        }catch (SQLException e) {
//            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }

    }


    public void deleteUser(User user) {

        try (
                PreparedStatement pr = this.conn.prepareStatement("DELETE from userstable WHERE name = ? AND login = ?")
        )
        {
            pr.setString(1, user.getName());
            pr.setString(2, user.getLogin());
            pr.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    public void deleteRole(String role) {

        try (
                PreparedStatement pr = this.conn.prepareStatement("DELETE from rolestable WHERE name = ?")
        )
        {
            pr.setString(1, role);
            pr.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public User getUser(String email) {

        User result = null;

        try( PreparedStatement pr = this.conn.prepareStatement("SELECT u.name, u.login, u.email, u.password, u.createdate, u.role_id FROM userstable as u WHERE email = ?"))
        {
            pr.setString(1, email);
            ResultSet rs = pr.executeQuery();
            while(rs.next()) {

                result = new User(rs.getString("name"), rs.getString("login"), rs.getString("email"),
                        rs.getString("password"), rs.getTimestamp("createdate"), rs.getInt("role_id"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<User> getUsers() {

        List<User> result = new ArrayList<>();
        try (
                PreparedStatement pr = this.conn.prepareStatement("SELECT u.name, u.login, u.email, u.password, u.createdate, u.role_id FROM userstable as u;") )
        {
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString("name"),rs.getString("login"),
                        rs.getString("email"), rs.getString("password"), rs.getTimestamp("createdate"),
                        Integer.parseInt(rs.getString("role_id")));

                user.setRoleId(Integer.parseInt(rs.getString("role_id")));
                result.add(user);
            }
        } catch (SQLException e){
            LOG.error(e.getMessage(), e);
        }


        return result;
    }


    public List<Role> getRoles() {

        List<Role> result = new ArrayList<>();

        try (
        PreparedStatement pr = this.conn.prepareStatement("SELECT * FROM rolestable;") )
        {
            ResultSet rs = pr.executeQuery();
            while(rs.next()) {
                Role role = new Role(rs.getString("name"));
                result.add(role);
            }
        } catch (SQLException e){
            LOG.error(e.getMessage(), e);
        }

        return result;
    }


    public boolean isCredential(String login, String password) {

        boolean exists = false;
        for(User user : getUsers()) {
            if(user.getLogin().equals(login) && user.getPassword().equals(password)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

//    public static void main(String[] args) {
//        BasicConfigurator.configure();
//
//        Role roleAdmin = new Role("admin");
//        roleAdmin.setId(1);
//
//        User admin = new User("admin", "admin", "admin@admin", "admin", new Timestamp(new Date().getTime()), 1);
//        admin.setRole(roleAdmin);
//        DBController controller = DBController.getInstance();
////        controller.createUser(admin);
////        controller.createRole("admin");
//
//        List<User> users = controller.getUsers();
//        List<Role> roles = controller.getRoles();
//
//        for(User user : users) {
//            System.out.println(user);
//        }
//
//        for(Role role : roles) {
//            System.out.println(role);
//        }
//
//        User user = controller.getUser("admin@admin");
//        System.out.println(user);
//
//        boolean test = controller.isCredential("user", "usergg");
//        System.out.println(test);
//    }
}
