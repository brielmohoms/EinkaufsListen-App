package org.prog3.app;

import org.prog3.controllers.ItemController;
import org.prog3.controllers.ShoppingListController;

import java.util.Scanner;


/**
 * Command line interface for the Einkaufslisten application
 */
public class CLI {

    ItemController itemController = new ItemController();
    Scanner scanner = new Scanner(System.in);

    /**
     *
     */
    public void start() {
        while (true){
            try{
                System.out.println("====SHOPPING APP====");
                System.out.println("1. Manage Items");
                System.out.println("2. Manage Shopping List");
                System.out.println("3. Exit");
                System.out.println("Choose an option");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice){
                    case 1 -> startItemMenu();
                    case 2 -> menuShopping();
                    case 3 -> {
                        System.out.println("Bye");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Invalid Choice. Please try again");
                }
            } catch (Exception e){
                System.out.println("An error occurred : " + e.getMessage());
                scanner.nextLine();
            }
        }
    }


    /**
     *
     */
    public void menuShopping() {
        while (true){
            try{
                System.out.println("=====SHOPPING MENU===== ");
                System.out.println("1. Consult the shopping list");
                System.out.println("2. Add shopping list ");
                System.out.println("3. Delete  shopping list ");
                System.out.println("4. Exit ");
                System.out.println("Choose an option");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice){
                    case 1 -> ShoppingListController.viewShoppingList();
                    case 2 -> ShoppingListController.addShoppingList();
                    case 3 -> ShoppingListController.deleteShoppingList();
                    case 4 -> {
                        System.out.println("Bye");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again");
                }
            } catch (Exception e) {
                System.err.println("An error occurred " + e.getMessage());
                scanner.nextLine();
            }
        }
    }


    /**
     *
     */
    public void startItemMenu() {
        while (true) {
            try {
                System.out.println("==== ITEM MENU ====");
                System.out.println("1. Add Item");
                System.out.println("2. Delete Item");
                System.out.println("3. Find item by it's name");
                System.out.println("4. Update item quantity");
                System.out.println("5. View all items in a shopping list");
                System.out.println("6. Deletes all items in a shopping list");
                System.out.println("7. Exit");
                System.out.println("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> itemController.addItem();
                    case 2 -> itemController.removeItem();
                    case 3 -> itemController.findItemByName();
                    case 4 -> itemController.updateItemQuantity();
                    case 5 -> itemController.viewAllItemsOfShoppingList();
                    case 6 -> itemController.deleteAllItemsOfShoppingList();
                    case 7 -> {
                        System.out.println("Bye");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again");
                }
            } catch (Exception e) {
                System.err.println("An error occurred " + e.getMessage());
                scanner.nextLine();
            }
        }
    }
}
