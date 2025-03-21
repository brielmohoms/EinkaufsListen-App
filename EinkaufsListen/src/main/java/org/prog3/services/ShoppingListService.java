package org.prog3.services;

import org.prog3.dao.ShoppingListDAO;
import org.prog3.models.ShoppingList;

import java.util.List;

/**
 * Service class responsible for handling business logic related to shopping lists.
 *
 * <p>
 * This class acts as an intermediary between the data access layer {@link ShoppingListDAO} and higher-level components.
 * It provides methods to add, delete, and retrieve shopping lists while ensuring that proper validations and user authorizations are enforced.
 * </p>
 */

public class ShoppingListService {

    private final ShoppingListDAO shoppingListDAO;
    private final UserService userService;

    /**
     * Constructs a new ShoppingListService.
     *
     * @param shoppingListDAO the data access object for shopping list operations
     * @param userService the service that handles user authentication and authorization, ensuring that
     *                    only authorized users can access or modify their shopping lists
     */
    public ShoppingListService(ShoppingListDAO shoppingListDAO, UserService userService) {
        this.shoppingListDAO = shoppingListDAO;
        this.userService = userService;
    }

    /**
     * Retrieves all shopping lists associated with the currently logged-in user.
     *
     * @return a list of shopping lists for the current user
     */
    public List<ShoppingList> getAllShoppingLists() {
        String username = userService.getLoggedInUser().getUsername();
        return shoppingListDAO.getAllShoppingLists(username);
    }


    /**
     * Checks if a shopping list exists by its name.
     *
     * @param shoppingListName the name of the shopping list to check.
     * @return true if the shopping list exists and false otherwise.
     */
    public boolean shoppingListExists(String shoppingListName) {
        return shoppingListDAO.shoppingListExists(shoppingListName);
    }


    /**
     * Adds a new shopping list after validating the provided name.
     *
     * @param name the name of the new shopping list
     */
    public void addShoppingList (String name) {
        if ((name == null) || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Shopping list or items cannot be null.");
        }
        if (!name.matches("^[A-Za-zÀ-ÿ ]+$")) {
            throw new IllegalArgumentException("the shopping list name must contain only letters.");
        }
        ShoppingList shoppingList = new ShoppingList(name);
        String username = userService.getLoggedInUser().getUsername();
        shoppingListDAO.addShoppingList(shoppingList, username);
    }


    /**
     * Deletes a shopping list by its name.
     *
     * @param shoppingListName the name of the shopping list to delete.
     */
    public void deleteShoppingListByName(String shoppingListName) {
        if (shoppingListName == null || shoppingListName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid shopping list name.");
        }

        if (!shoppingListDAO.shoppingListExists(shoppingListName)) {
            throw new IllegalArgumentException("Error: Shopping list does not exist.");
        }

        try {
            shoppingListDAO.deleteShoppingList(shoppingListName);
        } catch (Exception e) {
            System.err.println("Error deleting shopping list." + e.getMessage());
        }
    }


    /**
     * Retrieves the total price of the items in a specified shopping list.
     *
     * @param shoppingListName the name of the shopping list
     * @return the total price of the shopping list; returns 0.0 if the shopping list does not exist or if an error occurs
     */
    public double getTotalPrice(String shoppingListName) {
        if (!shoppingListDAO.shoppingListExists(shoppingListName)) {
            System.out.println("Error: Shopping list does not exist.");
        }
        return shoppingListDAO.getTotalPrice(shoppingListName);
    }

}

