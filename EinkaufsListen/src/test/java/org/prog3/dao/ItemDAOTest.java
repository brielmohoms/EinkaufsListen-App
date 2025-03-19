package org.prog3.dao;

import org.junit.jupiter.api.*;
import org.prog3.models.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link ItemDAO} class
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemDAOTest {

    private ItemDAO itemDAO;
    private Connection connection;


    /**
     * Sets up the test environment by initializing database and ItemDAO instance all the tests
     *
     * @throws SQLException if an error occurs during the database connection
     */
    @BeforeAll
    void setUpTheDatabase () throws SQLException {
        connection = DatabaseConnection.getConnection();
        DatabaseInitializer.initialise();
        itemDAO = new ItemDAO();
    }


    /**
     * Cleans up the database before each test to ensure test isolation
     * and prevent data contamination
     *
     * @throws SQLException if an error occurs during the process
     */
    @BeforeEach
    void cleanUpTheDatabase () throws SQLException {
        connection.createStatement().execute("DELETE FROM Item");
    }


    /**
     * Tests saving an item to the database.
     */
    @Test
    void testSaveItem () {
        Item item = new Item("Apple", "Groceries", 2.99, 5, "Fruit");
        itemDAO.addItem(item);

        Item found = itemDAO.findByName("Groceries", "Apple");
        assertNotNull(found);
        assertEquals("Apple", found.getName());
    }


    /**
     * Tests deleting an item by name.
     */
    @Test
    void testDeleteByName () {
        Item item = new Item("Apple", "Fruit", 2.99,1.0, "Kitchen");
        itemDAO.addItem(item);

        boolean deleted = itemDAO.deleteItemByName("Kitchen","Apple");
        Item deletedItem = itemDAO.findByName("Kitchen", "Apple");

        assertTrue(deleted, "Item deleted successfully.");
        assertNull(deletedItem, "Deleted item should not be found.");
    }


    /**
     * Tests finding an item by its name.
     */
    @Test
    void testFindByName () {
        Item item = new Item("Apple", "Groceries", 1.99, 1.0, "Fruit");
        itemDAO.addItem(item);

        Item foundItem = itemDAO.findByName("Groceries", "Apple");

        assertNotNull(foundItem);
        assertEquals("Apple", foundItem.getName());
    }


    /**
     * Tests updating the quantity of an item.
     */
    @Test
    void testUpdateQuantity () {
        Item item = new Item("Apple", "Groceries", 3.00, 2, "Fruits");
        itemDAO.addItem(item);

        boolean updated = itemDAO.updateQuantity("Groceries", "Apple", 5);
        assertTrue(updated, "Quantity should be updated.");

        Item updatedQuantityItem = itemDAO.findByName("Groceries", "Apple");
        assertNotNull(updatedQuantityItem, "Apple should be found in the database.");
        assertEquals(5, updatedQuantityItem.getQuantity(), "Updated quantity should be 5.");
    }


    /**
     * Tests retrieving all items from a shopping list when items exist.
     */
    @Test
    void testFindAllItemsByShoppingListName () {
        itemDAO.addItem(new Item("Apple", "Groceries", 2.5, 1, "Fruit"));
        itemDAO.addItem(new Item("Banane", "Groceries", 1.5, 2, "Fruit"));

        List<Item> items = itemDAO.findAllItemsByShoppingListName("Groceries");
        assertEquals(2, items.size());
    }


    /**
     * Tests deleting all items from a shopping list.
     */
    @Test
    void testDeleteAllItems () {
        itemDAO.addItem(new Item("Apple", "Fruit", 1.99, 1.0, "Kitchen"));
        itemDAO.addItem(new Item("Banane", "Fruit", 1.99, 1.0, "Kitchen"));
        itemDAO.deleteAllItems("Kitchen");

        List<Item> items = itemDAO.findAllItemsByShoppingListName("Kitchen");
        assertTrue(items.isEmpty());
    }
}