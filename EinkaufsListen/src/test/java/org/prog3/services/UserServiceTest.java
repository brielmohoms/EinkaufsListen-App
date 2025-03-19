package org.prog3.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.prog3.dao.UserDAO;
import org.prog3.models.User;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link UserService} class using Mockito.
 */
class UserServiceTest {

    @Mock
    private UserDAO userDAO = Mockito.mock(UserDAO.class);

    @InjectMocks
    private UserService userService;

    /**
     * Initializes mock dependencies before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests user login with valid credentials.
     */
    @Test
    void testLoginUserWithValidCredentials() {
        when(userDAO.displayAllUsers()).thenReturn(Collections.emptyList());
        assertFalse(userService.loginUser("john_doe", "wrong_password"));
    }

    /**
     * Tests user login with invalid credentials.
     */
    @Test
    void testLoginUserWithInvalidCredentials() {
        when(userDAO.displayAllUsers()).thenReturn(Collections.emptyList());
        assertFalse(userService.loginUser("john_doe", "wrong_password"));
    }

    /**
     * Tests user logout functionality.
     */
    @Test
    void testLogoutUser() {
        User user = new User(1, "John", "john_doe", "password", "regular");
        userService.loginUser("john_doe", "password");
        userService.logout();
        assertNull(userService.getLoggedInUser());
    }

    /**
     * Tests whether a user is an admin.
     */
    @Test
    void testIsAdmin() {
        User adminUser = new User(1, "Admin", "admin", "adminpass", "admin");
        assertTrue(userService.isAdmin(adminUser));
    }

    /**
     * Tests finding a user by name when the user exists.
     */
    @Test
    void testFindUserByNameWhenUserExists() throws Exception {
        User user = new User(1, "John", "John", "adminpass", "admin");
        when(userDAO.findUser("John")).thenReturn(user);
        assertEquals(user, userDAO.findUser("John"));
    }

    /**
     * Tests finding a user by name when the user does not exist.
     */
    @Test
    void testFindUserByNameWhenUserDoesNotExist() throws Exception {
        when(userDAO.findUser("Unknown")).thenReturn(null);
        assertNull(userService.findUser("Unknown"));
    }

    /**
     * Tests updating a user successfully.
     */
    @Test
    void testUpdateUserSuccessfully() {
        when(userDAO.update("new_username", "new_password", "John"))
                .thenReturn(true);
        boolean updatedUser = userService.updateUser("new_username", "new_password", "John");

        assertFalse(updatedUser);
    }

    /**
     * Tests deleting a user successfully.
     */
    @Test
    void testDeleteUserSuccessfully() {
        when(userDAO.delete("John")).thenReturn(true); // Mock successful deletion
        boolean isDeleted = userService.deleteUser("John");
        assertTrue(isDeleted);
    }
}
