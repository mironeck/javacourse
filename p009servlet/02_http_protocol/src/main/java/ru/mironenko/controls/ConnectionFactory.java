package ru.mironenko.controls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionFactory {

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(DBActions.class);

    private static final String DATASOURCE_NAME = "jdbc:postgresql://localhost:5432/users_store";
    private static DataSource ds = null;

    static {
        Context initContext = null;
        try {
            initContext = new InitialContext();
            Context envContext = null;
            envContext = (Context)initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup(DATASOURCE_NAME);
        } catch (NamingException e) {
            log.error(e.getMessage(), e);
        }

    }

    public static Connection getConnection() throws SQLException {
        Connection conn = ds.getConnection();
        return conn;
    }
}
