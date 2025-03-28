package org.prog3.dao;

import org.prog3.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for the User entity.
 * Handles all CRUD (Create, Read, Update, Delete) operations on the "users" table.
 */
public class UserDAO {


    /**
     * Inserts a new user record into the database.
     *
     * @param name the name of the new user.
     * @param username the unique username of the new user.
     * @param password the password of the new user.
     * @param role the role assigned to the user.
     * @return the created User object with an assigned id, or null if creation failed.
     */
    public User create(String name, String username, String password, String role) {
        String sql = "INSERT INTO User (name, username, password, role) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set the parameters for the SQL statement
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, role);

            // Execute the insertion and check if any rows were affected
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                // Retrieve the generated key (id) for the new user
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        return new User(generatedId, name, username, password, role);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Failed to create User: " + e.getMessage());
        }
        return null;
    }


    /**
     * Retrieves a user record by name.
     *
     * @param username the name of the user to retrieve.
     * @return the matching User object, or null if no user is found.
     */
    public User findUser (String username) {
        final String sql = "SELECT * FROM User WHERE username = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("username"),
                            rs.getString("password"), rs.getString("role"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to find user by ID: " + e.getMessage());
        }
        return null;
    }


    /**
     * Retrieves all users from the database.
     *
     * @return a List of User objects. Returns an empty list if no users are found.
     */
    public List<User> displayAllUsers () {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                final User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("❌ Failed to list all users: " + e.getMessage());
        }
        return users;
    }


    /**
     * Updates an existing user record in the database.
     *
     * @param username the new username to set for the user.
     * @param password the new password to set for the user.
     * @param name     the name of the user to update.
     * @return true if the update was successful, false otherwise.
     */
    public boolean update(String username, String password, String name) {
        final String sql = "UPDATE User SET username = ?, password = ? WHERE name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, name);

            int affectedRows = statement.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            System.err.println("Failed to update user: " + e.getMessage());
        }
        return false;
    }


    /**
     * Deletes a user record from the database.
     *
     * @param username the name of the user to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean delete (String username) {
        String sql = "DELETE FROM User WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, username);
            int affectedRows = statement.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            System.err.println("Failed to delete user: " + e.getMessage());
        }
        return false;
    }


    /**
     * Promotes a user to admin by updating their role.
     *
     * @param username the username of the user to promote.
     * @return true if exactly one row was updated or false otherwise.
     */
    public boolean promoteUserToAdmin(String username) {
        final String sql = "UPDATE User SET role = 'admin' WHERE username = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            int updatedRows = statement.executeUpdate();
            return updatedRows == 1; // Returns true if exactly 1 row was updated
        } catch (SQLException e) {
            System.err.println("Failed to promote user to admin: " + e.getMessage());
        }
        return false;
    }
}


