package org.prog3.models;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a shopping list in the application
 */
public class ShoppingList {
    private double totalPrice ;
    public List<Item> items ;

    /**
     *
     * @param totalPrice the Price of all Items
     */

    // Constructor to initialize the  ShoppingList
    public ShoppingList(double totalPrice) {
        this.totalPrice = totalPrice;
        items = new LinkedList<>();
    }

    // add an item to the Shopping List
    public void addItem (Item item){
        items.add(item);
        updateTotalPrice();
    }

    // remove an item to the Shopping List
    public void deleteItem (Item item) {
        items.remove(item);
        updateTotalPrice();
    }

    // update the quantity or price of an existing item in the list
    public void updateItem (Item item){
    // Find the item in the list
        for ( int i = 0 ; i < items.size();i++){
            Item currentItem = items.get(i);
            if (currentItem.getName().equals(item.getName())){
                // update the item Details
                currentItem.setQuantity(item.getQuantity());
                currentItem.setPrice(item.getPrice());
                updateTotalPrice();
                return;// Exit after updating
            }

        }
        // If the items doesn't exist , add it to the list
        addItem(item);

    }
    private  void updateTotalPrice (){
        totalPrice=0;
        for (Item item : items ){
            totalPrice += item.getPrice() * item.getQuantity();
        }
    }
    // display the shoppingList and total Price
   public void display (){
       System.out.println("Shopping List : ");
       for(Item item : items ){
           System.out.println(item.getName() + "Quantity : " +item.getQuantity() + "; Price " + item.getPrice());
       }
       System.out.println( "Total Price = " + totalPrice);
   }

}
