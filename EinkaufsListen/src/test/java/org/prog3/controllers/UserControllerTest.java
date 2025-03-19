package org.prog3.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.prog3.models.User;
import org.prog3.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link UserController} class.
 */
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Scanner scanner;

    @InjectMocks
    private UserController userController;

    /**
     * Initializes mocks and the user controller before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService, scanner);
    }

    /**
     * Tests user creation.
     */
    @Test
    void testCreateUser() throws Exception {
        when(userService.createUser("John", "john_doe", "password")).thenReturn(true);
        assertDoesNotThrow(() -> userController.createUser());
    }

    /**
     * Tests successful user login.
     */
    @Test
    void testLoginUserSuccess() {
        when(scanner.nextLine()).thenReturn("john_doe", "password");
        when(userService.loginUser("john_doe", "password")).thenReturn(true);
        boolean result = userController.loginUser();
        assertTrue(result);
        verify(userService, times(1)).loginUser("john_doe", "password");
    }

    /**
     * Tests failed user login.
     */
    @Test
    void testLoginUserFailure() {
        when(scanner.nextLine()).thenReturn("john_doe", "wrongpassword");
        when(userService.loginUser("john_doe", "wrongpassword")).thenReturn(false);
        boolean result = userController.loginUser();
        assertFalse(result);
        verify(userService, times(1)).loginUser("john_doe", "wrongpassword");
    }

    /**
     * Tests user logout.
     */
    @Test
    void testLogoutUser() {
        doNothing().when(userService).logout();
        assertDoesNotThrow(() -> userController.logoutUser());
        verify(userService, times(1)).logout();
    }

    /**
     * Tests deleting a user.
     */
    @Test
    void testDeleteUser() throws Exception {
        when(scanner.nextLine()).thenReturn("John");
        when(scanner.nextLine()).thenReturn("john");
        userController.deleteUser();
        verify(userService, never()).deleteUser("John");
    }

    /**
     * Tests displaying all users when users exist.
     */
    @Test
    void testDisplayAllUsersWithUsers() {
        List<User> users = Arrays.asList(
                new User(1, "john_doe", "password", "regular"),
                new User(2, "jane_doe", "password", "admin")
        );
        when(userService.getAllUsers()).thenReturn(users);
        assertDoesNotThrow(() -> userController.displayAllUsers());
        verify(userService, times(1)).getAllUsers();
    }

    /**
     * Tests displaying all users when no users exist.
     */
    @Test
    void testDisplayAllUsersWithNoUsers() {
        when(userService.getAllUsers()).thenReturn(List.of());
        assertDoesNotThrow(() -> userController.displayAllUsers());
        verify(userService, times(1)).getAllUsers();
    }

    /**
     * Tests finding a user by name when the user exists.
     */
    @Test
    void testFindUserByNameSuccess() throws Exception {
        User user = new User(1, "john_doe", "password123", "regular");
        when(userService.findUser(anyString())).thenReturn(user);
        assertDoesNotThrow(() -> userController.findUser());
    }

    /**
     * Tests finding a user by name when the user does not exist.
     */
    @Test
    void testFindUserByNameFailure() throws Exception {
        when(userService.findUser(anyString())).thenReturn(null);
        assertDoesNotThrow(() -> userController.findUser());
    }

    /**
     * Tests updating a user successfully.
     */
    @Test
    void testUpdateUserSuccess() {
        when(userService.updateUser(anyString(), anyString(), anyString())).thenReturn(true);
        assertDoesNotThrow(() -> userController.updateUser());
    }

    /**
     * Tests updating a user failure scenario.
     */
    @Test
    void testUpdateUserFailure() {
        when(userService.updateUser(anyString(), anyString(), anyString())).thenReturn(false);
        assertDoesNotThrow(() -> userController.updateUser());
    }
}
