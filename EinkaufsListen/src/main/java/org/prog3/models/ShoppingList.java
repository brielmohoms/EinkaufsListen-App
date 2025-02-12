package org.prog3.models;

import org.prog3.dao.ShoppingListDAO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a shopping list in the application
 */
public class ShoppingList {

    private double totalPrice ;
    public List<Item> items ;
    public String name ;
    public int id;


    /**
     * Constructor to initialize the  ShoppingList
     *
     * @param id
     * @param name
     */
    public ShoppingList( int id , String name) {
        this.id =id ;
        this.name = name ;
        this.items= new ArrayList<>();
        this.totalPrice=0.0;
    }


    /**
     * add an item to the Shopping List
     *
     * @param item
     */
    public void addShoppingList (Item item){
        if (item!=null){
            items.add(item);
            updateTotalPrice();
        }

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


    /**
     *
     */
    public void display (){
        System.out.println("Shopping List : " + name );
        for(Item item : items ){
            System.out.println(item.getName() + " Quantity : " +item.getQuantity() + "; Price " + item.getPrice());
        }
        System.out.println( "Total Price = " + totalPrice);
    }

    /**
     *
     *
     * @param dao
     * @return
     * @throws Exception
     */
    public List<Item> getItems(ShoppingListDAO dao) throws Exception {
        return dao.getItems(this.id);
    }

}
