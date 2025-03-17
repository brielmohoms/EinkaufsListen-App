package org.prog3.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.prog3.models.ShoppingList;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

}
