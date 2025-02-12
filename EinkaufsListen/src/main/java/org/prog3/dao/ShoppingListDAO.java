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
    private final Connection connection;
    public ShoppingListDAO(Connection connection){
        this.connection = connection;
    }
    public List<ShoppingList> getAllShoppingLists() throws SQLException {
        List<ShoppingList> shoppingsLists = new ArrayList<>();
        String query = "SELECT id , name FROM shoppingList";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                shoppingsLists.add(new ShoppingList(rs.getInt("id"), rs.getString("name")));
            }
        }
        return shoppingsLists;
    }
    public void addShoppingList(String name) throws SQLException {
        String query = "INSERT INTO shoppingList(name) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }
    public void deleteShoppingList(int shoppingListId) throws SQLException {
        String query = "DELETE FROM shoppingList  WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, shoppingListId);
            stmt.executeUpdate();
        }
    }
    public List<Item> getItems(int shoppingListId) throws SQLException {
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
        }
        return items;
    }

}

