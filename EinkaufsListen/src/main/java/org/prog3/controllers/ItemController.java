package org.prog3.controllers;

import org.prog3.models.Item;
import org.prog3.services.ItemService;

import java.util.List;
import java.util.Scanner;

public class ItemController {

    private final ItemService itemService = new ItemService();
    private final Scanner scanner = new Scanner(System.in);

    /**
     * adds an item in a specific shopping list
     */
    public void addItem() {
        System.out.println("Enter item shopping list ID: ");
        int shoppingListId = scanner.nextInt();
        System.out.println("Enter item category: ");
        String category = scanner.next();
        System.out.println("Enter item name: ");
        String name = scanner.next();
        System.out.println("Enter item price: ");
        double price = scanner.nextDouble();
        System.out.println("Enter item quantity: ");
        double quantity = scanner.nextDouble();

        try{
            itemService.addItem(shoppingListId, name, category, price, quantity);
            System.out.println("Item successfully added to your shopping list!");
        } catch (IllegalArgumentException e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * remove an item from a specific shopping list given its name
     */
    public void removeItem() {
        System.out.println("Enter item shopping list ID: ");
        int shoppingListId = scanner.nextInt();
        System.out.println("Enter item name: ");
        String name = scanner.next();
        System.out.println("Are you sure you want to delete the Item? YES/NO");
        String response = scanner.next();

        if(response.equalsIgnoreCase("YES")) {
            try{
                itemService.deleteItemByName(shoppingListId, name);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Cancelled!");
        }
    }

    /**
     * finds an item of a specific shopping list
     */
    public void findItemByName() {
        System.out.println("Enter item shopping list ID: ");
        int shoppingListId = scanner.nextInt();
        System.out.println("Enter item name: ");
        String name = scanner.next();
        scanner.nextLine();

        itemService.findItemByName(shoppingListId, name);
        System.out.println();
    }

    /**
     * updates the quantity of an item in a specific shopping list
     */
    public void updateItemQuantity() {
        System.out.println("Enter item shopping list ID: ");
        int shoppingListId = scanner.nextInt();
        System.out.println("Enter item name: ");
        String name = scanner.next();
        System.out.println("Enter item new quantity: ");
        double quantity = scanner.nextDouble();

        try{
            itemService.updateItemQuantity(shoppingListId, name, quantity);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * displays all the items of a specific shopping list
     */
    public void viewAllItemsOfShoppingList(){
        System.out.println("Enter shopping list ID: ");
        int shoppingListId = scanner.nextInt();
        List<Item> items = itemService.getAllItems(shoppingListId);
        if(items.isEmpty()){
            System.out.println("No item found!");
        } else {
            System.out.println("====ITEMS====");
            items.forEach(System.out::println);
        }
    }

    /**
     * deletes all the items of a specific shopping list
     */
    public void deleteAllItemsOfShoppingList() {
        System.out.println("Enter shopping list ID: ");
        int shoppingListId = scanner.nextInt();
        scanner.nextLine();
        try {
            itemService.deleteAllItemsOfAShoppingList(shoppingListId);
            System.out.println("All items deleted from the shopping list successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
