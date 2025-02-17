package org.prog3.services;

import org.prog3.dao.ShoppingListDAO;
import org.prog3.models.ShoppingList;

import java.util.List;


public class ShoppingListService {

    private ShoppingListDAO shoppingListDao = new ShoppingListDAO();


    /**
     * Retrieves all shopping lists.
     */
    public List<ShoppingList> getAllShoppingLists() {
        return shoppingListDao.getAllShoppingLists();
    }

    /**
     * Checks if a shopping list exists by name.
     *
     * @param shoppingListName The name of the shopping list.
     * @return true if the shopping list exists, false otherwise.
     */
    public boolean shoppingListExists(String shoppingListName) {
        return shoppingListDao.shoppingListExists(shoppingListName);
    }

    /**
     * Adds a new shopping list after validation.
     */
    public void addShoppingList(String userName ,String name ) {
        if ((name == null) || name.trim().isEmpty()){
            throw new IllegalArgumentException("Shopping list or items cannot be null.");
        }
        ShoppingList shoppingList = new ShoppingList(userName, name );
        shoppingListDao.addShoppingList(shoppingList);
    }


    /**
     * Deletes a shopping list by ID.
     *
     * @param shoppingListId the ID of the shopping list to delete.
     * @throws IllegalArgumentException if the ID is invalid.
     */
    public void deleteShoppingListById(int shoppingListId) {
        if (shoppingListId <= 0) {
            throw new IllegalArgumentException("Invalid shopping list ID.");
        }
        try {
            shoppingListDao.deleteShoppingList(shoppingListId);
        } catch (Exception e) {
            System.err.println("Error deleting shopping list: " + e.getMessage());
        }
    }
}

