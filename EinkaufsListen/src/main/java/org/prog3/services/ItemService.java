package org.prog3.services;

import org.prog3.dao.ItemDAO;
import org.prog3.models.Item;

import java.util.List;

/**
 * Service for managing item related operations
 */
public class ItemService {

    private final ItemDAO itemDAO = new ItemDAO();

    /**
     * Adds a new item
     *
     * @param shoppingListId the shopping list id
     * @param name the item name
     * @param category the item category
     * @param price the item price
     * @param quantity the item quantity
     */
    public void addItem(int shoppingListId, String name, String category, double price, double quantity) {
        if (price <= 0 || quantity <= 0) {
            throw new IllegalArgumentException("Price and quantity cannot be zero or negative");
        } else {
            Item item = new Item(name, category, price, quantity, shoppingListId);
            itemDAO.saveItem(item);
        }
    }

    /**
     * Deletes an item by its name
     *
     * @param shoppingListId the shopping list ID
     * @param name the item name
     */
    public boolean deleteItemByName (int shoppingListId, String name){
        boolean deleted = itemDAO.deleteByName(shoppingListId, name);
        if (deleted) {
            System.out.println("✅ Item " + name + " deleted successfully.");
        } else {
            System.out.println("Item " + name + " not found.");
        }
        return deleted;
    }

    /**
     * Finds an item by its name
     *
     * @param name the item name
     * @return the item found
     */
    public Item findItemByName (int shoppingListId, String name) {
        return itemDAO.findByName(shoppingListId, name);
    }

    /**
     * Updates an item quantity after finding by its name
     *
     * @param name the item name
     * @param newQuantity the item quantity
     */
    public boolean updateItemQuantity (int shoppingListId, String name, double newQuantity) {
        if(newQuantity == 0){
            System.out.println("Invalid quantity");
            return false;
        }

        boolean item = itemDAO.updateQuantity(shoppingListId, name, newQuantity);

        if(item) {
            System.out.println("Quantity updated for " + name + " to " + newQuantity);
        } else {
            System.out.println("Item not found or update failed");
        }
        return item;
    }

    /**
     * Gets all the items
     *
     * @param shoppingListId the shopping list ID
     * @return a list of all the items
     */
    public List<Item> getAllItems (int shoppingListId) {
        return itemDAO.findAllItemsByShoppingListId(shoppingListId);
    }

    /**
     * deletes all the items of a specific shopping list
     *
     * @param shoppingListId the shopping list ID
     */
    public void deleteAllItemsOfAShoppingList(int shoppingListId) {
        List<Item> items = itemDAO.findAllItemsByShoppingListId(shoppingListId);
        if(items.isEmpty()) {
            throw new IllegalArgumentException("No items found in shopping list");
        }
        itemDAO.deleteAllItems(shoppingListId);
    }
}
