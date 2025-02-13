package org.prog3.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testConstructorWithAllFields() {
        Item item = new Item(1, "Apple", "Fruit", 1.99, 1);
        assertEquals(1, item.getId());
        assertEquals("Apple", item.getName());
        assertEquals("Fruit", item.getCategory());
        assertEquals(1.99, item.getPrice());
        assertEquals(1, item.getQuantity());
    }

    @Test
    void testConstructorWithShoppingListId() {
        Item item = new Item("Apple", "Fruit", 1.99, 1, 1);
        assertEquals("Apple", item.getName());
        assertEquals("Fruit", item.getCategory());
        assertEquals(1.99, item.getPrice());
        assertEquals(1, item.getQuantity());
        assertEquals(1, item.getShoppingListId());
    }

    @Test
    void testConstructorWithDifferentOrder() {
        Item item = new Item("Apple", 1, 2, 1.99, "Fruit");
        assertEquals("Apple", item.getName());
        assertEquals(1, item.getShoppingListId());
        assertEquals(2, item.getQuantity());
        assertEquals(1.99, item.getPrice());
        assertEquals("Fruit", item.getCategory());
    }

    @Test
    void testDefaultConstructor(){
        Item item = new Item();
        assertNotNull(item);
    }

    @Test
    void testGetAndSetId() {
        Item item = new Item();
        item.setId(1);
        assertEquals(1, item.getId());
    }

    @Test
    void testGetAndSetName() {
        Item item = new Item();
        item.setName("Chris");
        assertEquals("Chris", item.getName());
    }

    @Test
    void testGetAndSetCategory() {
        Item item = new Item();
        item.setCategory("Fruit");
        assertEquals("Fruit", item.getCategory());
    }

    @Test
    void testGetAndSetPrice() {
        Item item = new Item();
        item.setPrice(2.99);
        assertEquals(2.99, item.getPrice());
    }

    @Test
    void testGetAndSetQuantity() {
        Item item = new Item();
        item.setQuantity(1);
        assertEquals(1, item.getQuantity());
    }

    @Test
    void testGetAndSetShoppingListId() {
        Item item = new Item();
        item.setShoppingListId(3);
        assertEquals(3, item.getShoppingListId());
    }

    @Test
    void testToString() {
    }
}