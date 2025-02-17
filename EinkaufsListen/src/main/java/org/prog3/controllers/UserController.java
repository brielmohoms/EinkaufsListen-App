package org.prog3.controllers;

import org.prog3.models.User;
import org.prog3.services.UserService;

import java.util.List;
import java.util.Scanner;

/**
 * Controller class for handling user-related operations.
 * Receives requests (from a UI, API, or CLI) and delegates actions to the UserService.
 */
public class UserController {

    // Scanner instance to read user input
    private static final Scanner scanner = new Scanner(System.in);
    private final UserService userService = new UserService();


    /**
     * Creates a new user by receiving the username and password.
     * Delegates to the UserService to handle the creation logic.
     */
    public void createUser() {
        // Ask the user for username and password
        System.out.println("Enter username: ");
        String username = scanner.next();
        System.out.println("Enter password: ");
        String password = scanner.next();
        scanner.nextLine();

        // Delegate to the UserService to create the user
        try {
            userService.createUser(username, password);
        } catch (Exception e) {
            System.err.println("Error creating user: " + e.getMessage());
        }
    }

    public boolean loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        scanner.nextLine();

        boolean success = userService.loginUser(username, password);
        if (!success) {
            System.out.println("❌ Login failed. Try again.");
            return false;
        }
        return true;
    }

    public void logoutUser() {
        userService.logout();
    }

    /**
     * Deletes a user by their ID.
     * Delegates to the UserService to handle the deletion logic.
     *
     * @throws Exception if an error occurs during user deletion
     */
    public void deleteUser() throws Exception {
        System.out.print("Enter user ID to delete: ");
        int id = scanner.nextInt();

        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("User deleted successfully.");
    }

    /**
     * Displays all users in the system.
     * Delegates to the UserService to retrieve and display the list of users.
     */
    public void displayAllUsers() {
        // Retrieve the list of all users from UserService
        List<User> users = userService.getAllUsers();

        // Display the users or a message if none are found
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("List of users:");
            for (User user : users) {
                System.out.println("ID: " + user.getId() + ", Username: " + user.getUsername());
            }
        }
    }

    /**
     * Retrieves a user by their ID.
     * Delegates to the UserService to fetch the user.
     */
    public void findUserById() {
        System.out.println("Enter user ID: ");
        int id = scanner.nextInt();

        // Fetch the user by ID and display the result
        try {
            User user = userService.getUserById(id);
            if (user!= null) {
                System.out.println("User found: ID: " + user.getId() + ", Username: " + user.getUsername());
            } else {
                System.out.println("User not found.");
            }
        } catch (Exception e) {
            System.err.println("Error retrieving user: " + e.getMessage());
        }
    }

    /**
     * Updates an existing user's username and password.
     * Delegates the update logic to the UserService.
     */
    public void updateUser() {
        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        System.out.println("Enter new username: ");
        String username = scanner.next();
        System.out.println("Enter new password: ");
        String password = scanner.next();
        boolean success = userService.updateUser(username,password,id);
        if (success) {
            System.out.println("User successfully updated!");
        } else {
            System.out.println("Failed to update user.");
        }
    }
    }

