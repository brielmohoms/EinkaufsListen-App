package org.prog3.services;

import org.prog3.dao.ItemDAO;
import org.prog3.dao.ShoppingListDAO;
import org.prog3.models.Item;

import java.util.List;

/**
 * Service for managing item related operations
 */
public class ItemService {

    private ItemDAO itemDAO = new ItemDAO();
    private ShoppingListDAO shoppingListDAO = new ShoppingListDAO();

    /**
     * Constructor to enable dependency injection. Used for the tests
     *
     * @param itemDAO an itemDAO object
     */
    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public ItemService() {

    }

    /**
     * Adds a new item
     *
     * @param shoppingListName the shopping list name
     * @param name the item name
     * @param category the item category
     * @param price the item price
     * @param quantity the item quantity
     */
    public void addItem(String shoppingListName, String name, String category, double price, double quantity) {
        if (!shoppingListDAO.shoppingListExists(shoppingListName)) {
            System.out.println("Error: Shopping list does not exist!");
            return;
        }

        if (price <= 0 || quantity <= 0) {
            throw new IllegalArgumentException("Price and quantity cannot be zero or negative");
        } else {
            Item item = new Item(name, shoppingListName, quantity, price, category);
            itemDAO.saveItem(item);
        }
    }

    /**
     * Deletes an item by its name
     *
     * @param shoppingListName the shopping list name
     * @param name the item name
     */
    public boolean deleteItemByName (String shoppingListName, String name){
        boolean deleted = itemDAO.deleteByName(shoppingListName, name);
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
     * @param shoppingListName the shopping list name
     * @param name the item name
     *
     * @return the item found
     */
    public Item findItemByName (String shoppingListName, String name) {
        return itemDAO.findByName(shoppingListName, name);
    }

    /**
     * Updates an item quantity after finding by its name
     *
     * @param shoppingListName the shopping list name
     * @param name the item name
     * @param newQuantity the item quantity
     */
    public boolean updateItemQuantity (String shoppingListName, String name, double newQuantity) {
        if(newQuantity == 0){
            System.out.println("Invalid quantity");
            return false;
        }

        boolean item = itemDAO.updateQuantity(shoppingListName, name, newQuantity);

        if(item) {
            System.out.println("✅ Quantity updated for " + name + " to " + newQuantity);
        } else {
            System.out.println("Item not found or update failed");
        }
        return item;
    }

    /**
     * Gets all the items
     *
     * @param shoppingListName the shopping list name
     * @return a list of all the items
     */
    public List<Item> getAllItems (String shoppingListName) {
        return itemDAO.findAllItemsByShoppingListName(shoppingListName);
    }

    /**
     * deletes all the items of a specific shopping list
     *
     * @param shoppingListName the shopping list name
     */
    public void deleteAllItemsOfAShoppingList (String shoppingListName) {
        List<Item> items = itemDAO.findAllItemsByShoppingListName(shoppingListName);
        if(items.isEmpty()) {
            throw new IllegalArgumentException("No items found in shopping list");
        }
        itemDAO.deleteAllItems(shoppingListName);
    }
}
