package org.prog3.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user of the application.
 * Each user has a unique identifier, a username, a password,
 * and a list of shopping lists associated with the user.
 */
public class User {

    // Unique identifier for the user (e.g., from the database)
    private int id;

    // The username for login purposes
    private String username;

    // The password for the user (should be stored securely in a production system)
    private String password;

    // A list of shopping lists owned by the user
    private List<ShoppingList> shoppingLists;

    /**
     * Default constructor.
     * Initializes the shoppingLists list as an empty ArrayList.
     */
    public User() {
        this.shoppingLists = new ArrayList<>();
    }

    /**
     * Parameterized constructor that accepts all fields.
     *
     * @param id            the unique identifier for the user
     * @param username      the username of the user
     * @param password      the password of the user
     * @param shoppingLists the list of shopping lists associated with the user
     */
    public User(int id, String username, String password, List<ShoppingList> shoppingLists) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.shoppingLists = shoppingLists;
    }

    /**
     * Parameterized constructor without shoppingLists.
     * Creates an empty list for shoppingLists.
     *
     * @param id       the unique identifier for the user
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(int id, String username, String password) {
        this(id, username, password, new ArrayList<>());
    }

    /**
     * Gets the unique identifier of the user.
     *
     * @return the user's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     *
     * @return the user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for the user.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the user.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the list of shopping lists associated with the user.
     *
     * @return the list of shopping lists
     */
    public List<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    /**
     * Sets the shopping lists for the user.
     *
     * @param shoppingLists the list of shopping lists to set
     */
    public void setShoppingLists(List<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }


}