package org.prog3.services;

import org.prog3.dao.ShoppingListDAO;
import org.prog3.models.ShoppingList;

import java.util.List;


public class ShoppingListService {

    private ShoppingListDAO shoppingListDAO = new ShoppingListDAO();


    /**
     * Retrieves all shopping lists.
     */
    public List<ShoppingList> getAllShoppingLists() {
        return shoppingListDAO.getAllShoppingLists();
    }


    /**
     * Checks if a shopping list exists by name.
     *
     * @param shoppingListName The name of the shopping list.
     * @return true if the shopping list exists, false otherwise.
     */
    public boolean shoppingListExists(String shoppingListName) {
        return shoppingListDAO.shoppingListExists(shoppingListName);
    }


    /**
     * Adds a new shopping list after validation.
     */
    public void addShoppingList(String name ) {
        if ((name == null) || name.trim().isEmpty()){
            throw new IllegalArgumentException("Shopping list or items cannot be null.");
        }
        ShoppingList shoppingList = new ShoppingList(name);
        shoppingListDAO.addShoppingList(shoppingList);
    }


    /**
     * Deletes a shopping list by ID.
     *
     * @param shoppingListName the ID of the shopping list to delete.
     * @throws IllegalArgumentException if the ID is invalid.
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
     *
     *
     * @param shoppingListName
     * @return
     */
    public double getTotalPrice(String shoppingListName) {
        if (!shoppingListDAO.shoppingListExists(shoppingListName)) {
            System.out.println("Error: Shopping list does not exist.");
        }
        return shoppingListDAO.getTotalPrice(shoppingListName);
    }

}

