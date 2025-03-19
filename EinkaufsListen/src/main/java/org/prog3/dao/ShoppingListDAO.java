package org.prog3.dao;

import org.prog3.models.Item;
import org.prog3.models.ShoppingList;
import org.prog3.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object (DAO) class responsible for managing shopping lists database operations
 *
 * <p>
 * this class provides methods for adding ,deleting , getting all shopping lists and getting total price shopping list data from the database ,
 * It ensures data integrity and abstracts database operations from the service layer.
 * </p>
 */
public class ShoppingListDAO {

    /**
     * Adds a new shopping list to the database.
     *
     * @param shoppingList the shopping list object to add
     * @param username the username of the user to whom the shopping list belongs
     */
    public void addShoppingList(ShoppingList shoppingList, String username) {
        String query = "INSERT INTO ShoppingList (name, username) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, shoppingList.getName());
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }


    /**
     * Retrieves all shopping lists for a given user.
     *
     * @param username the username whose shopping lists are to be retrieved
     * @return a list of shopping lists belonging to the specified user
     */
    public List<ShoppingList> getAllShoppingLists (String username) {
        String query = "SELECT * FROM ShoppingList WHERE username = ? ORDER BY id ASC";
        List<ShoppingList> shoppingsLists = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    ShoppingList shoppingList = new ShoppingList();
                    shoppingList.setId(rs.getInt("id"));
                    shoppingList.setName(rs.getString("name"));
                    shoppingsLists.add(shoppingList);
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return shoppingsLists;
    }


    /**
     * Deletes a shopping list from the database.
     *
     * @param shoppingListName the name of the shopping list to delete
     */
    public void deleteShoppingList (String shoppingListName) {
        String query = "DELETE FROM ShoppingList WHERE name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, shoppingListName);
            statement.executeUpdate();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    /**
     * Checks if a shopping list exists in the database by its name.
     *
     * @param shoppingListName the name of the shopping list to check.
     * @return true if the shopping list exists, false otherwise
     */
    public boolean shoppingListExists (String shoppingListName) {
        String query = "SELECT COUNT(*) FROM ShoppingList WHERE name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, shoppingListName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error checking shopping list existence: " + e.getMessage());
        }
        return false;
    }

    /**
     * Calculates the total price of items in a specified shopping list.
     *
     * @param shoppingListName the name of the shopping list.
     * @return the total price in Euros. Returns 0.0 if there are no items or if an error occurs.
     */
    public double getTotalPrice (String shoppingListName) {
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

