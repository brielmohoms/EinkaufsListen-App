package org.prog3.app;

import org.prog3.controllers.ItemController;
import org.prog3.controllers.ShoppingListController;
import org.prog3.controllers.UserController;
import org.prog3.models.ShoppingList;
import org.prog3.models.User;
import org.prog3.services.ShoppingListService;
import org.prog3.services.UserService;


import java.util.Scanner;

/**
 * Command line interface for the Einkaufslisten application
 */
public class CLI {

    UserController userController = new UserController();
    ItemController itemController = new ItemController();
    ShoppingListController shoppingListController = new ShoppingListController();

    Scanner scanner = new Scanner(System.in);
    private boolean running = true;


    /**
     *
     */
    public void start() {
        while (running) {
            try {
                System.out.println("==== MENU ====");
                System.out.println("1. New user");
                System.out.println("2. Existing user");
                System.out.println("3. Exit");
                System.out.println("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> userController.createUser();
                    case 2 -> {
                        boolean isLoggedIn = userController.loginUser();
                        if (isLoggedIn) {
                            menuShopping();
                        }
                    }
                    case 3 -> {
                        System.out.println("Bye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid Choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred : " + e.getMessage());
                scanner.nextLine();
            }
        }
    }



    /**
     *
     */
    /*public void menuUser () {
        while (true) {
            try {
                System.out.println("=====USER MENU===== ");
                System.out.println("1. Add user");
                System.out.println("2. Delete user ");
                System.out.println("3. Update users ");
                System.out.println("4. Consult users ");
                System.out.println("5. Find user by Id ");
                System.out.println("6. EXIT");
                System.out.println("choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> userController.createUser();
                    case 2 -> userController.deleteUser();
                    case 3 -> userController.updateUser();
                    case 4 -> userController.displayAllUsers();
                    case 5 -> userController.findUserById();
                    case 6 -> {
                        System.out.println("Bye!");
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

    }*/


    /**
     *
     */
    public void menuShopping (){
        while (true){
            try{
                System.out.println("===== SHOPPING MENU ===== ");
                System.out.println("1. View Shopping Lists");
                System.out.println("2. Add Shopping List");
                System.out.println("3. Delete Shopping List");
                System.out.println("4. Manage Shopping List");
                System.out.println("5. Logout");
                System.out.println("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice){
                    case 1 -> shoppingListController.viewShoppingList();
                    case 2 -> shoppingListController.addShoppingList();
                    case 3 -> shoppingListController.deleteShoppingList();
                    case 4 -> startItemMenu();
                    case 5 -> {
                        userController.logoutUser();
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


    /**
     *
     */
    public void startItemMenu () {
        while (true) {
            try{
                System.out.println("==== MANAGE SHOPPING LIST ====");
                System.out.println("1. Add Item");
                System.out.println("2. Delete Item");
                System.out.println("3. Find item by it's name");
                System.out.println("4. Update item quantity");
                System.out.println("5. View all items in a shopping list");
                System.out.println("6. Deletes all items in a shopping list");
                System.out.println("7. Back");
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
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("An error occurred " + e.getMessage());
                scanner.nextLine();
            }
        }
    }
}