package org.prog3.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Responsible for managing the connection between the java application and the database(SQLite)
 */
public class DatabaseConnection {

    private static String dbURL;  // Database URl
    private static String dbUsername;  // Database Username
    private static String dbPassword;  // Database Password

    // Block to load database configuration from db.properties file
    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/db.properties"));

            dbURL = properties.getProperty("db.url");
            dbUsername = properties.getProperty("db.username", "");
            dbPassword = properties.getProperty("db.password", "");
        } catch (IOException e) {
            System.out.println("Failed to load database configuration: " + e.getMessage());
        }
    }

    /**
     * Establishes a connection to the database
     *
     * @return a connection object that represents the connection to the database
     * @throws SQLException if the connection fails due to invalid configuration
     */
    public static Connection getConnection() throws SQLException {
        if (dbURL == null || dbURL.isEmpty()) {
            throw new SQLException("Database URL is not configured");
        }
        return DriverManager.getConnection(dbURL, dbUsername, dbPassword);
    }

    /**
     * Validates the database connection. It attempts to establish a connection to the database and checks if the connection is open
     *
     * @return true if the connection is valid and open and false otherwise
     */
    public static boolean validateConnection() {
        try (Connection connection = getConnection()) {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            System.err.println("Failed to validate database connection: " + e.getMessage());
            return false;
        }
    }

}