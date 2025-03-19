package org.prog3.services;

import org.prog3.dao.UserDAO;
import org.prog3.models.User;
import java.util.List;
import java.util.Optional;

/**
 * Service class for handling business logic related to users.
 * This class delegates the data access operations to the UserDAO.
 */
public class UserService {

    private final UserDAO userDAO = new UserDAO();
    private User loggedInUser = null;


    /**
     * Attempts to log in a user by validating the username and password
     *
     * @param username the username to log in with. Must not be empty or null
     * @param password the password to log in with. Must not be empty or null
     * @return true if login was successful and false otherwise
     */
    public boolean loginUser (String username, String password) {
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            System.out.println("❌ Username or password cannot be empty or null");
            return false;
        }


        List<User> users = userDAO.displayAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("✅ Login successful! Welcome, " + loggedInUser.getUsername());
                return true;
            }
        }
        System.out.println("❌ Invalid username or password.");
        return false;
    }


    /**
     * Retrieves the currently logged-in user.
     *
     * @return the logged-in user, or null if no user is logged in.
     */
    public User getLoggedInUser() {
        return loggedInUser;
    }


    /**
     * Logs out the current user.
     */
    public void logout() {
        loggedInUser = null;
        System.out.println("✅ Logged out successfully.");
    }


    /**
     * Creates a new user in the system.
     *
     * @param name the full name of the user. Must not be empty or null.
     * @param username the unique username. Must not be null empty.
     * @param password true if the user is created successfully and false otherwise.
     */
    public boolean createUser (String name, String username, String password) {
        if ((name == null) || name.trim().isEmpty() ||
                (username == null) || username.trim().isEmpty() ||
                (password == null) || password.trim().isEmpty()  ){
            System.out.println("❌ Name, username or password cannot be empty or null.");
            return false;
        }

        List<User> existingUsers = userDAO.displayAllUsers();
        Optional<User> existingUser = existingUsers.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst();

        if (existingUser.isPresent()) {
            System.out.println("⚠️ Username already exists. Please choose a different one.");
        }

        String role;
        if (existingUsers.isEmpty()) {
            role = "admin";  // First user is automatically an admin
            System.out.println("✅ First user detected. Assigned role: ADMIN.");
        } else {
            role = "regular"; // Default role for all other users
        }

        userDAO.create(name, username, password, role);
        return true;
    }


    /**
     * Checks if a user has an admin role.
     *
     * @param user the User to check. Must not be null.
     * @return true if the user is an admin and false otherwise.
     */
    public boolean isAdmin(User user) {
        if (user == null) {
            System.out.println("❌ User cannot be null.");
            return false;
        }

        return "admin".equalsIgnoreCase(user.getRole());
    }


    /**
     * Retrieves a user by their name.
     *
     * @param username the username of the user to retrieve. Must not be null or empty.
     * @return the User object if found or null if the user does not exist
     */
    public User findUser (String username) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("❌ Invalid username. Please enter a valid username.");
        }

        return userDAO.findUser(username);
    }


    /**
     * Retrieves all users in the system.
     *
     * @return a List of all User objects.
     */
    public List<User> getAllUsers() {
        return userDAO.displayAllUsers();
    }


    /**
     * Updates an existing user's details.
     *
     * @param username the new username. Must not be empty or null.
     * @param password the new password. Must not be empty or null.
     * @param name the name of the user to update. Must not be empty or null.
     * @return true if the update was successful.
     */
    public boolean updateUser(String username, String password, String name) {
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                name == null || name.trim().isEmpty()) {
            System.out.println("❌ Username, password, or name cannot be null or empty.");
            return false;
        }

        return userDAO.update(username, password, name);
    }


    /**
     * Deletes a user by username.
     *
     * @param username the username of the user. Must not be null or empty.
     * @return true if the deletion was successful.
     */
    public boolean deleteUser (String username) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("❌ Username cannot be null or empty.");
            return false;
        }

        try {
            userDAO.delete(username);
        } catch (Exception e) {
            System.out.println("❌ Error deleting user: " + e.getMessage());
        }
        return true;
    }


    /**
     * Promotes a regular user to admin status. Only a logged-in admin can perform this operation.
     *
     * @param username the username of the user to promote. Must not be null or empty.
     * @return true if the promotion is successful and false otherwise.
     */
    public boolean promoteUserToAdmin(String username) {
        if (loggedInUser == null || !isAdmin(loggedInUser)) {
            System.out.println("❌ Only an admin can promote users.");
            return false;
        }
        return userDAO.promoteUserToAdmin(username);
    }

}

