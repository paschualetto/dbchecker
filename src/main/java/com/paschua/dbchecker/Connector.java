package com.paschua.dbchecker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando
 */
public class Connector {

    private static final Logger LOG = Logger.getLogger(Connector.class.getSimpleName());

    public static boolean isConnected(Properties params) {
        boolean isConnected = false;
        
        String url = params.getProperty("url");
        String user = params.getProperty("user");
        String password = params.getProperty("password");
        LOG.log(Level.INFO, "Parameters{ URL: " + url + ", user: " + user + ", password: " + password + "}");

        if (validateParams(url, user, password)) {
            isConnected = tryConnectDatabase(url, user, password);
        } else {
            LOG.log(Level.WARNING, "Some parameters are invalid");
        }

        return isConnected;
    }

    private static boolean tryConnectDatabase(String url, String user, String password) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection != null;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "SQL state: " + ex.getSQLState() + ". " + ex.getMessage());
        }
        return false;
    }

    private static boolean validateParams(String... params) {
        for (String param : params) {
            if (param == null || param.equals("")) {
                return false;
            }
        }
        return true;
    }
}
