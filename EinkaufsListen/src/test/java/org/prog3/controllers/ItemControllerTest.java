package org.prog3.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.prog3.models.Item;
import org.prog3.services.ItemService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

class ItemControllerTest {

    @Mock
    private ItemService itemService;

    @Mock
    private Scanner scanner;

    @InjectMocks
    private ItemController itemController;

    /**
     *
     */
    @BeforeEach
    void setUp() throws SQLException {
        itemService = mock(ItemService.class);
        scanner = mock(Scanner.class);
        itemController = new ItemController(itemService, scanner);
    }

    /**
     *
     */
    @Test
    void testAddItem() {
        when(scanner.next()).thenReturn("Kitchen","Fruit", "Apple"); // Shopping list name
        when(scanner.nextDouble()).thenReturn(2.99, 2.0);

        itemController.addItem();

        verify(itemService, times(1)).addItem("Kitchen", "Apple", "Fruit", 2.99, 2.0);
    }

    /**
     *
     */
    @Test
    void testRemoveItem() {
        when(scanner.next()).thenReturn("Kitchen", "Apple", "YES");

        itemController.removeItem();

        verify(itemService, times(1)).deleteItemByName("Kitchen", "Apple");
    }

    /**
     *
     */
    @Test
    void testRemoveItemCancelled() {
        when(scanner.next()).thenReturn("Kitchen");
        when(scanner.next()).thenReturn("Apple", "NO");

        itemController.removeItem();

        verify(itemService, never()).deleteItemByName(anyString(), anyString());
    }

    /**
     *
     */
    @Test
    void testFindItemByName() {
        when(scanner.next()).thenReturn("Kitchen", "Apple");

        itemController.findItemByName();

        verify(itemService).findItemByName("Kitchen", "Apple");
    }

    /**
     *
     */
    @Test
    void testUpdateItemQuantity() {
        when(scanner.next()).thenReturn("Kitchen", "Apple");

        when(scanner.nextDouble()).thenReturn(3.0);

        itemController.updateItemQuantity();

        verify(itemService).updateItemQuantity("Kitchen", "Apple", 3.0);
    }

    /**
     *
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
     *
     */
    @Test
    void testDeleteAllItemsOfShoppingList() {
        when(scanner.next()).thenReturn("Kitchen", "YES");

        itemController.deleteAllItemsOfShoppingList();

        verify(itemService).deleteAllItemsOfAShoppingList("Kitchen");
    }
}
