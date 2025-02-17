package org.prog3.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user of the application. Each user has a unique identifier, a username, a password,
 * and a list of shopping lists associated with the user.
 */
public class User {

    private int id;

    private String username;

    private String name;

    private String password;

    private List<ShoppingList> shoppingLists;

    private String role;

    /**
     * Default constructor. Initializes the shoppingLists list as an empty ArrayList.
     */
    public User() {

    }


    /**
     *
     *
     * @param id
     * @param username
     * @param name
     * @param password
     * @param role
     */
    public User(int id, String username, String name, String password, String role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public User(int id, String username, String name, String role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
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
     *
     * @return
     */
    public String getName() {
        return name;
    }


    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
     *
     *
     * @return
     */
    public String getRole() {
        return role;
    }


    /**
     *
     *
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
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