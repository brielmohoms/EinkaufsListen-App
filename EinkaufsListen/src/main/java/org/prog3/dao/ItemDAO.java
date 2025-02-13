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
    public void saveItem (Item item){
        String SQL = "INSERT INTO Item (shopping_list_id, category, name, price, quantity) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setInt(1, item.getShoppingListId());
            statement.setString(2, item.getCategory());
            statement.setString(3, item.getName());
            statement.setDouble(4, item.getPrice());
            statement.setDouble(5, item.getQuantity());
            statement.executeUpdate();

        } catch (Exception e) {
            System.err.println("Failed to add the item: " + e.getMessage());
        }
    }


    /**
     * deletes an item by its name
     *
     * @param shoppingListId The ID of the shopping list
     * @param name the item name
     */
    public boolean deleteByName (int shoppingListId, String name) {
        String SQL = "DELETE FROM Item WHERE shopping_list_id = ? AND name = ? ";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setInt(1, shoppingListId);
            statement.setString(2, name);
            statement.executeUpdate();

        } catch (Exception e) {
            System.err.println("Failed to delete item by name: " + e.getMessage());
        }
        return true;
    }


    /**
     * find an item by its name
     *
     * @param name the item name
     */
    public Item findByName (int shoppingListId, String name) {
        String SQL = "SELECT * FROM Item WHERE shopping_list_id = ? AND name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setInt(1, shoppingListId);
            statement.setString(2, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setCategory(resultSet.getString("category"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setShoppingListId(shoppingListId);
                System.out.println("Item found: " + item);
                return item;
            } else {
                System.out.println("No items found with name: " + name + " in your shopping list");
            }
        } catch (Exception e) {
            System.err.println("Failed to find the item: " + e.getMessage());
        }
        return null;
    }


    /**
     * updates the quantity of an item
     *
     * @param shoppingListId the ID of the shopping list
     * @param name the item name
     * @param newQuantity the new item quantity
     */
    public boolean updateQuantity (int shoppingListId, String name, double newQuantity) {
        String sql = "UPDATE Item SET quantity = ? WHERE shopping_list_id = ? AND name = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setDouble(1, newQuantity);
            statement.setInt(2, shoppingListId);
            statement.setString(3, name);

            int update = statement.executeUpdate();
            return update > 0;
        } catch (Exception e) {
            System.err.println("Failed to update item quantity: " + e.getMessage());
            return false;
        }
    }


    /**
     * Retrieves all the items of a specific shopping list
     *
     * @param shoppingListId the ID of the shopping list
     * @return the list of items
     */
    public List<Item> findAllItemsByShoppingListId (int shoppingListId) {
        List<Item> items = new ArrayList<>();
        String SQL = "SELECT * FROM Item WHERE shopping_list_id = ? ORDER BY id ASC";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setInt(1, shoppingListId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setCategory(resultSet.getString("category"));
                item.setPrice(resultSet.getDouble("price"));
                item.setQuantity(resultSet.getDouble("quantity"));
                item.setShoppingListId(shoppingListId);
                items.add(item);
            }
        } catch (Exception e) {
            System.err.println("Failed to retrieve items: " + e.getMessage());
        }
        return items;
    }


    /**
     * Deletes all the items of a specific shopping list
     *
     * @param shoppingListId the ID of the shopping list
     */
    public void deleteAllItems(int shoppingListId) {
        String SQL = "DELETE FROM Item WHERE shopping_list_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, shoppingListId);
            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Failed to clear items in the shopping list: " + e.getMessage());
        }
    }
}
