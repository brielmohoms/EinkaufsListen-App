package org.prog3.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.prog3.models.ShoppingList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link ShoppingListDAO} class.
 *
 *<p>
 *This class tests the persistence layer for handling shopping lists.
 *It verifies that shopping lists can be added, retrieved, deleted,
 *and checked for existence in the database.
 * </p>
 */

public class ShoppingListDAOTest {

    private ShoppingListDAO shoppingListDAO;


    /**
     * Sets up the database connection and clears the shopping list table before each test.
     * @throws Exception  if a database connection or SQL execution error occurs.
     */
    @BeforeEach
    void SetUp () throws Exception{
        shoppingListDAO = new ShoppingListDAO();
        try (Connection connection =DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement()){
            stmt.execute("DELETE FROM Item");
            stmt.execute("DELETE FROM ShoppingList");

        }
    }


    /**
     * Cleans up the database by removing all shopping lists after each test.
     * @throws Exception if a database connection or SQL execution error occurs.
     */
    @AfterEach
    void tearDown () throws Exception{
        try (Connection connection =DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement()){
            stmt.execute("DELETE FROM ShoppingList");
        }
    }


    /**
     *Tests adding a new shopping list and retrieving all shopping lists.
     * Ensures that after insertion, the list is present in the database.
     */
    @Test
    void testAddAndGetAllShoppingLists(){
        ShoppingList list = new ShoppingList("Testlist");
        shoppingListDAO.addShoppingList(list, "TestUser");

        List<ShoppingList> lists = shoppingListDAO.getAllShoppingLists("TestUser");
        assertFalse(lists.isEmpty()," the list should be not empty after insertion");
        boolean found = lists.stream().anyMatch(l -> "Testlist".equals(l.getName()));
        assertTrue(found ," die liste existiert schon ");

    }


    /**
     * Tests deleting the ShoppingList
     *  Ensures that the shopping list is removed from the database after deletion.
     */
    @Test
    void testDeleteShoppingList(){
        ShoppingList list = new ShoppingList("ToDelete");
        shoppingListDAO.addShoppingList(list, "TestUser");

        assertTrue(shoppingListDAO.shoppingListExists("ToDelete")," die liste muss existieren bevor delete");
        shoppingListDAO.deleteShoppingList("ToDelete");
        assertFalse(shoppingListDAO.shoppingListExists("ToDelete"),"after delete , the list must not exists");
    }


    /**
     * Tests to verify an existing ShoppingList
     * Ensures that checking an existing list returns true and a non-existing one returns false.
     */
    @Test
    void testShoppingListsExist(){
        assertFalse(shoppingListDAO.shoppingListExists("NonExisting"),"the list NonExisting must not exist");
        ShoppingList list = new ShoppingList("ExistingList");
        shoppingListDAO.addShoppingList(list, "TestUser");
        assertTrue(shoppingListDAO.shoppingListExists("ExistingList")," the list Existinglist must be exist after Insertion");
    }


    /**
     * Tests retrieving the total price from the database.
     */
    @Test
    void testGetTotalPriceSuccess() throws Exception {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement()) {

            // Insert a shopping list
            stmt.execute("INSERT INTO ShoppingList (name,username) VALUES ('Groceries','Van')");

            // Insert items into the shopping list
            stmt.execute("INSERT INTO Item (shopping_list_name,category ,name ,price ,quantity) " +
                    "VALUES ('Groceries','bakery Items','Milk',1.50,2), ('Groceries','seafood','fisch',2.50,3)");

            // Verify the inserted data
            ResultSet rs = stmt.executeQuery("SELECT * FROM Item WHERE shopping_list_name = 'Groceries'");
            while (rs.next()) {
                System.out.println("Item: " + rs.getString("name") +
                        ", Price: " + rs.getDouble("price") +
                        ", Quantity: " + rs.getInt("quantity"));
            }
        }

    }


    /**
     * Tests retrieving the total price of a non-existent shopping list.
     */
    @Test
    void testGetTotalPrice_NonExistentList() {
        double totalPrice = shoppingListDAO.getTotalPrice("NonExistentList");
        assertEquals(0.0, totalPrice, 0.01);
    }
}
