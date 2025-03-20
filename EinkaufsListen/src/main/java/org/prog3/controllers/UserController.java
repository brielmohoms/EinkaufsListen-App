package org.prog3.controllers;

import org.prog3.models.User;
import org.prog3.services.UserService;

import java.util.List;
import java.util.Scanner;

/**
 * Controller class responsible for handling user-related operations.
 *
 * <p>
 * This class manages user interactions by reading inputs from the console and delegating user-related operations to the {@link UserService}.
 * It covers functionalities such as user registration, login, logout, deletion, retrieval, update, and promotion.
 * </p>
 */
public class UserController {

    private final Scanner scanner ;
    private final UserService userService;


    /**
     * Constructs a new UserController with the specified user service.
     *
     * @param userService the service handling user operations
     */
    public UserController(UserService userService,Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }


    /**
     * Creates a new user by prompting for the user's name, username, and password.
     *
     * <p>
     * Delegates the creation to the {@link UserService}. Displays a success message if registration is successful,
     * or an error message otherwise.
     * </p>
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
     * Logs in a user by prompting for the username and password.
     *
     * <p>
     * Delegates authentication to the {@link UserService} and displays an error message if login fails.
     * </p>
     *
     * @return true if login is successful and false otherwise
     */
    public boolean loginUser () {
        System.out.print("\nEnter your username: ");
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


    /**
     * Logs out the current user.
     *
     * <p>
     * Delegates the logout process to the {@link UserService}.
     * </p>
     */
    public void logoutUser () {
        userService.logout();
    }


    /**
     * Deletes an existing user by prompting for the username.
     *
     * <p>
     * If deletion is successful, a confirmation message is displayed. Otherwise, an error message is shown.
     * </p>
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
     * Displays all registered users.
     *
     * <p>
     * Retrieves the list of users from the {@link UserService} and prints their details.
     * If no users are found, a warning message is displayed.
     * </p>
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
     * Finds and displays a user by their name.
     *
     * <p>
     * Prompts the user for a name, then delegates the search to the {@link UserService}.
     * If found, displays the user's details; otherwise, notifies that the user was not found.
     * </p>
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
     *
     * <p>
     * Prompts the user for the current name, new username, and new password,
     * then delegates the update process to the {@link UserService}. A success or error message is displayed based on the outcome.
     * </p>
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
     * Promotes a user to an administrator role.
     *
     * <p>
     * Prompts for the username to be promoted, and delegates the operation to the {@link UserService}.
     * A confirmation or error message is then displayed.
     * </p>
     */
    public void promoteUserToAdmin () {
        System.out.print("Enter username to promote to admin: ");
        String username = scanner.nextLine();

        boolean success = userService.promoteUserToAdmin(username);
        if (success) {
            System.out.println("✅ User " + username + " is now an admin!");
        } else {
            System.out.println("❌ Failed to promote user. Either user not found or already an admin.");
        }
    }


    /**
     * Checks if the currently logged-in user has administrator privileges.
     *
     * <p>
     * Delegates the check to the {@link UserService} based on the logged-in user's information.
     * </p>
     *
     * @return true if the current user is an administrator and false otherwise
     */
    public boolean isAdmin () {
        User currentUser = userService.getLoggedInUser();
        return currentUser != null && userService.isAdmin(currentUser);
    }

}

