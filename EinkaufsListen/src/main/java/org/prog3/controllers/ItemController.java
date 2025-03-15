package org.prog3.controllers;

import org.prog3.dao.ShoppingListDAO;
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
    private ShoppingListDAO shoppingListDAO = new ShoppingListDAO();
    private Scanner scanner = new Scanner(System.in);

    /**
     * Default constructor
     */
    public ItemController () {
    }

    public ItemController(ShoppingListDAO shoppingListDAO) {
        this.shoppingListDAO = shoppingListDAO;
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
        System.out.print("Enter the shopping list name: \n");
        String shoppingListName = scanner.nextLine();

        System.out.println("\nEnter item category: ");
        String category = scanner.nextLine();

        System.out.println("\nEnter item name: ");
        String name = scanner.nextLine();

        System.out.println("\nEnter item price: ");
        double price = scanner.nextDouble();

        System.out.println("\nEnter item quantity: ");
        double quantity = scanner.nextDouble();
        scanner.nextLine();

        if (itemService.addItem(shoppingListName, name, category, price, quantity)) {
            System.out.println("✅ Item successfully added to your shopping list!");
        } else {
            System.out.println("❌ Failed to add item. Please try again.");
        }
    }


    /**
     * Remove an item from a specific shopping list given its name
     */
    public void deleteItem () {
        System.out.println("Enter item shopping list name: ");
        String shoppingListName = scanner.nextLine();
        System.out.println("\nEnter item name: ");
        String name = scanner.nextLine();
        System.out.println("Are you sure you want to delete the Item? YES/NO");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("YES")) {
            if (itemService.deleteItemByName(shoppingListName, name)) {
                System.out.println("✅ Item " + name + " deleted successfully.");
            } else {
                System.out.println("❌ Item " + name + " not found.");
            }
        } else {
            System.out.println("OK. Cancelled!");
        }
    }


    /**
     * Finds an item of a specific shopping list
     */
    public void findItemByName () {
        System.out.println("\nEnter item shopping list name: ");
        String shoppingListName = scanner.nextLine();

        System.out.println("\nEnter item name: ");
        String name = scanner.nextLine();

        itemService.findItemByName(shoppingListName, name);
        System.out.println();
    }


    /**
     * Updates the quantity of an item in a specific shopping list
     */
    public void updateItemQuantity () {
        System.out.println("\nEnter item shopping list name: ");
        String shoppingListName = scanner.nextLine();
        System.out.println("\nEnter item name: ");
        String name = scanner.nextLine();
        System.out.println("\nEnter item new quantity: ");
        double newQuantity = scanner.nextDouble();

        if(itemService.updateItemQuantity(shoppingListName, name, newQuantity)) {
            System.out.println("✅ Quantity updated for " + name + " to " + newQuantity);
        } else {
            System.out.println("❌ Error: Item not found or update failed");
        }
    }


    /**
     * Displays all the items of a specific shopping list
     */
    public void viewAllItemsOfShoppingList () {
        System.out.println("\nEnter shopping list name: ");
        String shoppingListName = scanner.nextLine();
        List<Item> items = itemService.getAllItems(shoppingListName);

        if (items.isEmpty()) {
            System.out.println("❌ No item found!");
        } else {
            System.out.println("\n\033[1;33m---------------------- Items of " + shoppingListName + " ----------------------\033[0m");
            for(Item item : items) {
                System.out.println("Name: " + item.getName() + " | Category: " + item.getCategory() +
                        " | Price: " + item.getPrice() + " | Quantity: " + item.getQuantity());
            }
            System.out.println("\033[1;33m----------------------------------------------------------\033[0m");
        }
    }


    /**
     * Deletes all the items of a specific shopping list
     */
    public void deleteAllItemsOfShoppingList () {
        System.out.println("\nEnter shopping list name: ");
        String shoppingListName = scanner.nextLine();
        System.out.println("\nAre you sure you want to delete the Item? YES/NO");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("YES")) {
            itemService.deleteAllItemsOfAShoppingList(shoppingListName);
            System.out.println("✅ All items deleted from the shopping list successfully.");
        } else {
            System.out.println("OK! Cancelled.");
        }
    }
}
