package org.prog3.controllers;

import org.prog3.models.ShoppingList;
import org.prog3.services.ShoppingListService;

import java.util.List;
import java.util.Scanner;

/**
 *Controller Class responsible for handling user interactions related to shopping lists .
 *
 * <p>
 * This class serves as an intermediary between the {@link ShoppingListService},
 * It manages operations such as adding , deleting ,viewing shopping lists .
 * </p>
 */

public class ShoppingListController {

    private ShoppingListService shoppingListService;
    private Scanner scanner = new Scanner(System.in) ;

    /**
     *constructs a new ShoppingListController with a specified service
     *
     * @param shoppingListService the service layer for handling shopping list operations
     * @param scanner the scanner object
     */

    public ShoppingListController(ShoppingListService shoppingListService, Scanner scanner) {
        this.shoppingListService = shoppingListService;
        this.scanner = scanner;
    }


    /**
     * views and displays a shopping list
     */
    public void viewShoppingList () {
        try {
            List<ShoppingList> shoppingLists = shoppingListService.getAllShoppingLists();
            if (shoppingLists.isEmpty()) {
                System.out.println("❌ No shopping lists found.");
                return;
            }
            for (ShoppingList list : shoppingLists){
                System.out.println("Name: " + list.getName() + " | Total price: " + shoppingListService.getTotalPrice(list.getName()));
            }
        } catch (Exception e) {
            System.out.println("❌ Error fetching shopping lists: " + e.getMessage());
        }
    }


    /**
     * Adds a new shopping list
     */
    public void addShoppingList() {
        System.out.println("\nEnter a shopping list name: ");
        String name = scanner.nextLine();

        try {
            shoppingListService.addShoppingList(name);
            System.out.println("✅ Shopping list added successfully.");
        } catch (Exception e){
            System.out.println("❌ Error adding shopping list: " + e.getMessage());
        }
    }


    /**
     *deletes a shopping list
     */
    public void deleteShoppingList() {
        System.out.print("\nEnter shopping list name to delete: ");
        String name = scanner.nextLine();

        try {
            shoppingListService.deleteShoppingListByName(name);
            System.out.println("✅ Shopping list deleted successfully.");
        } catch (Exception e) {
            System.out.println("❌ Error deleting shopping list: " + e.getMessage());
        }
    }


    /**
     * viewing the totalPrice
     */
    public void viewTotalPrice() {
        System.out.println("\n Enter shopping list name: ");
        String shoppingListName = scanner.nextLine();

        try {
            double totalPrice = shoppingListService.getTotalPrice(shoppingListName);
            System.out.printf("Total price of '%s' is: €%.2f%n", shoppingListName, totalPrice);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

}
