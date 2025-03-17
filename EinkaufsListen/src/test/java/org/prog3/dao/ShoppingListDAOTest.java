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

public class ShoppingListDAOTest {

    private ShoppingListDAO shoppingListDAO;

    @BeforeEach
    void SetUp () throws Exception{
        shoppingListDAO = new ShoppingListDAO();
        try (Connection connection =DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement()){
            stmt.execute("DELETE FROM ShoppingList");
        }
    }


    @AfterEach
    void tearDown () throws Exception{
        try (Connection connection =DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement()){
            stmt.execute("DELETE FROM ShoppingList");
        }
    }


    /**
     * Tests adding and getting all the ShoppingLists
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
     */
    @Test
    void testShoppingListsExist(){
        assertFalse(shoppingListDAO.shoppingListExists("NonExisting"),"the list NonExisting must not exist");
        ShoppingList list = new ShoppingList("ExistingList");
        shoppingListDAO.addShoppingList(list, "TestUser");
        assertTrue(shoppingListDAO.shoppingListExists("ExistingList")," the list Existinglist must be exist after Insertion");
    }

}
