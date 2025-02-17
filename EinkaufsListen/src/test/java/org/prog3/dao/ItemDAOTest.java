package org.prog3.dao;

import org.junit.jupiter.api.*;
import org.prog3.models.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ItemDAOTest {

    private ItemDAO itemDAO;
    private Connection connection;

    @BeforeEach
    void openConnection() throws Exception {
        connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS Item (id INTEGER PRIMARY KEY AUTOINCREMENT, shopping_list_name TEXT, category TEXT, name TEXT, price REAL, quantity REAL)");
        statement.close();

        itemDAO = new ItemDAO();  // Use the default constructor so it also uses DatabaseConnection
    }


    @AfterEach
    void closeConnection() throws Exception {
        connection.createStatement().execute("DROP TABLE Item");
        connection.close();
    }


    //TODO Fix
    /*@Test
    void testSaveItem() {
        Item item = new Item("Milk", "Dairy", 2.5, 1, "Kitchen");
        itemDAO.saveItem(item);

        // Debugging: Print database contents
        List<Item> items = itemDAO.findAllItemsByShoppingListName("Kitchen");
        System.out.println("Items in DB: " + items.size());

        assertEquals(1, items.size(), "Expected one item in the shopping list");
        assertEquals("Milk", items.get(0).getName(), "Expected item name to be 'Milk'");
    }*/



    /**
     *
     */
    @Test
    void testDeleteByName() {
        Item item = new Item("Apple", "Fruit", 2.99,1.0, "Kitchen");
        itemDAO.saveItem(item);

        boolean deleted = itemDAO.deleteByName("Kitchen","Apple");
        Item deletedItem = itemDAO.findByName("Kitchen", "Apple");

        assertTrue(deleted, "Item deleted successfully.");
        assertNull(deletedItem, "Deleted item should not be found.");
    }

    /**
     *
     */
    /*@Test
    void testFindByName() {
        Item item = new Item("Apple", "Fruit", 1.99, 1.0, "Kitchen");
        itemDAO.saveItem(item);

        Item foundItem = itemDAO.findByName("Kitchen", "Apple");

        assertNotNull(foundItem);
        assertEquals("Apple", foundItem.getName());
    }*/ // TODO Fix

    /**
     *
     */
    /*@Test
    void testUpdateQuantity() {
        Item item = new Item("Apple", "Fruit", 3.00, 2, "Kitchen");
        itemDAO.saveItem(item);

        boolean updated = itemDAO.updateQuantity("Kitchen", "Apple", 5);
        Item updatedItem = itemDAO.findByName("Kitchen", "Apple");

        assertTrue(updated, "Quantity should be updated.");
        assertNotNull(updatedItem, "Apple should be found in the database.");
        assertEquals(5, updatedItem.getQuantity(), "Updated quantity should be 5.");
    }*/ // TODO Fix

    /**
     *
     */
    /*@Test
    void testFindAllItemsByShoppingListName_ItemsExist() {
        itemDAO.saveItem(new Item("Milk", "Dairy", 2.5, 1, "Kitchen"));
        itemDAO.saveItem(new Item("Bread", "Bakery", 1.5, 2, "Kitchen"));
        List<Item> items = itemDAO.findAllItemsByShoppingListName("Kitchen");
        assertEquals(2, items.size());
    }*/ // TODO Fix

    /**
     *
     */
    @Test
    void testDeleteAllItems() {
        itemDAO.saveItem(new Item("Apple", "Fruit", 1.99, 1.0, "Kitchen"));
        itemDAO.saveItem(new Item("Banane", "Fruit", 1.99, 1.0, "Kitchen"));
        itemDAO.deleteAllItems("Kitchen");
        List<Item> items = itemDAO.findAllItemsByShoppingListName("Kitchen");
        assertTrue(items.isEmpty());
    }
}