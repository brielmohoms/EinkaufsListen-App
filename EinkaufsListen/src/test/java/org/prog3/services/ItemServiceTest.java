package org.prog3.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.prog3.dao.ItemDAO;
import org.prog3.models.Item;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    @Mock
    private ItemDAO itemDAO;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     *
     */
    /*@Test
    void testAddItemWithValidInput() {
        doNothing().when(itemDAO).saveItem(any(Item.class));
        itemService.addItem("Kitchen", "Apple", "Fruit", 1.99, 1);
        verify(itemDAO, times(1)).saveItem(any(Item.class));
    }*/

    /**
     *
     */
    /*@Test
    void testAddItemWithInvalidPriceOrQuantity() {
        assertThrows(IllegalArgumentException.class, () -> itemService.addItem("Kitchen", "Banane", "Fruit", 0, 1));
        assertThrows(IllegalArgumentException.class, () -> itemService.addItem("Kitchen", "Banane", "Fruit", 1.99, 0));
    }*/

    /**
     *
     */
    @Test
    void testDeleteItemByNameWhenItemExists() {
        when(itemDAO.deleteByName("Kitchen", "Banane")).thenReturn(true);
        assertTrue(itemService.deleteItemByName("Kitchen", "Banane"));
        verify(itemDAO, times(1)).deleteByName("Kitchen", "Banane");
    }

    /**
     *
     */
    @Test
    void testDeleteItemByNameWhenItemDoesNotExists() {
        when(itemDAO.deleteByName("Kitchen", "Milk")).thenReturn(false);
        assertFalse(itemService.deleteItemByName("Kitchen", "Milk"));
    }

    /**
     *
     */
    @Test
    void testFindItemByNameWhenItemExists() {
        Item item = new Item("Banane", "Fruit", 1.99, 1, "Kitchen");
        when(itemDAO.findByName("Kitchen","Banane")).thenReturn(item);
        assertEquals(item, itemService.findItemByName("Kitchen","Banane"));
    }

    /**
     *
     */
    @Test
    void testFindItemByNameWhenItemDoesNotExists() {
        when(itemDAO.findByName("Kitchen","Banane")).thenReturn(null);
        assertNull(itemService.findItemByName("Kitchen","Banane"));
    }

    /**
     *
     */
    @Test
    void testUpdateItemQuantityWithValidInput() {
        when(itemDAO.updateQuantity("Kitchen", "Banane", 3)).thenReturn(true);
        assertTrue(itemService.updateItemQuantity("Kitchen", "Banane", 3));
        verify(itemDAO, times(1)).updateQuantity("Kitchen", "Banane", 3);
    }

    /**
     *
     */
    @Test
    void testUpdateItemQuantityWithInvalidInput() {
        assertFalse(itemService.updateItemQuantity("Kitchen", "Banane", 0));
        verify(itemDAO, never()).updateQuantity(anyString(), anyString(), anyInt());
    }

    /**
     *
     */
    @Test
    void testGetAllItemsWhenItemsExist() {
        List<Item> items = Arrays.asList(new Item("Milk", "Dairy", 2.5, 1, "Kitchen"));
        when(itemDAO.findAllItemsByShoppingListName("Kitchen")).thenReturn(items);
        assertEquals(items, itemService.getAllItems("Kitchen"));
    }

    /**
     *
     */
    @Test
    void testGetAllItemsWhenItemsDoesNotExist() {
        when(itemDAO.findAllItemsByShoppingListName("Kitchen")).thenReturn(Collections.emptyList());
        assertTrue(itemService.getAllItems("Kitchen").isEmpty());
    }

    /**
     *
     */
    @Test
    void testDeleteAllItemsOfAShoppingListWhenItemsExist() {
        List<Item> items = Arrays.asList(new Item("Milk", "Dairy", 2.5, 1, "Kitchen"));
        when(itemDAO.findAllItemsByShoppingListName("Kitchen")).thenReturn(items);
        doNothing().when(itemDAO).deleteAllItems("Kitchen");
        itemService.deleteAllItemsOfAShoppingList("Kitchen");
        verify(itemDAO, times(1)).deleteAllItems("Kitchen");
    }

    /**
     *
     */
    @Test
    void testDeleteAllItemsOfAShoppingListWhenItemsDoesNotExist() {
        when(itemDAO.findAllItemsByShoppingListName("Kitchen")).thenReturn(Collections.emptyList());
        assertThrows(IllegalArgumentException.class, () -> itemService.deleteAllItemsOfAShoppingList("Kitchen"));
    }
}