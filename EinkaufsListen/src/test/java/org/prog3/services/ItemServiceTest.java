package org.prog3.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.prog3.dao.ItemDAO;
import org.prog3.dao.ShoppingListDAO;
import org.prog3.models.Item;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link ItemService} class using mockito
 */
class ItemServiceTest {

    @Mock
    private ItemDAO itemDAO;

    @Mock
    private ShoppingListDAO shoppingListDAO;

    @InjectMocks
    private ItemService itemService;

    @InjectMocks
    private ShoppingListService shoppingListService;

    /**
     * sets up the mock dependency before each test
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        itemService = new ItemService(itemDAO, shoppingListDAO);
        when(shoppingListDAO.shoppingListExists(anyString())).thenReturn(true);
    }

    /**
     * tests adding an item to a shopping list with a valid input
     */
    @Test
    void testAddItemWithValidInput() {
        when(shoppingListDAO.shoppingListExists("Groceries")).thenReturn(true);
        doNothing().when(itemDAO).addItem(any(Item.class));

        itemService.addItem("Groceries", "Apple", "Fruit", 1.99, 1);
        verify(itemDAO, times(1)).addItem(any(Item.class));
    }


    /**
     * tests adding an item to a shopping list with an invalid input
     */
    @Test
    void testAddItemWithInvalidPriceOrQuantity() {
        shoppingListService.addShoppingList("Groceries");
        assertFalse(itemService.addItem("Groceries", "Banane", "Fruit", 0, 1));
        assertFalse(itemService.addItem("Groceries", "Banane", "Fruit", 1.99, 0));
    }


    /**
     * tests deleting an item from a shopping list when the item exist
     */
    @Test
    void testDeleteItemByNameWhenItemExists() {
        when(itemDAO.deleteItemByName("Kitchen", "Banane")).thenReturn(true);
        when(shoppingListDAO.shoppingListExists("Kitchen")).thenReturn(true);
        assertTrue(itemService.deleteItemByName("Kitchen", "Banane"));
        verify(itemDAO, times(1)).deleteItemByName("Kitchen", "Banane");
    }


    /**
     * tests deleting an item from a shopping list when the item does not exist
     */
    @Test
    void testDeleteItemByNameWhenItemDoesNotExists() {
        when(itemDAO.deleteItemByName("Kitchen", "Milk")).thenReturn(false);
        assertFalse(itemService.deleteItemByName("Kitchen", "Milk"));
    }


    /**
     * tests finding an item when the item exist
     */
    @Test
    void testFindItemByNameWhenItemExists() {
        Item item = new Item("Banane", "Fruit", 1.99, 1, "Kitchen");
        when(itemDAO.findByName("Kitchen","Banane")).thenReturn(item);
        assertEquals(item, itemService.findItemByName("Kitchen","Banane"));
    }


    /**
     * tests finding an item when the item does not exist
     */
    @Test
    void testFindItemByNameWhenItemDoesNotExists() {
        when(itemDAO.findByName("Groceries","Banane")).thenReturn(null);
        assertNull(itemService.findItemByName("Groceries","Banane"));
    }


    /**
     * tests updating an item quantity with valid input
     */
    @Test
    void testUpdateItemQuantityWithValidInput() {
        when(itemDAO.updateQuantity("Groceries", "Banane", 3)).thenReturn(true);
        assertTrue(itemService.updateItemQuantity("Groceries", "Banane", 3));
        verify(itemDAO, times(1)).updateQuantity("Groceries", "Banane", 3);
    }


    /**
     * tests updating an item quantity with invalid input
     */
    @Test
    void testUpdateItemQuantityWithInvalidInput() {
        assertFalse(itemService.updateItemQuantity("Groceries", "Banane", 0));
        verify(itemDAO, never()).updateQuantity(anyString(), anyString(), anyInt());
    }


    /**
     * tests getting all the items of a non-empty shopping list
     */
    @Test
    void testGetAllItemsWhenItemsExist() {
        List<Item> items = Arrays.asList(new Item("Apple", "Groceries", 2.5, 1, "Fruit"));
        when(itemDAO.findAllItemsByShoppingListName("Groceries")).thenReturn(items);
        assertEquals(items, itemService.getAllItems("Groceries"));
    }


    /**
     * tests getting all the items of an empty shopping list
     */
    @Test
    void testGetAllItemsWhenItemsDoesNotExist() {
        when(itemDAO.findAllItemsByShoppingListName("Groceries")).thenReturn(Collections.emptyList());
        assertTrue(itemService.getAllItems("Groceries").isEmpty());
    }


    /**
     * tests deleting all the items of a non-empty shopping list
     */
    @Test
    void testDeleteAllItemsOfAShoppingListWhenItemsExist() {
        when(shoppingListDAO.shoppingListExists("Dairy")).thenReturn(true);
        List<Item> items = Arrays.asList(new Item("Milk", "Dairy", 2.5, 1, "Kitchen"));
        when(itemDAO.findAllItemsByShoppingListName("Kitchen")).thenReturn(items);
        doNothing().when(itemDAO).deleteAllItems("Kitchen");
        itemService.deleteAllItemsOfAShoppingList("Kitchen");
        verify(itemDAO, times(1)).deleteAllItems("Kitchen");
    }


    /**
     * tests deleting all the items of an empty shopping list
     */
    @Test
    void testDeleteAllItemsOfAShoppingListWhenItemsDoesNotExist() {
        when(itemDAO.findAllItemsByShoppingListName("Kitchen")).thenReturn(Collections.emptyList());
        assertFalse(itemService.deleteAllItemsOfAShoppingList("Kitchen"));
    }
}