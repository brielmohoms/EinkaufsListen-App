package org.prog3.controllers;

import org.prog3.models.User;
import org.prog3.services.UserService;

import java.util.List;
import java.util.Scanner;

/**
 * Controller class for handling user-related operations.
 */
public class UserController {

    private static final Scanner scanner = new Scanner(System.in);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user by receiving the username and password.
     */
    public void createUser() {
        // Ask the user for username and password
        System.out.println("\nEnter your name: ");
        String name = scanner.nextLine();

        System.out.println("\nEnter a unique username: ");
        String username = scanner.nextLine();

        System.out.println("\nEnter password: ");
        String password = scanner.nextLine();

        if (userService.createUser(name, username, password)) {
            System.out.println("✅ User registered successfully!");
        } else {
            System.out.println("❌ Failed to register. Please try again.");
        }
    }


    /**
     *
     * @return
     */
    public boolean loginUser () {
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();

        System.out.print("\nEnter password: ");
        String password = scanner.nextLine();

        boolean success = userService.loginUser(username, password);
        if (!success) {
            System.out.println("❌ Login failed. Try again.");
            return false;
        }
        return true;
    }

    public void logoutUser () {
        userService.logout();
    }

    /**
     * Deletes an existing user
     */
    public void deleteUser () {
        System.out.print("\nEnter the username to the user to delete: ");
        String username = scanner.nextLine();

        if (userService.deleteUser(username)) {
            System.out.println(" ✅ User deleted successfully.");
        } else {
            System.out.println("❌ Failed to delete user. Please try again.");
        }
    }

    /**
     * Displays all users in the system.
     */
    public void displayAllUsers () {
        // Retrieve the list of all users from UserService
        List<User> users = userService.getAllUsers();

        // Display the users or a message if none are found
        if (users.isEmpty()) {
            System.out.println("⚠️ No users found.");
        } else {
            System.out.println("\n\033[1;33m---------------- Registered Users ----------------\033[0m");
            for (User user : users) {
                System.out.println("Name: " + user.getName() + "| Username: " + user.getUsername() + "| Role: " + user.getRole());
            }
        }
    }

    /**
     * Retrieves a user by their name.
     */
    public void findUser () {
        System.out.println("\nEnter user name: ");
        String name = scanner.nextLine();

        // Fetch the user by ID and display the result
        try {
            User user = userService.findUser(name);
            if (user!= null) {
                System.out.println("✅ User found: Name: " + user.getName() + ", Username: " + user.getUsername());
            } else {
                System.out.println("⚠️ User not found.");
            }
        } catch (Exception e) {
            System.err.println("❌ Error retrieving user: " + e.getMessage());
        }
    }

    /**
     * Updates an existing user's username and password.
     * Delegates the update logic to the UserService.
     */
    public void updateUser () {
        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();
        System.out.println("\nEnter new username: ");
        String username = scanner.next();
        System.out.println("\nEnter new password: ");
        String password = scanner.next();

        boolean success = userService.updateUser(username, password, name);
        if (success) {
            System.out.println("✅ User successfully updated!");
        } else {
            System.out.println("❌ Failed to update user. Invalid name or username. Please try again!");
        }
    }


    /**
     *
     */
    public void promoteUserToAdmin () {
        System.out.print("Enter username to promote to admin: ");
        String username = scanner.next();

        boolean success = userService.promoteUserToAdmin(username);
        if (success) {
            System.out.println("✅ User " + username + " is now an admin!");
        } else {
            System.out.println("❌ Failed to promote user. Either user not found or already an admin.");
        }
    }

    /**
     *
     * @return
     */
    public boolean isAdmin () {
        User currentUser = userService.getLoggedInUser();
        return currentUser != null && userService.isAdmin(currentUser);
    }

}

