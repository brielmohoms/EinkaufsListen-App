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

class ShoppingListControllerTest {

    @Mock
    private ShoppingListService shoppingListService;

    @Mock
    private Scanner scanner;

    @InjectMocks
    ShoppingListController shoppingListController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        shoppingListController = new ShoppingListController(shoppingListService, scanner);
    }


    /**
     * Test seeing the Shopping List
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
     * Tests adding the shopping List
     */
    @Test
    void testAddShoppingList() {
        when(scanner.nextLine()).thenReturn("WeekendList");
        doNothing().when(shoppingListService).addShoppingList(anyString());

        shoppingListController.addShoppingList();

        verify(shoppingListService, times(1)).addShoppingList("WeekendList");

    }


    /**
     * Tests deleting the ShoppingList
     */
    @Test
    void TestDeleteShoppingList() {
        when(scanner.nextLine()).thenReturn("Kitchen");
        doNothing().when(shoppingListService).deleteShoppingListByName(anyString());

        shoppingListController.deleteShoppingList();

        verify(shoppingListService, times(1)).deleteShoppingListByName("Kitchen");

    }
}

