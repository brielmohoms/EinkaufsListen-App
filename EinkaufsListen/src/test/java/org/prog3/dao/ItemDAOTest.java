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
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE Item (id INTEGER PRIMARY KEY AUTOINCREMENT, shopping_list_id INTEGER, category TEXT, name TEXT, price REAL, quantity REAL)");
        statement.close();
        itemDAO = new ItemDAO();
    }

    @AfterEach
    void closeConnection() throws Exception {
        connection.createStatement().execute("DROP TABLE Item");
        connection.close();
    }

    //TODO Fix
    /*@Test
    void testSaveItem() {
        Item item = new Item("Milk", "Dairy", 2.5, 1, 1);
        itemDAO.saveItem(item);
        List<Item> items = itemDAO.findAllItemsByShoppingListId(1);
        assertEquals(1, items.size());
        assertEquals("Milk", items.getFirst().getName());
    }*/ //Funktioniert nicht

    /**
     *
     */
    @Test
    void testDeleteByName() {
        Item item = new Item("Apple", "Fruit", 2.99,1.0,1);
        itemDAO.saveItem(item);

        boolean deleted = itemDAO.deleteByName(1,"Apple");
        Item deletedItem = itemDAO.findByName(1, "Apple");

        assertTrue(deleted, "Item deleted successfully.");
        assertNull(deletedItem, "Deleted item should not be found.");
    }

    /**
     *
     */
    /*@Test
    void testFindByName() {
        Item item = new Item("Apple", "Fruit", 1.99, 1.0, 1);
        itemDAO.saveItem(item);

        Item foundItem = itemDAO.findByName(1, "Apple");

        assertNotNull(foundItem);
        assertEquals("Apple", foundItem.getName());
    }* // TODO Fix

    /**
     *
     */
    /*@Test
    void testUpdateQuantity() {
        Item item = new Item("Apple", "Fruit", 3.00, 2,1);
        itemDAO.saveItem(item);

        boolean updated = itemDAO.updateQuantity(1, "Apple", 5);
        Item updatedItem = itemDAO.findByName(1, "Apple");

        assertTrue(updated, "Quantity should be updated.");
        assertNotNull(updatedItem, "Apple should be found in the database.");
        assertEquals(5, updatedItem.getQuantity(), "Updated quantity should be 5.");
    }*/ // TODO Fix

    /**
     *
     */
    /*@Test
    void testFindAllItemsByShoppingListId_ItemsExist() {
        itemDAO.saveItem(new Item("Milk", "Dairy", 2.5, 1, 1));
        itemDAO.saveItem(new Item("Bread", "Bakery", 1.5, 2, 1));
        List<Item> items = itemDAO.findAllItemsByShoppingListId(1);
        assertEquals(2, items.size());
    }*/ // TODO Fix

    /**
     *
     */
    @Test
    void deleteAllItems() {
        itemDAO.saveItem(new Item("Apple", "Fruit", 1.99, 1.0, 1));
        itemDAO.saveItem(new Item("Banane", "Fruit", 1.99, 1.0, 1));
        itemDAO.deleteAllItems(1);
        List<Item> items = itemDAO.findAllItemsByShoppingListId(1);
        assertTrue(items.isEmpty());
    }
}