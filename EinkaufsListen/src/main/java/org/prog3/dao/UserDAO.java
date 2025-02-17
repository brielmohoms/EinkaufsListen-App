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
     * @param name the name of the new user
     * @param username the unique username of the new user.
     * @param password the password of the new user.
     * @return the created User object with an assigned id, or null if creation failed.
     */
    public User create(String name, String username, String password, String role) {
        // SQL query to insert a new user
        final String sql = "INSERT INTO User (name, username, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set the parameters for the SQL statement
            pstmt.setString(1, name);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, role);

            // Execute the insertion and check if any rows were affected
            final int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                // Retrieve the generated key (id) for the new user
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        final int generatedId = generatedKeys.getInt(1);
                        return new User(generatedId, name, username, password, role);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to create User: " + e.getMessage());
        }
        return null;
    }


    /**
     * Retrieves a user record by its id.
     *
     * @param name the id of the user to retrieve.
     * @return the matching User object, or null if no user is found.
     */
    public User findByName(String name) {
        final String sql = "SELECT * FROM User WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("role"));
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
    public List<User> findAll() {
        final List<User> users = new ArrayList<>();
        final String sql = "SELECT * FROM User";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                final User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                // Optionally, load shopping lists or other related entities here if needed.
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Failed to list all users: " + e.getMessage());
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
    public boolean update(final String username, final String password, final String name) {
        final String sql = "UPDATE User SET username = ?, password = ? WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, name);

            final int affectedRows = pstmt.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            System.err.println("Failed to update user: " + e.getMessage());
        }
        return false;
    }


    /**
     * Deletes a user record from the database.
     *
     * @param id the id of the user to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean delete(final int id) {
        final String sql = "DELETE FROM User WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            final int affectedRows = pstmt.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            System.err.println("Failed to delete user: " + e.getMessage());
        }
        return false;
    }


    /**
     *
     * @param name
     * @return
     */
    public boolean promoteUserToAdmin(String name) {
        final String sql = "UPDATE User SET role = 'admin' WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            int updatedRows = pstmt.executeUpdate();
            return updatedRows == 1; // Returns true if exactly 1 row was updated
        } catch (SQLException e) {
            System.err.println("Failed to promote user to admin: " + e.getMessage());
        }
        return false;
    }
}


