package org.prog3.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.prog3.models.ShoppingList;
import org.prog3.services.ShoppingListService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;


/**
 * Unit tests for the {@link ShoppingListController} class
 *
 * <p>
 * This class tests the functionality of viewing, adding, and deleting shopping lists.
 * The {@link ShoppingListService} is mocked to isolate the controller logic.
 * </p>
 */
public class ShoppingListControllerTest {

    @Mock
    private ShoppingListService shoppingListService;

    @Mock
    private Scanner scanner;

    @InjectMocks
    ShoppingListController shoppingListController;


    /**
     * Sets up the mock dependencies before each test
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        shoppingListController = new ShoppingListController(shoppingListService, scanner);
    }


    /**
     *Tests retrieving and displaying all shopping lists.
     * Ensures the {@code getAllShoppingLists()} method is called once.
     */
    @Test
    void testViewShoppingList() {
        List<ShoppingList> shoppingLists = Arrays.asList(
                new ShoppingList("Kitchen"),
                new ShoppingList("Furnitures")
        );
        when(shoppingListService.getAllShoppingLists()).thenReturn(shoppingLists);
        shoppingListController.viewShoppingList();
        verify(shoppingListService,times(1)).getAllShoppingLists();
    }


    /**
     * Tests adding a new shopping list.
     * Ensures the {@code addShoppingList()} method correctly processes user input.
     */
    @Test
    void testAddShoppingList() {
        when(scanner.nextLine()).thenReturn("WeekendList");
        doNothing().when(shoppingListService).addShoppingList(anyString());

        shoppingListController.addShoppingList();

        verify(shoppingListService, times(1)).addShoppingList("WeekendList");

    }


    /**
     * Tests deleting a shopping list by name.
     * Ensures the correct service method is called with the correct parameter.
     */
    @Test
    void TestDeleteShoppingList() {
        when(scanner.nextLine()).thenReturn("Kitchen");
        doNothing().when(shoppingListService).deleteShoppingListByName(anyString());

        shoppingListController.deleteShoppingList();

        verify(shoppingListService, times(1)).deleteShoppingListByName("Kitchen");

    }


    /**
     * Tests calculating the total price of a shopping list successfully.
     */
    @Test
    void testViewTotalPrice_Success() {
        when(scanner.nextLine()).thenReturn("Groceries");
        when(shoppingListService.getTotalPrice("Groceries")).thenReturn(20.50);

        shoppingListController.viewTotalPrice();

        verify(shoppingListService, times(1)).getTotalPrice("Groceries");
    }


    /**
     * Tests when the shopping list does not exist.
     */
    @Test
    void testViewTotalPrice_ListDoesNotExist() {
        when(scanner.nextLine()).thenReturn("NonExistentList");
        when(shoppingListService.getTotalPrice("NonExistentList"))
                .thenThrow(new IllegalArgumentException("Shopping list does not exist."));

        shoppingListController.viewTotalPrice();

        verify(shoppingListService, times(1)).getTotalPrice("NonExistentList");
    }

}

