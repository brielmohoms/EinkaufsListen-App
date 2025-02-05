package org.prog3.dao;

import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Manages the database connection.
 */
public class DatabaseConnection {
    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;

    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties");

            if (inputStream == null) {
                throw new IOException("db.properties file not found in classpath!");
            }

            properties.load(inputStream);
            inputStream.close();  // Close after reading

            dbUrl = properties.getProperty("db.url");
            dbUsername = properties.getProperty("db.username", "");
            dbPassword = properties.getProperty("db.password", "");

            if (dbUrl == null || dbUrl.isEmpty()) {
                throw new IllegalStateException("Database URL is not configured. Check db.properties.");
            }

            Class.forName("org.sqlite.JDBC");

        } catch (IOException e) {
            System.err.println("Failed to load database configuration: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC Driver not found: " + e.getMessage());
        }
    }

    /**
     * Establishes a connection to the database.
     *
     * @return A Connection object.
     * @throws SQLException if the connection fails.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }
}
