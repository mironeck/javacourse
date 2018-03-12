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

    private static final String CREATE_USERS_TABLE_QUERY = "CREATE TABLE IF NOT EXIST userswithpass(id serial PRIMARY KEY," +
            "name VARCHAR(20), login VARCHAR(20), password VARCHAR(20), email VARCHAR(20), role_id INTEGER, createdate TIMESTAMP);";

    private static final String CREATE_ROLES_TABLE_QUERY = "CREATE TABLE IF NOT EXIST roles(id serial primary key, role_id INTEGER, name VARCHAR(20));";

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
        createTablesIfNotExist(CREATE_ROLES_TABLE_QUERY);

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
                PreparedStatement pr = this.conn.prepareStatement("INSERT INTO userswithpass(name, login, password, email, role_id,  createdate) VALUES (?, ?, ?, ?, ?, ?)")
        )
        {
            pr.setString(1, user.getName());
            pr.setString(2, user.getLogin());
            pr.setString(3, user.getPassword());
            pr.setString(4, user.getEmail());
            pr.setInt(5, user.getRole().getId());
            pr.setTimestamp(6, (Timestamp) user.getCreateDate());
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
                PreparedStatement pr = this.conn.prepareStatement("UPDATE userswithpass SET login = ?, email = ? " +
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


//    /**
//     * Gets user from the table.
//     * @param name user's name to get
//     * @param login user's login to get
//     */
//    public User getUser(String name, String login) {
//
//        User result = null;
//        try(
//                PreparedStatement pr = this.conn.prepareStatement("SELECT * FROM userswithpass WHERE name = ? AND login = ?")
//        )
//        {
//            pr.setString(1, name);
//            pr.setString(2, login);
//            ResultSet rs = pr.executeQuery();
//            while (rs.next()) {
//                result = new User(rs.getString("name"), rs.getString("login"),
//                        rs.getString("password"), rs.getString("email"), rs.getTimestamp("createdate"));
//
//            }
//        } catch (SQLException e) {
//            LOG.error(e.getMessage(), e);
//        }
//        return result;
//    }


    /**
     * Deletes user from table.
     * @param name user's name to delete.
     * @param login user's login to delete.
     */
    public void deleteUser(String name, String login) {

        try (
                PreparedStatement pr = this.conn.prepareStatement("DELETE FROM userswithpass WHERE name = ? AND login = ?");
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
                PreparedStatement pr = this.conn.prepareStatement("select u.name, u.login, u.password, u.email, u.createdate, r.name as role, r.role_id as \"role id\" from \n" +
                        "userswithpass as u inner join roles as r\n" +
                        "on u.role_id = r.role_id;")
        )
        {
            ResultSet rs = pr.executeQuery();
            while(rs.next()) {
                User user =  new User(rs.getString("name"), rs.getString("login"),
                        rs.getString("password"), rs.getString("email"), rs.getTimestamp("createdate"));
                Role role = new Role(Integer.parseInt(rs.getString("role id")), rs.getString("role"));
                user.setRole(role);
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


    public List<Role> getRoles() {

        List<Role> roles = new ArrayList<>();
        try (
                PreparedStatement pr = this.conn.prepareStatement("SELECT * FROM roles;")
        )
        {
            ResultSet rs = pr.executeQuery();
            while(rs.next()) {
                Role role =  new Role(rs.getInt("role_id"), rs.getString("name"));
                roles.add(role);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        return roles;
    }


    public void createNewRole(int role_id, String name) {

        try(
                PreparedStatement pr = this.conn.prepareStatement("INSERT INTO roles(role_id, name) VALUES (?, ?)")
        )
        {
            pr.setInt(1, role_id);
            pr.setString(2, name);
            pr.executeUpdate();
        }catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    public void editRole(int roleId, String name, int newRoleId, String newName) {

        try(
                PreparedStatement pr = this.conn.prepareStatement("UPDATE roles SET role_id = ?, name = ? " +
                        "WHERE role_id = ? AND name = ?")
        )
        {
            pr.setInt(1 , newRoleId);
            pr.setString(2 , newName);
            pr.setInt(3 , roleId);
            pr.setString(4 , name);
            pr.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void deleteRole(int id, String name) {

        try (
                PreparedStatement pr = this.conn.prepareStatement("DELETE FROM roles WHERE role_id = ? AND name = ?")
        )
        {
            pr.setInt(1, id);
            pr.setString(2, name);
            pr.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

    }

    public static void main(String[] args) {

        List<User> list = UserStore.getInstance().getUserList();

        for(User user : list) {
            System.out.println(user);
        }
    }
}
