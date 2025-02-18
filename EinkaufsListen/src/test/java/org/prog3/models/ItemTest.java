package org.prog3.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Item} class
 */
class ItemTest {

    /**
     * tests the constructor with the item ID
     */
    @Test
    void testConstructorWithAllFields() {
        Item item = new Item(1, "Apple", "Fruit", 1.99, 1.5);
        assertEquals(1, item.getId());
        assertEquals("Apple", item.getName());
        assertEquals("Fruit", item.getCategory());
        assertEquals(1.99, item.getPrice());
        assertEquals(1.5, item.getQuantity());
    }


    /**
     * tests the constructor with the shopping list name
     */
    @Test
    void testConstructorWithShoppingListName() {
        Item item = new Item("Apple", "Kitchen", 1.99, 1.5, "Fruit");
        assertEquals("Apple", item.getName());
        assertEquals("Fruit", item.getCategory());
        assertEquals(1.99, item.getPrice());
        assertEquals(1.5, item.getQuantity());
        assertEquals("Kitchen", item.getShoppingListName());
    }


    /**
     * tests the default constructor
     */
    @Test
    void testDefaultConstructor(){
        Item item = new Item();
        assertNotNull(item);
    }


    /**
     * tests the getter and setter for ID
     */
    @Test
    void testGetAndSetId() {
        Item item = new Item();
        item.setId(1);
        assertEquals(1, item.getId());
    }


    /**
     * tests the getter and setter for item name
     */
    @Test
    void testGetAndSetName() {
        Item item = new Item();
        item.setName("Chris");
        assertEquals("Chris", item.getName());
    }


    /**
     * tests the getter and setter for item category
     */
    @Test
    void testGetAndSetCategory() {
        Item item = new Item();
        item.setCategory("Fruit");
        assertEquals("Fruit", item.getCategory());
    }


    /**
     * tests the getter and setter for item price
     */
    @Test
    void testGetAndSetPrice() {
        Item item = new Item();
        item.setPrice(2.99);
        assertEquals(2.99, item.getPrice());
    }


    /**
     * tests the getter and setter for item quantity
     */
    @Test
    void testGetAndSetQuantity() {
        Item item = new Item();
        item.setQuantity(1);
        assertEquals(1, item.getQuantity());
    }


    /**
     * tests the getter and setter for the item's shopping list name
     */
    @Test
    void testGetAndSetShoppingListName() {
        Item item = new Item();
        item.setShoppingListName("Kitchen");
        assertEquals("Kitchen", item.getShoppingListName());
    }


    /**
     * tests the String representation of the item object
     */
    @Test
    void testToString() {
        Item item = new Item(1, "Apple", "Fruit", 1.99, 1.5);
        String expected = "Item{id=1, name='Apple', category='Fruit', price=1.99, quantity=1.5, shoppingListName=null}";
        assertEquals(expected, item.toString());
    }

}