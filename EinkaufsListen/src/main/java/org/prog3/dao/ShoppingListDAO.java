package org.prog3.dao;

import org.prog3.models.Item;
import org.prog3.models.ShoppingList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for managing shopping lists database operations
 */
public class ShoppingListDAO {

    private Connection connection;


    /**
     *
     *
     * @return
     */
    public List<ShoppingList> getAllShoppingLists() {
        String query = "SELECT id, name FROM ShoppingList";
        List<ShoppingList> shoppingsLists = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                shoppingsLists.add(new ShoppingList(rs.getString("id"), rs.getString("name")));
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return shoppingsLists;
    }



    /**
     * The shopping list to be created
     *
     * @param shoppingList
     */
    public void addShoppingList(ShoppingList shoppingList) {
        String query = "INSERT INTO ShoppingList (user_name, name) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, shoppingList.getUserName());
            stmt.setString(2, shoppingList.getName());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Checks if a shopping list exists by name.
     *
     * @param shoppingListName The name of the shopping list.
     * @return true if the shopping list exists, false otherwise.
     */
    public boolean shoppingListExists(String shoppingListName) {
        String query = "SELECT COUNT(*) FROM ShoppingList WHERE name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, shoppingListName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error checking shopping list existence: " + e.getMessage());
        }
        return false;
    }


    /**
     *
     *
     * @param shoppingListId
     */
    public void deleteShoppingList(int shoppingListId) {
        String query = "DELETE FROM ShoppingList  WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, shoppingListId);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    /**
     *
     *
     * @param shoppingListId
     * @return
     */
    public List<Item> getItems(int shoppingListId) {
        List<Item> items = new ArrayList<>();
        String query = "SELECT id, name, category, price, quantity FROM Item WHERE list_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, shoppingListId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String category = rs.getString("category");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    items.add(new Item(id, name, category, price, quantity));
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return items;
    }
}

