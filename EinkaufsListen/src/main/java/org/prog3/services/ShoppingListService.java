package org.prog3.services;

import org.prog3.dao.ShoppingListDAO;
import org.prog3.models.ShoppingList;

import java.sql.SQLException;
import java.util.List;


public class ShoppingListService {

    private static ShoppingListDAO shoppingListDao;


    static {
        try {
            shoppingListDao = new ShoppingListDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructor to inject DAO dependency
     *
     * @param shoppingListDao
     */
    /**public ShoppingListService(ShoppingListDAO shoppingListDao) {
        this.shoppingListDao = shoppingListDao;
    }
     **/


    /**
     * Retrieves all shopping lists.
     */
    public static List<ShoppingList> getAllShoppingLists() throws Exception {
        return shoppingListDao.getAllShoppingLists();
    }



    /**
     * Adds a new shopping list after validation.
     */
    public void addShoppingList(int userId ,String name ) throws Exception {
        if ((name == null) || name.trim().isEmpty()){
            throw new IllegalArgumentException("Shopping list or items cannot be null.");
        }
    ShoppingList shoppingList = new ShoppingList(userId, name );
        shoppingListDao.addShoppingList(shoppingList);
    }


    /**
     * Deletes a shopping list by ID.
     *
     * @param shoppingListId the ID of the shopping list to delete.
     * @throws IllegalArgumentException if the ID is invalid.
     * @throws Exception                if an error occurs during data deletion.
     */
    public void deleteShoppingListById(int shoppingListId) throws Exception {
        if (shoppingListId <= 0) {
            throw new IllegalArgumentException("Invalid shopping list ID.");
        }
        try {
            shoppingListDao.deleteShoppingList(shoppingListId);
        } catch (Exception e) {
            throw new Exception("Error deleting shopping list: " + e.getMessage(), e);
        }
    }
}

