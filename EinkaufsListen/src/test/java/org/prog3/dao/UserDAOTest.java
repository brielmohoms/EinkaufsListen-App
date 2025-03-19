package org.prog3.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.prog3.models.User;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link UserDAO} class.
 */
public class UserDAOTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    @InjectMocks
    private UserDAO userDAO;

    /**
     * Sets up mock dependencies and cleans up the User table before each test.
     */
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        userDAO = new UserDAO();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM User"); // Ensure no users exist before tests
        }
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockStatement);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
    }

    /**
     * Cleans up the User table after each test.
     */
    @AfterEach
    void tearDown() throws Exception {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM User");
        }
    }

    /**
     * Tests creating a new user.
     */
    @Test
    void testCreateUser() {
        User createdUser = userDAO.create("John", "john_doe", "password123", "regular");
        assertNotNull(createdUser, "User creation should return a non-null object");
        assertEquals("john_doe", createdUser.getName(), "The name should match");
        assertEquals("John", createdUser.getUsername(), "The username should match");
        assertEquals("regular", createdUser.getRole(), "The role should match");
    }

    /**
     * Tests updating an existing user.
     */
    @Test
    void testUpdateUser() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);

        boolean updated = userDAO.update("jane_smith", "newpassword789", "Jane Smith");
        assertFalse(updated, "User should be updated successfully.");
    }

    /**
     * Tests deleting a user.
     */
    @Test
    void testDeleteUser() {
        User user = userDAO.create("Alice Brown", "alice_brown", "password000", "user");
        assertTrue(userDAO.delete(user.getName()), "User should be deleted successfully.");
        User deletedUser = userDAO.findUser("Alice Brown");
        assertNull(deletedUser, "User should not be found after deletion.");
    }

    /**
     * Tests promoting a user to admin.
     */
    @Test
    void testPromoteUserToAdmin() {
        User user = userDAO.create("Admin User", "adminUser", "password123", "user");
        boolean promotedUser = userDAO.promoteUserToAdmin("adminUser");
        assertTrue(promotedUser, "User should be promoted to admin successfully.");
    }

    /**
     * Tests finding a user by username.
     */
    @Test
    void testFindUser() throws SQLException {
        User user = userDAO.create("Admin User", "John", "password123", "user");
        User foundUser = userDAO.findUser("John");
        assertNotNull(foundUser, "User should be found successfully.");
        assertEquals("John", foundUser.getUsername(), "The username should match.");
    }

    /**
     * Tests retrieving all users.
     */
    @Test
    void testFindAllUsers() {
        userDAO.create("Tom Green", "tom_green", "password111", "user");
        userDAO.create("Lucy Yellow", "lucy_yellow", "password222", "user");

        List<User> users = userDAO.displayAllUsers();
        assertFalse(users.isEmpty(), "User list should not be empty after adding users.");
        assertTrue(users.size() >= 2, "There should be at least two users in the list.");
    }
}
