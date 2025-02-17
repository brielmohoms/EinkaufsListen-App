package org.prog3.models;

import java.util.List;

/**
 * Represents a shopping list in the application
 */
public class ShoppingList {

    private double totalPrice ;
    public List<Item> items ;
    public String name ;
    public int id;
    private String userName ;

    public ShoppingList(String userName, String name) {
        this.userName = userName;
        this.name = name ;
    }

    public ShoppingList() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     *
     * @param item
     */
    public void deleteShoppingList (Item item){
        if (items.contains(item)) {
            items.remove(item);
            updateTotalPrice();
        }
    }


    /**
     *
     *
     * @param oldItem
     * @param newItem
     */
    public void updateShoppingList (Item oldItem , Item newItem){
        int index = items.indexOf(oldItem);
        if (index != -1) {
            items.set(index, newItem);
            updateTotalPrice();
        }
    }


    /**
     *
     */
    private  void updateTotalPrice(){
        totalPrice=0.0;
        for (Item item : items ){
            totalPrice += item.getPrice() * item.getQuantity();
        }
    }
}
