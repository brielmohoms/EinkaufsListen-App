package org.prog3.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.prog3.dao.ShoppingListDAO;
import org.prog3.models.ShoppingList;
import org.prog3.models.User;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShoppingListServiceTest {

    @Mock
    private ShoppingListDAO shoppingListDAO;

    @Mock
    private UserService userService;

    @InjectMocks
    private ShoppingListService shoppingListService ;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Set up the mock to return a user with username "TestUser"
        when(userService.getLoggedInUser()).thenReturn(new User(1, "TestUser", "Test Name", "password", "regular"));
    }


    /**
     * Test getting all the shopping Lists
     */
    @Test
    void testGetAllShoppingLists() {
        List<ShoppingList> mockLists = Arrays.asList(new ShoppingList("Fruits"),new ShoppingList("furnitures"));
        when(shoppingListDAO.getAllShoppingLists("TestUser")).thenReturn(mockLists);
        List<ShoppingList> result = shoppingListService.getAllShoppingLists();
        assertEquals(2,result.size());
        assertEquals("Fruits",result.get(0).getName());
        assertEquals("furnitures",result.get(1).getName());
        verify(shoppingListDAO, times(1)).getAllShoppingLists("TestUser");
    }


    /**
     * Tests for the Existance of Shopping List
     */
    @Test
    void testShoppingListExists() {
        when(shoppingListDAO.shoppingListExists("Fruits")).thenReturn(true);
        assertTrue(shoppingListService.shoppingListExists("Fruits"));

        when(shoppingListDAO.shoppingListExists("cake")).thenReturn(false);
        assertFalse(shoppingListService.shoppingListExists("cake"));
        verify(shoppingListDAO, times(2)).shoppingListExists(anyString());
    }


    /**
     * tests adding a  Shopping List by valid Name
     */
    @Test
    void testAddShoppingListValidName() {
        shoppingListService.addShoppingList("Fruits");
        //verify(shoppingListDAO, times(1)).addShoppingList(any(ShoppingList.class), "TestUser");
        verify(shoppingListDAO, times(1)).addShoppingList(any(ShoppingList.class), eq("TestUser"));

    }


    /**
     * Tests adding a Shopping List when Name ist  null
     */
    @Test
    void testAddShoppingListWithNullName(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shoppingListService.addShoppingList(null);
        });
        assertEquals("Shopping list or items cannot be null.", exception.getMessage());
    }


    /**
     * Tests deleting a shopping List by valid Name
     */
    @Test
    void testDeleteShoppingListByNameValid() {
        when(shoppingListDAO.shoppingListExists("Fruits")).thenReturn(true);
        shoppingListService.deleteShoppingListByName("Fruits");
        verify(shoppingListDAO, times(1)).deleteShoppingList("Fruits");
    }


    /**
     * Tests deleting the Shopping List when Name ist null
     */
    @Test
    void testDeleteShoppingListByNameWithNull(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shoppingListService.deleteShoppingListByName(null);
        });
        assertEquals("Invalid shopping list name.", exception.getMessage());
    }

}
