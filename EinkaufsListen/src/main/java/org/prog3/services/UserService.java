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
        List<User> users = userDAO.findAll();
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
     * Creates a new user in the system.
     * This method contains the business logic for creating a new user, such as checking for duplicate usernames (if applicable).
     *
     * @param username the username for the new user
     * @param password the password for the new user
     */

    public void createUser(String username, String password ) throws Exception {
        if ((username == null) || username.trim().isEmpty() || (password == null) || password.trim().isEmpty()  ){
            throw new IllegalArgumentException("Shopping list or items cannot be null.");
        }

        List<User> existingUsers = userDAO.findAll();
        Optional<User> existingUser = existingUsers.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst();

        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Username already exists. Choose another.");
        }

        userDAO.create(username,password);
        System.out.println("✅ User registered successfully!");
    }

    /**
     * Retrieves a user by its unique id.
     * This method allows fetching a user based on their database id.
     *
     * @param id the id of the user to retrieve
     * @return the User object if found, or null if the user does not exist
     */
    public User getUserById(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid User ID.");
        }
        try {
            return userDAO.findById(id);
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
        return userDAO.findAll();
    }


    /**
     * Updates an existing user in the system.
     * This method contains the logic for updating user information, such as changing the username or password.
     *
     * @param username the new username for the user
     * @param password the new password for the user
     * @param id the id of the user to update
     * @return true if the user was successfully updated, or false if the update failed
     */
    public boolean updateUser(String username, String password, int id) {
        return userDAO.update(username, password, id);
    }


    /**
     *
     * @param id
     * @throws Exception
     */
    public void deleteUser(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid shopping list ID.");
        }
        try {
            userDAO.delete(id);
        } catch (Exception e) {
            throw new Exception("Error deleting shopping list: " + e.getMessage(), e);
        }
    }
}

