package org.prog3.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.prog3.dao.ShoppingListDAO;
import org.prog3.models.Item;
import org.prog3.services.ItemService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link ItemController} class
 */
class ItemControllerTest {

    @Mock
    private ItemService itemService;

    @Mock
    private Scanner scanner;

    @Mock
    private ShoppingListDAO shoppingListDAO;

    @InjectMocks
    private ItemController itemController;

    /**
     * Sets up the mock dependencies before each test
     *
     * @throws SQLException if an error occurs during the database connection
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    /**
     * tests to ensure that an item is added correctly.
     */
    @Test
    void testAddItem() {
        when(scanner.nextLine()).thenReturn("Kitchen","Fruit", "Apple"); // Shopping list name
        when(scanner.nextDouble()).thenReturn(2.99, 2.0);
        itemController.addItem();
        verify(itemService, times(1)).addItem("Kitchen", "Apple", "Fruit", 2.99, 2.0);
    }


    /**
     * test to ensure that an item is removed when the user enters YES
     */
    @Test
    void testRemoveItem() {
        when(scanner.nextLine()).thenReturn("Kitchen", "Apple", "YES");
        itemController.removeItem();
        verify(itemService, times(1)).deleteItemByName("Kitchen", "Apple");
    }


    /**
     * test to ensure that an item is not removed when the user enters NO
     */
    @Test
    void testRemoveItemCancelled() {
        when(scanner.nextLine()).thenReturn("Kitchen");
        when(scanner.nextLine()).thenReturn("Apple", "NO");
        itemController.removeItem();
        verify(itemService, never()).deleteItemByName(anyString(), anyString());
    }


    /**
     * test to ensure that an item is found and displayed
     */
    /*@Test
    void testFindItemByName() {
        when(scanner.nextLine()).thenReturn("Kitchen", "Apple");
        when(shoppingListDAO.shoppingListExists("Kitchen")).thenReturn(true);
        itemController.findItemByName();
        verify(itemService).findItemByName("Kitchen", "Apple");
    }*/


    /**
     * test to update the quantity of an item of a specific shopping list
     */
    @Test
    void testUpdateItemQuantity() {
        when(scanner.next()).thenReturn("Kitchen", "Apple");
        when(scanner.nextDouble()).thenReturn(3.0);
        itemController.updateItemQuantity();
        verify(itemService).updateItemQuantity("Kitchen", "Apple", 3.0);
    }


    /**
     * test to view all the items of a specific shopping list
     */
    @Test
    void testViewAllItemsOfShoppingList() {
        when(scanner.next()).thenReturn("Kitchen");
        List<Item> items = Arrays.asList(new Item("Apple", "Fruit", 2.99, 2.0, "Kitchen"));
        when(itemService.getAllItems("Kitchen")).thenReturn(items);
        itemController.viewAllItemsOfShoppingList();
        verify(itemService).getAllItems("Kitchen");
    }


    /**
     * test to delete all the items of a specific shopping list
     */
    @Test
    void testDeleteAllItemsOfShoppingList() {
        when(scanner.next()).thenReturn("Kitchen", "YES");
        itemController.deleteAllItemsOfShoppingList();
        verify(itemService).deleteAllItemsOfAShoppingList("Kitchen");
    }
}
