package org.prog3.dao;

import org.prog3.models.Item;
import org.prog3.models.ShoppingList;
import org.prog3.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for managing shopping lists database operations
 */
public class ShoppingListDAO {

    /**
     * The shopping list to be created
     *
     * @param shoppingList
     */
    public void addShoppingList(ShoppingList shoppingList) {
        String query = "INSERT INTO ShoppingList (name) VALUES (?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, shoppingList.getName());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    /**
     *
     *
     * @return
     */
    public List<ShoppingList> getAllShoppingLists() {
        String query = "SELECT * FROM ShoppingList ORDER BY id ASC";
        List<ShoppingList> shoppingsLists = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                ShoppingList shoppingList = new ShoppingList();
                shoppingList.setId(rs.getInt("id"));
                shoppingList.setName(rs.getString("name"));
                shoppingsLists.add(shoppingList);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return shoppingsLists;
    }


    /**
     *
     *
     * @param shoppingListName
     */
    public void deleteShoppingList(String shoppingListName) {
        String query = "DELETE FROM ShoppingList WHERE name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, shoppingListName);
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
}

