package org.prog3.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.prog3.models.Item;
import org.prog3.services.ItemService;

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
    void setUp() {
        itemService = mock(ItemService.class);
        scanner = mock(Scanner.class);
        itemController = new ItemController(itemService, scanner);
    }

    /**
     *
     */
    @Test
    void testAddItem() {
        when(scanner.nextInt()).thenReturn(1); // Shopping list ID
        when(scanner.next()).thenReturn("Fruit", "Apple");
        when(scanner.nextDouble()).thenReturn(2.99, 2.0);

        itemController.addItem();

        verify(itemService).addItem(1, "Apple", "Fruit", 2.99, 2.0);
    }

    /**
     *
     */
    @Test
    void testRemoveItem() {
        when(scanner.nextInt()).thenReturn(1); // Shopping list ID
        when(scanner.next()).thenReturn("Apple", "YES");

        itemController.removeItem();

        verify(itemService).deleteItemByName(1, "Apple");
    }

    /**
     *
     */
    @Test
    void testRemoveItemCancelled() {
        when(scanner.nextInt()).thenReturn(1);
        when(scanner.next()).thenReturn("Apple", "NO");

        itemController.removeItem();

        verify(itemService, never()).deleteItemByName(anyInt(), anyString());
    }

    /**
     *
     */
    @Test
    void testFindItemByName() {
        when(scanner.nextInt()).thenReturn(1);
        when(scanner.next()).thenReturn("Apple");

        itemController.findItemByName();

        verify(itemService).findItemByName(1, "Apple");
    }

    /**
     *
     */
    @Test
    void testUpdateItemQuantity() {
        when(scanner.nextInt()).thenReturn(1);
        when(scanner.next()).thenReturn("Apple");
        when(scanner.nextDouble()).thenReturn(3.0);

        itemController.updateItemQuantity();

        verify(itemService).updateItemQuantity(1, "Apple", 3.0);
    }

    /**
     *
     */
    @Test
    void testViewAllItemsOfShoppingList() {
        when(scanner.nextInt()).thenReturn(1);
        List<Item> items = Arrays.asList(new Item("Apple", "Fruit", 2.99, 2.0, 1));
        when(itemService.getAllItems(1)).thenReturn(items);

        itemController.viewAllItemsOfShoppingList();

        verify(itemService).getAllItems(1);
    }

    /**
     *
     */
    @Test
    void testDeleteAllItemsOfShoppingList() {
        when(scanner.nextInt()).thenReturn(1);

        itemController.deleteAllItemsOfShoppingList();

        verify(itemService).deleteAllItemsOfAShoppingList(1);
    }
}
