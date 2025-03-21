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
    private Scanner scanner = new Scanner(System.in);

    /**
     * Default constructor
     */
    public ItemController () {
    }

    /**
     * Constructor for dependency injection
     *
     * @param shoppingListDAO the shopping list data access object
     */
    public ItemController(ShoppingListDAO shoppingListDAO) {
    }

    /**
     * Constructor used in testing or when custom dependencies are needed.
     *
     * @param itemService the itemService object
     * @param scanner the scanner object
     */
    public ItemController (ItemService itemService, Scanner scanner) {
        this.itemService = itemService;
        this.scanner = scanner;
    }


    /**
     * Adds an item to a specified shopping list.
     *
     * <p>
     * Prompts the user for the shopping list name, item category, name, price, and quantity,
     * then attempts to add the item using the service layer.
     * </p>
     */
    public void addItem () {
        System.out.print("\nEnter the shopping list name: ");
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
     * Deletes a specific item from a shopping list by its name.
     *
     * <p>
     * Prompts the user for the shopping list name and item name, then confirms the deletion.
     * </p>
     */
    public void deleteItem () {
        System.out.println("\nEnter item shopping list name: ");
        String shoppingListName = scanner.nextLine();

        System.out.println("\nEnter item name: ");
        String name = scanner.nextLine();

        System.out.println("\nAre you sure you want to delete the Item? YES/NO");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("YES")) {
            if (itemService.deleteItemByName(shoppingListName, name)) {
                System.out.println("✅ Item " + name + " deleted successfully.");
            } else {
                System.out.println("❌ Item " + name + " not found.");
            }
        } else {
            System.out.println("You choose not to delete the item. Item not deleted.");
        }
    }


    /**
     * Finds and displays an item in a specified shopping list by its name.
     *
     * <p>
     * Prompts the user for the shopping list name and item name, then delegates the search to the service layer.
     * </p>
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
     * Updates the quantity of a specific item in a shopping list.
     *
     * <p>
     * Prompts the user for the shopping list name, item name, and new quantity,
     * then attempts to update the item using the service layer.
     * </p>
     */
    public void updateItemQuantity () {
        System.out.println("\nEnter item shopping list name: ");
        String shoppingListName = scanner.nextLine();

        System.out.println("\nEnter item name: ");
        String name = scanner.nextLine();

        System.out.println("\nEnter item new quantity: ");
        double newQuantity = scanner.nextDouble();
        scanner.nextLine();

        if (itemService.updateItemQuantity(shoppingListName, name, newQuantity)) {
            System.out.println("✅ Quantity updated for " + name + " to " + newQuantity);
        } else {
            System.out.println("❌ Error: Item not found or update failed");
        }
    }


    /**
     * Displays all items in a specified shopping list.
     *
     * <p>
     * Prompts the user for the shopping list name and displays the list of items along with their details.
     * </p>
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
     * Deletes all items from a specified shopping list.
     *
     * <p>
     * Prompts the user for the shopping list name and confirms the deletion before removing all items.
     * </p>
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
