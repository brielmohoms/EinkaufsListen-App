package org.prog3.models;

import java.util.List;

/**
 * Represents a shopping list in the application
 */
public class ShoppingList {

    private int id;

    private String name;

    private List<Item> items;

    private double totalPrice;


    /**
     * default constructor
     */
    public ShoppingList() {
    }


    /**
     *Constructor
     *
     * @param name name of the shopping list
     */
    public ShoppingList(String name) {
        this.name = name;
    }


    /**
     * gets the Items
     * @return items
     */
    public List<Item> getItems() {
        return items;
    }


    /**
     * sets the Items
     *
     * @param items the Item of the shopping List
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }


    /**
     * gets the name of the shopping list
     *
     * @return the name of the shopping list
     */
    public String getName() {
        return name;
    }


    /**
     * sets the name of the shopping list
     *
     * @param name name of the shopping list
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     *gets the shopping list id
     *
     * @return the id of the shopping list
     */
    public int getId() {
        return id;
    }


    /**
     *sets the shopping list id
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * gets the totalPrice
     * @return the totalPrice
     */

    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * sets the totalPrice
     * @param totalPrice the total price
     */

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
