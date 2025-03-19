package org.prog3.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link User} class.
 */
class UserTest {

    private User user;

    /**
     * Sets up a new user instance before each test.
     */
    @BeforeEach
    void setUp() {
        user = new User(1, "john_doe", "John", "password", "regular");
    }

    /**
     * Tests getting and setting the user ID.
     */
    @Test
    void testGetAndSetId() {
        user.setId(2);
        assertEquals(2, user.getId());
    }

    /**
     * Tests getting and setting the username.
     */
    @Test
    void testGetAndSetUsername() {
        user.setUsername("jane_doe");
        assertEquals("jane_doe", user.getUsername());
    }

    /**
     * Tests getting and setting the name.
     */
    @Test
    void testGetAndSetName() {
        user.setName("Jane");
        assertEquals("Jane", user.getName());
    }

    /**
     * Tests getting and setting the password.
     */
    @Test
    void testGetAndSetPassword() {
        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());
    }

    /**
     * Tests getting and setting the role.
     */
    @Test
    void testGetAndSetRole() {
        user.setRole("admin");
        assertEquals("admin", user.getRole());
    }

    /**
     * Tests default constructor and setting values manually.
     */
    @Test
    void testDefaultConstructor() {
        User newUser = new User();
        newUser.setId(3);
        newUser.setUsername("test_user");
        newUser.setName("Test");
        newUser.setPassword("testpass");
        newUser.setRole("regular");

        assertEquals(3, newUser.getId());
        assertEquals("test_user", newUser.getUsername());
        assertEquals("Test", newUser.getName());
        assertEquals("testpass", newUser.getPassword());
        assertEquals("regular", newUser.getRole());
    }
}

