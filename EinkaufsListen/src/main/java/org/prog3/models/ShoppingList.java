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
     *
     */
    public ShoppingList() {
    }


    /**
     *
     *
     * @param name
     */
    public ShoppingList(String name) {
        this.name = name;
    }


    /**
     *
     * @return
     */
    public List<Item> getItems() {
        return items;
    }


    /**
     *
     *
     * @param items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }


    /**
     *
     *
     * @return
     */
    public String getName() {
        return name;
    }


    /**
     *
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     *
     *
     * @return
     */
    public int getId() {
        return id;
    }


    /**
     *
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
