package org.prog3.dao;

import org.prog3.models.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for managing items database operations
 */
public class ItemDAO {

    /**
     * Adds an item to a shopping list
     *
     * @param item the item to be added
     */
    public void addItem (Item item) {
        String SQL = "INSERT INTO Item (shopping_list_name, category, name, price, quantity) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setString(1, item.getShoppingListName());
            statement.setString(2, item.getCategory());
            statement.setString(3, item.getName());
            statement.setDouble(4, item.getPrice());
            statement.setDouble(5, item.getQuantity());
            statement.executeUpdate();

        } catch (Exception e) {
            System.err.println("❌ Failed to add the item: " + e.getMessage());
        }
    }


    /**
     * Deletes an item by its name
     *
     * @param shoppingListName The name of the shopping list
     * @param name the item name
     */
    public boolean deleteItemByName (String shoppingListName, String name) {
        String SQL = "DELETE FROM Item WHERE shopping_list_name = ? AND name = ? ";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setString(1, shoppingListName);
            statement.setString(2, name);
            statement.executeUpdate();

        } catch (Exception e) {
            System.err.println("❌ Failed to delete item by name: " + e.getMessage());
        }
        return true;
    }


    /**
     * Finds an item by its name
     *
     * @param shoppingListName the shopping list name
     * @param name the item name
     */
    public Item findByName (String shoppingListName, String name) {
        String SQL = "SELECT * FROM Item WHERE shopping_list_name = ? AND name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setString(1, shoppingListName);
            statement.setString(2, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setCategory(resultSet.getString("category"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setShoppingListName(shoppingListName);
                System.out.println("Name: " + item.getName() + " | Category: " + item.getCategory() +
                        " | Price: " + item.getPrice() + " | Quantity: " + item.getQuantity());
                return item;
            } else {
                System.out.println("❌ No items found with name: " + name + " in your shopping list");
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to find the item: " + e.getMessage());
        }
        return null;
    }


    /**
     * Updates the quantity of an item
     *
     * @param shoppingListName the name of the shopping list
     * @param name the item name
     * @param newQuantity the new item quantity
     */
    public boolean updateQuantity (String shoppingListName, String name, double newQuantity) {
        String SQL = "UPDATE Item SET quantity = ? WHERE shopping_list_name = ? AND name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setDouble(1, newQuantity);
            statement.setString(2, shoppingListName);
            statement.setString(3, name);

            int update = statement.executeUpdate();
            return update > 0;
        } catch (Exception e) {
            System.err.println("❌ Failed to update item quantity: " + e.getMessage());
            return false;
        }
    }


    /**
     * Retrieves all the items of a specific shopping list
     *
     * @param shoppingListName the name of the shopping list
     * @return the list of items
     */
    public List<Item> findAllItemsByShoppingListName (String shoppingListName) {
        List<Item> items = new ArrayList<>();
        String SQL = "SELECT * FROM Item WHERE shopping_list_name = ? ORDER BY id ASC";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setString(1, shoppingListName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setCategory(resultSet.getString("category"));
                item.setPrice(resultSet.getDouble("price"));
                item.setQuantity(resultSet.getDouble("quantity"));
                item.setShoppingListName(shoppingListName);
                items.add(item);
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to retrieve items: " + e.getMessage());
        }
        return items;
    }


    /**
     * Deletes all the items of a specific shopping list
     *
     * @param shoppingListName the name of the shopping list
     */
    public void deleteAllItems(String shoppingListName) {
        String SQL = "DELETE FROM Item WHERE shopping_list_name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, shoppingListName);
            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("❌ Failed to clear items in the shopping list: " + e.getMessage());
        }
    }
}
