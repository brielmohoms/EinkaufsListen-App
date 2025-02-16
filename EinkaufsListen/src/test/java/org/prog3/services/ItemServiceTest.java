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
    @Test
    void testAddItemWithValidInput() {
        doNothing().when(itemDAO).saveItem(any(Item.class));
        itemService.addItem(1, "Banane", "Fruit", 1.99, 1);
        verify(itemDAO, times(1)).saveItem(any(Item.class));
    }

    /**
     *
     */
    @Test
    void testAddItemWithInvalidPriceOrQuantity() {
        assertThrows(IllegalArgumentException.class, () -> itemService.addItem(1, "Banane", "Fruit", 0, 1));
        assertThrows(IllegalArgumentException.class, () -> itemService.addItem(1, "Banane", "Fruit", 1.99, 0));
    }

    /**
     *
     */
    @Test
    void testDeleteItemByNameWhenItemExists() {
        when(itemDAO.deleteByName(1, "Banane")).thenReturn(true);
        assertTrue(itemService.deleteItemByName(1, "Banane"));
        verify(itemDAO, times(1)).deleteByName(1, "Banane");
    }

    /**
     *
     */
    @Test
    void testDeleteItemByNameWhenItemDoesNotExists() {
        when(itemDAO.deleteByName(1, "Milk")).thenReturn(false);
        assertFalse(itemService.deleteItemByName(1, "Milk"));
    }

    /**
     *
     */
    @Test
    void testFindItemByNameWhenItemExists() {
        Item item = new Item("Banane", "Fruit",1.99,1, 1);
        when(itemDAO.findByName(1,"Banane")).thenReturn(item);
        assertEquals(item, itemService.findItemByName(1,"Banane"));
    }

    /**
     *
     */
    @Test
    void testFindItemByNameWhenItemDoesNotExists() {
        when(itemDAO.findByName(1,"Banane")).thenReturn(null);
        assertNull(itemService.findItemByName(1,"Banane"));
    }

    /**
     *
     */
    @Test
    void testUpdateItemQuantityWithValidInput() {
        when(itemDAO.updateQuantity(1, "Banane", 3)).thenReturn(true);
        assertTrue(itemService.updateItemQuantity(1, "Banane", 3));
        verify(itemDAO, times(1)).updateQuantity(1, "Banane", 3);
    }

    /**
     *
     */
    @Test
    void testUpdateItemQuantityWithInvalidInput() {
        assertFalse(itemService.updateItemQuantity(1, "Banane", 0));
        verify(itemDAO, never()).updateQuantity(anyInt(), anyString(), anyInt());
    }

    /**
     *
     */
    @Test
    void testGetAllItemsWhenItemsExist() {
        List<Item> items = Arrays.asList(new Item("Milk", "Dairy", 2.5, 1, 1));
        when(itemDAO.findAllItemsByShoppingListId(1)).thenReturn(items);
        assertEquals(items, itemService.getAllItems(1));
    }

    /**
     *
     */
    @Test
    void testGetAllItemsWhenItemsDoesNotExist() {
        when(itemDAO.findAllItemsByShoppingListId(1)).thenReturn(Collections.emptyList());
        assertTrue(itemService.getAllItems(1).isEmpty());
    }

    /**
     *
     */
    @Test
    void testDeleteAllItemsOfAShoppingListWhenItemsExist() {
        List<Item> items = Arrays.asList(new Item("Milk", "Dairy", 2.5, 1, 1));
        when(itemDAO.findAllItemsByShoppingListId(1)).thenReturn(items);
        doNothing().when(itemDAO).deleteAllItems(1);
        itemService.deleteAllItemsOfAShoppingList(1);
        verify(itemDAO, times(1)).deleteAllItems(1);
    }

    /**
     *
     */
    @Test
    void testDeleteAllItemsOfAShoppingListWhenItemsDoesNotExist() {
        when(itemDAO.findAllItemsByShoppingListId(1)).thenReturn(Collections.emptyList());
        assertThrows(IllegalArgumentException.class, () -> itemService.deleteAllItemsOfAShoppingList(1));
    }
}