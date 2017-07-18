package ru.mironenko.testtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Date;

/**
 * Created by nikita on 08.07.2017.
 */
/**
 * Connects to DB, creates table if it not exist, adds vacancies in table.
 */
public class ConnectToDB {

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(ConnectToDB.class);

    /**
     * Properties where store host, login and password to connect to DB.
     */
    private final Properties prop = new Properties();

    /**
     * Link to Connection.
     */
    private Connection conn;

    /**
     * Constructor of ConnectionToDB. Reads prop, connects to DB, creates table "vacancies" if it not exists.
     * @throws IOException
     */
    public ConnectToDB() throws IOException, SQLException {

        try (
                InputStream io = getClass().getClassLoader().getResourceAsStream("resources.properties")

        ) {
            prop.load(io);
            String url = prop.getProperty("db.host");
            String login = prop.getProperty("db.login");
            String pass = prop.getProperty("db.password");
            conn = DriverManager.getConnection(url, login, pass);
        }

        createTableIfNotExist();
    }

    /**
     * Creates table in DB "vacancies" if it not exists.
     */
    private void createTableIfNotExist() throws SQLException {

        try (
            PreparedStatement pr = this.conn.prepareStatement("create table if not exists testThree(id serial primary key, position varchar(500), date timestamp, unique (position, date));");
        )
        {
            pr.execute();
        }
    }

    /**
     * Adds data in table "vacancies": position and date of vacancy's creation.
     * @param vacancy - vacancy
     * @param date - date of vacancy's creation.
     * @return true if addition is succeed.
     */
    public boolean addVacancyToDB(String vacancy, Date date) throws SQLException {

        boolean result;

        try (
            PreparedStatement prAdd = this.conn.prepareStatement("insert into testThree(position, date) values(?, ?)");
        ){
            prAdd.setString(1, vacancy);
            prAdd.setTimestamp(2, new java.sql.Timestamp(date.getTime()));
            prAdd.executeUpdate();
            result = true;
        }
        return result;
    }

    /**
     * Checks is table is empty
     * @return true if empty, false if not
     */
    public boolean isTableEmpty() throws SQLException{

        int result = -1;

        try (
            PreparedStatement prCheck = this.conn.prepareStatement("SELECT CASE WHEN EXISTS (SELECT * FROM testThree LIMIT 1) THEN 1 ELSE 0 END");
            ResultSet rs = prCheck.executeQuery()
        )
        {
            if(rs.next()) {
                result = rs.getInt(1);
            }
        }
       return (result == 0) ? true : false;
    }

    /**
     * Closes connection to DB
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
