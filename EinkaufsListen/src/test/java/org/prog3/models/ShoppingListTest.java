package org.prog3.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for the {@link ShoppingList} class
 */
public class ShoppingListTest {

    private ShoppingList shoppingList;

    /**
     * Initializes a new empty ShoppingList instance before each test.
     */
    @BeforeEach
    void setUp(){
        shoppingList = new ShoppingList();
    }


    /**
     *  Tests the default constructor.
     */
    @Test
    void TestDefaultConstructor (){
        assertNull(shoppingList.getName(),"Name shoulb be null");
        assertEquals(0 ,shoppingList.getId());
        assertNull(shoppingList.getItems() ,"Items liste sollte initial null sein  ");
    }


    /**
     * Tests the parameterized constructor.
     */
    @Test
    void testParametriziedConstructor(){
        String name = "wicked ";
        ShoppingList listName = new ShoppingList(name);
        assertEquals(name,listName.getName());
    }


    /**
     * Tests setting and getting the Name
     */
    @Test
    void testSetAndGetName (){
        String name ="kitchen";
        shoppingList.setName(name);
        assertEquals(name,shoppingList.getName());

    }


    /**
     * Tests setting and getting the id
     */
    @Test
    void TestSetAndGetId (){
        int id = 56;
        shoppingList.setId(id);
        assertEquals(id, shoppingList.getId());
    }


    /**
     * Tests Setting and getting Items
     */
    @Test
    void SetAndGetItems(){
        Item item1 = new Item("cooking pot ","kitchen" ,19.5,1.0 , "kitchen ");
        Item item2= new Item("spoon" ,"kitchen " , 3.99, 10.0 ,"kitchen");
        List<Item> items = new ArrayList<>(Arrays.asList(item1,item2));

        shoppingList.setItems(items);
        assertEquals(items ,shoppingList.getItems());
    }


    /**
     * Tests setting and getting the total price.
     */
    @Test
    void testSetAndGetTotalPrice() {
        shoppingList.setTotalPrice(15.75);
        assertEquals(15.75, shoppingList.getTotalPrice(), 0.01);
    }

}
