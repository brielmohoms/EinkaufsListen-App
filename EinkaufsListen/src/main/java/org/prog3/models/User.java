package org.prog3.models;

import java.util.LinkedList;
import java.util.List;


/**
 * Represents a user in the application.
 * A user can have multiple shopping lists.
 */
public class User {

    // A unique identifier for the user (e.g., from the database)
    private int id;

    // The username (login name) for this user
    private String username;


    // A collection of shopping lists owned by this user
    private List<ShoppingList> shoppingLists;


    /**
     * Constructor with parameters for id, username, and password.
     * Also initializes shoppingLists to an empty list.
     *
     * @param id       the unique identifier for this user
     * @param username the username
     */
    public User(int id, String username, String password) {
        // Assign the provided values
        this.id = id;
        this.username = username;

    }

    // Getters and setters

    /**
     * @return the unique identifier of this user
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for this user.
     *
     * @param id the identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the username of this user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for this user.
     *
     * @param username the new username to assign
     */
    public void setUsername(String username) {
        this.username = username;
    }



}
