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
    public void addShoppingList(ShoppingList shoppingList, String username) {
        String query = "INSERT INTO ShoppingList (name, username) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, shoppingList.getName());
            stmt.setString(2, username);
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
    public List<ShoppingList> getAllShoppingLists(String username) {
        String query = "SELECT * FROM ShoppingList WHERE username = ? ORDER BY id ASC";
        List<ShoppingList> shoppingsLists = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ShoppingList shoppingList = new ShoppingList();
                    shoppingList.setId(rs.getInt("id"));
                    shoppingList.setName(rs.getString("name"));
                    shoppingsLists.add(shoppingList);
                }
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

    /**
     *
     *
     * @param shoppingListName
     * @return
     */
    public double getTotalPrice(String shoppingListName) {
        String query = "SELECT SUM(price * quantity) AS total FROM Item WHERE shopping_list_name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, shoppingListName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                double totalDollars = rs.getDouble("total");
                double totalEuros = totalDollars * 0.92;  // Convert to EUR
                return Math.round(totalEuros * 100.0) / 100.0;
            }
        } catch (Exception e) {
            System.err.println("Error calculating total price: " + e.getMessage());
        }
        return 0.0;  // Return 0 if there are no items or an error occurs
    }

}

