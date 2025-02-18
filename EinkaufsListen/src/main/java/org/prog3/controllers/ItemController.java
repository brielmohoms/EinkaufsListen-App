package org.prog3.controllers;

import org.prog3.models.Item;
import org.prog3.services.ItemService;

import java.util.List;
import java.util.Scanner;

/**
 * Controller class responsible for handling user interactions related to items.
 *
 * <p>
 * This class acts as an intermediary between the {@link ItemService} and the user,
 * processing input and forwarding requests to the service layer. It manages
 * operations such as adding, deleting, updating, and retrieving items.
 * </p>
 */
public class ItemController {

    private ItemService itemService = new ItemService();
    private Scanner scanner = new Scanner(System.in);

    /**
     * Default constructor
     */
    public ItemController () {
    }


    /**
     * Constructor used in the test classes
     *
     * @param itemService the itemService object
     * @param scanner the scanner object
     */
    public ItemController (ItemService itemService, Scanner scanner) {
        this.itemService = itemService;
        this.scanner = scanner;
    }


    /**
     * Adds an item in a specific shopping list
     */
    public void addItem () {
        System.out.print("Enter the shopping list name: ");
        String shoppingListName = scanner.next();
        System.out.println("Enter item category: ");
        String category = scanner.next();
        System.out.println("Enter item name: ");
        String name = scanner.next();
        System.out.println("Enter item price: ");
        double price = scanner.nextDouble();
        System.out.println("Enter item quantity: ");
        double quantity = scanner.nextDouble();
        scanner.nextLine();

        try {
            itemService.addItem(shoppingListName, name, category, price, quantity);
            System.out.println("✅ Item successfully added to your shopping list!");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    /**
     * Remove an item from a specific shopping list given its name
     */
    public void removeItem () {
        System.out.println("Enter item shopping list name: ");
        String shoppingListName = scanner.next();
        System.out.println("Enter item name: ");
        String name = scanner.next();
        System.out.println("Are you sure you want to delete the Item? YES/NO");
        String response = scanner.next();
        scanner.nextLine();

        if (response.equalsIgnoreCase("YES")) {
            try {
                itemService.deleteItemByName(shoppingListName, name);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("OK. Cancelled!");
        }
    }


    /**
     * Finds an item of a specific shopping list
     */
    public void findItemByName () {
        System.out.println("Enter item shopping list name: ");
        String shoppingListName = scanner.next();
        System.out.println("Enter item name: ");
        String name = scanner.next();
        scanner.nextLine();

        itemService.findItemByName(shoppingListName, name);
        System.out.println();
    }


    /**
     * Updates the quantity of an item in a specific shopping list
     */
    public void updateItemQuantity () {
        System.out.println("Enter item shopping list name: ");
        String shoppingListName = scanner.next();
        System.out.println("Enter item name: ");
        String name = scanner.next();
        System.out.println("Enter item new quantity: ");
        double quantity = scanner.nextDouble();
        scanner.nextLine();

        try {
            itemService.updateItemQuantity(shoppingListName, name, quantity);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    /**
     * Displays all the items of a specific shopping list
     */
    public void viewAllItemsOfShoppingList () {
        System.out.println("Enter shopping list name: ");
        String shoppingListName = scanner.next();
        List<Item> items = itemService.getAllItems(shoppingListName);
        scanner.nextLine();

        if (items.isEmpty()) {
            System.out.println("❌ No item found!");
        } else {
            System.out.println("====ITEMS====");
            items.forEach(System.out::println);
        }
    }


    /**
     * Deletes all the items of a specific shopping list
     */
    public void deleteAllItemsOfShoppingList () {
        System.out.println("Enter shopping list name: ");
        String shoppingListName = scanner.next();
        System.out.println("Are you sure you want to delete the Item? YES/NO");
        String response = scanner.next();
        scanner.nextLine();

        if (response.equalsIgnoreCase("YES")) {
            try {
                itemService.deleteAllItemsOfAShoppingList(shoppingListName);
                System.out.println("✅ All items deleted from the shopping list successfully.");
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("OK! Cancelled.");
        }
    }
}
