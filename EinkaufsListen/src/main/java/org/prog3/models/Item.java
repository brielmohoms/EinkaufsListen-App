package org.prog3.models;


/**
 * Represents an item in the application
 */
public class Item {

    private int id;
    private String name;
    private String category;
    private double price;
    private int quantity;

    /**
     * Constructor
     *
     * @param id the item id
     * @param name the item name
     * @param category the item category
     * @param price the item price
     * @param quantity the item quantity
     */
    public Item(int id, String name, String category, double price, int quantity){
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * gets the item id
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * sets the item id
     *
     * @param id the item id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * gets the item name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the item name
     *
     * @param name the item name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the item category
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * sets the item category
     *
     * @param category the item category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * gets the item price
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * sets the item price
     *
     * @param price the item price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * gets the item quantity
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * sets the item quantity
     *
     * @param quantity the item quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
