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

    // Instance of the UserDAO used for interacting with the database
    private UserDAO userDAO = new UserDAO();
    private User loggedInUser = null;


    public boolean loginUser(String username, String password) {
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

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void logout() {
        loggedInUser = null;
        System.out.println("✅ Logged out successfully.");
    }


    /**
     * Creates a new user in the system. This method contains the business logic for creating a new user,
     * such as checking for duplicate usernames (if applicable).
     *
     * @param name
     * @param username
     * @param password
     */
    public boolean createUser (String name, String username, String password) {
        if ((name == null) || (username == null) || username.trim().isEmpty() || (password == null) || password.trim().isEmpty()  ){
            System.out.println("name or password cannot be null.");
            return false;
        }

        List<User> existingUsers = userDAO.displayAllUsers();
        Optional<User> existingUser = existingUsers.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst();

        if (existingUser.isPresent()) {
            System.out.println("Username already exists. Choose another.");
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
     *
     * @param user
     * @return
     */
    public boolean isAdmin(User user) {
        return "admin".equalsIgnoreCase(user.getRole());
    }


    /**
     * Retrieves a user by its unique id.
     * This method allows fetching a user based on their database id.
     *
     * @param userName the id of the user to retrieve
     * @return the User object if found, or null if the user does not exist
     */
    public User findUser(String userName) throws Exception {
        if (userName == null) {
            System.out.println("Invalid username");
        }
        try {
            return userDAO.findUser(userName);
        } catch (Exception e) {
            throw new Exception("Error returning User: " + e.getMessage(), e);
        }
    }


    /**
     * Retrieves all users in the system.
     * This method allows fetching a list of all users from the database.
     *
     * @return a List of all User objects in the system
     */
    public List<User> getAllUsers() {
        return userDAO.displayAllUsers();
    }


    /**
     * Updates an existing user in the system.
     * This method contains the logic for updating user information, such as changing the username or password.
     *
     * @param username the new username for the user
     * @param password the new password for the user
     * @param name the name of the user to update
     * @return true if the user was successfully updated, or false if the update failed
     */
    public boolean updateUser(String username, String password, String name) {
        return userDAO.update(username, password, name);
    }


    /**
     *
     * @param username
     */
    public boolean deleteUser(String username) {
        try {
            userDAO.delete(username);
        } catch (Exception e) {
            System.out.println("❌ Error deleting user: " + e.getMessage());
        }
        return true;
    }

    public boolean promoteUserToAdmin(String username) {
        // Check that the logged-in user is an admin
        if (loggedInUser == null || !isAdmin(loggedInUser)) {
            System.out.println("❌ Only an admin can promote users.");
            return false;
        }
        return userDAO.promoteUserToAdmin(username);
    }

}

