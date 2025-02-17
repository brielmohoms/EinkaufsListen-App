package org.prog3.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    /**
     *
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
     *
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
     *
     */
    @Test
    void testDefaultConstructor(){
        Item item = new Item();
        assertNotNull(item);
    }

    /**
     *
     */
    @Test
    void testGetAndSetId() {
        Item item = new Item();
        item.setId(1);
        assertEquals(1, item.getId());
    }

    /**
     *
     */
    @Test
    void testGetAndSetName() {
        Item item = new Item();
        item.setName("Chris");
        assertEquals("Chris", item.getName());
    }

    /**
     *
     */
    @Test
    void testGetAndSetCategory() {
        Item item = new Item();
        item.setCategory("Fruit");
        assertEquals("Fruit", item.getCategory());
    }

    /**
     *
     */
    @Test
    void testGetAndSetPrice() {
        Item item = new Item();
        item.setPrice(2.99);
        assertEquals(2.99, item.getPrice());
    }

    /**
     *
     */
    @Test
    void testGetAndSetQuantity() {
        Item item = new Item();
        item.setQuantity(1);
        assertEquals(1, item.getQuantity());
    }

    /**
     *
     */
    @Test
    void testGetAndSetShoppingListId() {
        Item item = new Item();
        item.setShoppingListName("Kitchen");
        assertEquals("Kitchen", item.getShoppingListName());
    }

    /**
     *
     */
    @Test
    void testToString() {
        Item item = new Item(1, "Apple", "Fruit", 1.99, 1.5);
        String expected = "Item{id=1, name='Apple', category='Fruit', price=1.99, quantity=1.5, shoppingListName=null}";
        assertEquals(expected, item.toString());
    }

}