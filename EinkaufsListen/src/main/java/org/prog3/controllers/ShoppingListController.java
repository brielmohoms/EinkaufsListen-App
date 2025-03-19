package org.prog3.controllers;

import org.prog3.models.ShoppingList;
import org.prog3.services.ShoppingListService;

import java.util.List;
import java.util.Scanner;

/**
 * Controller class responsible for handling user interactions related to shopping lists .
 *
 * <p>
 * This class communicates with the ShoppingListService to perform operations such as adding, deleting, and viewing shopping lists.
 * It manages user inputs and displays the corresponding outputs.
 * </p>
 */

public class ShoppingListController {

    private final ShoppingListService shoppingListService;
    private Scanner scanner = new Scanner(System.in) ;

    /**
     * Constructs a new ShoppingListController with the specified service and input scanner.
     *
     * @param shoppingListService the service layer for handling shopping list operations
     * @param scanner the scanner object for reading user input
     */
    public ShoppingListController(ShoppingListService shoppingListService, Scanner scanner) {
        this.shoppingListService = shoppingListService;
        this.scanner = scanner;
    }


    /**
     * Displays all shopping lists along with their total prices.
     *
     * <p>
     * If no shopping lists are found, a corresponding message is shown.
     * </p>
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
     * Prompts the user to add a new shopping list.
     *
     * <p>
     * Reads the shopping list name from the user and attempts to add it via the service layer.
     * </p>
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
     * Prompts the user to delete an existing shopping list.
     *
     * <p>
     * Reads the shopping list name from the user and attempts deletion via the service layer.
     * </p>
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
     * Displays the total price of a specified shopping list.
     *
     * <p>
     * Prompts the user for the shopping list name and then retrieves and displays the total price.
     * </p>
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
