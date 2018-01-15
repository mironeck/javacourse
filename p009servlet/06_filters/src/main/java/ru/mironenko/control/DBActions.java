package ru.mironenko.control;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mironenko.models.Role;
import ru.mironenko.models.User;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;


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

        try {
            Class.forName("org.postgresql.Driver");
            this.conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        createUserTableIfNotExist();
        createRolesTableIfNotExist();


//        Role roleAdmin = new Role("admin");
//        Role roleUser =  new Role("user");
//        roleAdmin.setId(1);
//        roleUser.setId(2);
//        User admin = new User("admin", "admin", "admin@admin", "admin", new Timestamp(new Date().getTime()));
//        User user = new User("user", "user", "user@user", "user", new Timestamp(new Date().getTime()));
//        admin.setRole(roleAdmin);
//        user.setRole(roleUser);
//        createUser(admin);
//        createUser(user);

    }

    private void createUserTableIfNotExist() {

        try (
                PreparedStatement pr = this.conn.prepareStatement("CREATE TABLE if not exists " +
                        "userstable(id serial PRIMARY KEY , name VARCHAR(200), " +
                        "login VARCHAR(20), email VARCHAR(30), password VARCHAR(30), createdate TIMESTAMP, role_id INTEGER);" )
        )
        {
            pr.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void createRolesTableIfNotExist() {

        try (
                PreparedStatement pr = this.conn.prepareStatement("CREATE TABLE if not exists " +
                        "rolestable(id serial PRIMARY KEY, name VARCHAR(200)" )
        )
        {
            pr.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

    }

    /**
     * Creates user.
     */
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
//            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public void createRole(String name) {

        try(
                PreparedStatement pr = this.conn.prepareStatement("INSERT INTO " +
                        "roles(name) VALUES (?)"
                )
        )
        {
            pr.setString(1, name);
            pr.executeUpdate();
        }catch (SQLException e) {
//            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }


    /**
     * Edits users.
     */
    public void editUser(String email, String newLogin, String newEmail, int newRoleId) {

        try(
                PreparedStatement pr = this.conn.prepareStatement("UPDATE userstable SET login = ?, email = ?, roleid = ? " +
                        "WHERE email = ?")
           )
        {
            pr.setString(1 , newLogin);
            pr.setString(2 , newEmail);
            pr.setInt(3 , newRoleId);
            pr.setString(4 , email);
            pr.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    public void editUsersRole(int roleId, String name) {

        try(
                PreparedStatement pr = this.conn.prepareStatement("UPDATE roles SET role = ? " +
                        "WHERE roleid = ?")
        )
        {
            pr.setString(1 , name);
            pr.setInt(2 , roleId);
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
                PreparedStatement pr = this.conn.prepareStatement("DELETE from userstable WHERE name = ? AND login = ?")
        )
        {
            pr.setString(1, name);
            pr.setString(2, login);
            pr.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    public User getUser(String name, String login) {

        User user = null;

        try( PreparedStatement pr = this.conn.prepareStatement("SELECT from userstable WHERE name = ? AND login = ?"))
        {
            pr.setString(1, name);
            pr.setString(2, login);
            ResultSet rs = pr.executeQuery();
            while(rs.next()) {

                user = new User(rs.getString("name"), rs.getString("login"), rs.getString("email"),
                        rs.getString("password"), rs.getTimestamp("createdate"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    public List<User> getUserList() {

        List<User> userList = new ArrayList<>();
        ResultSet rs = null;
        try (
                PreparedStatement pr = this.conn.prepareStatement("SELECT u.name as name, u.login as login, " +
                        "u.email as email, u.password as pass, u.createdate as create,  r.name as rname, " +
                        "r.role_id as roleid FROM userstable as u left join rolestable as r on u.role_id = r.id") ) {
            rs = pr.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString("name"),rs.getString("login"),
                        rs.getString("email") , rs.getString("password"), rs.getTimestamp("createdate"));
                Role role = new Role(rs.getString("rname"));
                role.setId(rs.getInt("role_id"));
                user.setRole(role);
                userList.add(user);
            }
        } catch (SQLException e){
            LOG.error(e.getMessage(), e);
        }
        return userList;
    }


    public int getRoleId(String login) {

        int result = 1;

        try(
                PreparedStatement pr = this.conn.prepareStatement("SELECT FROM userstable WHERE login = ?")
        )
        {
            pr.setString(1, login);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                result = rs.getInt("role_id");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        return result;
    }


    public List<Role> getRoles() {

        List<Role> result = new ArrayList<>();
        ResultSet rs = null;

        try( PreparedStatement pr = this.conn.prepareStatement("SELECT FROM roles") )
        {

            rs = pr.executeQuery();
            while(rs.next()) {
                Role role = new Role(rs.getString("name"));
                role.setId(rs.getInt("role_id"));
                result.add(role);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
