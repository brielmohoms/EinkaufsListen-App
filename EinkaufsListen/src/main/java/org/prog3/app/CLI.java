package org.prog3.app;

import org.prog3.controllers.ItemController;
import org.prog3.controllers.ShoppingListController;
import org.prog3.controllers.UserController;



import java.util.Scanner;

/**
 * Command line interface for the Einkaufslisten application
 */
public class CLI {
   UserController userController = new UserController();
    ItemController itemController = new ItemController();
    Scanner scanner = new Scanner(System.in);
    private boolean running = true;


    public void start() {
        while (running) {
            try {
                System.out.println("====SHOPPING APP====");
                System.out.println("1.Manage Users");
                System.out.println("2.Manage Items");
                System.out.println("3.Manage Shopping List");
                System.out.println("4.Exit");
                System.out.println("choose an option");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> menuUser();
                    case 2 -> startItemMenu();
                    case 3 -> menuShopping();
                    case 4 -> {
                        System.out.println("Bye");
                        running = false;
                    }
                    default -> System.out.println("Invalid Choice. Please try again");
                }
            } catch (Exception e) {
                System.out.println("an Error occured : " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    public void menuUser () {
        while (true) {
            try {
                System.out.println("=====USER MENU===== ");
                System.out.println("1. add User");
                System.out.println("2. delete User ");
                System.out.println("3. update Users ");
                System.out.println("4. consult Users ");
                System.out.println("5. find User by Id ");
                System.out.println("6. Go Back");
                System.out.println("choose an option");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> userController.createUser();
                    case 2 -> userController.deleteUser();
                    case 3 -> userController.updateUser();
                    case 4 -> userController.displayAllUsers();
                    case 5 -> userController.findUserById();
                    case 6 -> {
                        System.out.println("Bye");
                        start();
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




    public void menuShopping (){
        while (true){
            try{
                System.out.println("=====SHOPPING MENU===== ");
                System.out.println("1. consult the shopping list");
                System.out.println("2. add shopping list ");
                System.out.println("3. delete  shopping list ");
                System.out.println("4.Exit ");
                System.out.println("choose an option");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice){
                    case 1 -> ShoppingListController.viewShoppingList();
                    case 2 ->ShoppingListController.addShoppingList();
                    case 3 ->ShoppingListController.deleteShoppingList();
                    case 4 ->{
                        System.out.println("Bye");
                        start();
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
    public void startItemMenu () {
        while (true) {
            try{
                System.out.println("==== SHOPPING APP ====");
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
                        start();
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
