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
      Connection connection = DatabaseConnection.getConnection() ;

    public ShoppingListDAO() throws SQLException {
    }

    /**
     *
     *
     * @param Connection
     */
    /**public ShoppingListDAO(Connection Connection ) throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }
     **/


    /**
     *
     *
     * @return
     * @throws SQLException
     */
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



    /**
     *
     *
     * @param shoppingList
     * @throws SQLException
     */
    public void addShoppingList(ShoppingList shoppingList) throws SQLException {
        connection= DatabaseConnection.getConnection();
        String query = "INSERT INTO ShoppingList (user_id, name) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(2, shoppingList.getName());
            stmt.setInt(1,shoppingList.getUserId());
            stmt.executeUpdate();
        }
    }


    /**
     *
     *
     * @param shoppingListId
     * @throws SQLException
     */
    public void deleteShoppingList(int shoppingListId) throws SQLException {
        String query = "DELETE FROM ShoppingList  WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, shoppingListId);
            stmt.executeUpdate();
        }
    }


    /**
     *
     *
     * @param shoppingListId
     * @return
     * @throws SQLException
     */
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

