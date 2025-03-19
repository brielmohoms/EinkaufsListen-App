package org.prog3.app;

import org.prog3.controllers.ItemController;
import org.prog3.controllers.ShoppingListController;
import org.prog3.controllers.UserController;
import org.prog3.dao.ShoppingListDAO;
import org.prog3.services.ShoppingListService;
import org.prog3.services.UserService;

import java.util.Scanner;

/**
 * Command line interface for the Einkaufslisten application
 * <p>
 * This class provides an interactive console-based user interface for
 * managing shopping lists, items and user accounts.
 * </p>
 */
public class CLI {

    private final UserService userService = new UserService();
    private final ShoppingListDAO shoppingListDAO = new ShoppingListDAO();

    private final ShoppingListService shoppingListService = new ShoppingListService(shoppingListDAO, userService);
    // Inject the shared services into your controllers
    private final UserController userController = new UserController(userService,new Scanner(System.in));
    private final ShoppingListController shoppingListController = new ShoppingListController(shoppingListService, new Scanner(System.in));
    private final ItemController itemController = new ItemController();



    private final Scanner scanner = new Scanner(System.in);
    private boolean running = true;


    /**
     * Starts the CLI application and displays the menu.
     * <p>
     * This method keeps running until the user chooses to exit.
     * It provides options for user registration, login and exiting the application.
     * </p>
     */
    public void start() {
        while (running) {
            try {
                System.out.println("\n==================");
                System.out.println("\033[1;34m     WELCOME!  \033[0m");
                System.out.println("==================");
                System.out.println(" 1. Register");
                System.out.println(" 2. Login");
                System.out.println(" 3. Exit");
                System.out.println("\nChoose an option: ");
                String choice = getUserInput().trim();

                switch (choice) {
                    case "1" -> userController.createUser();
                    case "2" -> {
                        boolean isLoggedIn = userController.loginUser();
                        if (isLoggedIn) {
                            if (userController.isAdmin()) {
                                adminMenu();
                            } else {
                                userMenuShopping();
                            }
                        }
                    }
                    case "3" -> {
                        System.out.println("Bye!");
                        running = false;
                    }
                    default -> System.out.println("⚠️ Invalid Choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("❌ An error occurred : " + e.getMessage());
                scanner.nextLine();
            }
        }
    }


    public void adminMenu() {
        while (true) {
            try {
                displayMessage("\n\033[1;33m----- ADMIN MENU -----\033[0m");
                displayMessage(" 1. My Shopping Lists");
                displayMessage(" 2. Add a shopping list");
                displayMessage(" 3. Delete shopping list");
                displayMessage(" 4. Manage shopping list");
                displayMessage(" 5. Total price of shopping list");
                displayMessage(" 6. Manage Username and Password");
                displayMessage(" 7. Admin Panel");
                displayMessage(" 8. Logout");
                displayMessage("\nChoose an option: ");
                String choice = getUserInput().trim();

                switch (choice) {
                    case "1" -> shoppingListController.viewShoppingList();
                    case "2" -> shoppingListController.addShoppingList();
                    case "3" -> shoppingListController.deleteShoppingList();
                    case "4" -> startItemMenu();
                    case "5" -> shoppingListController.viewTotalPrice();
                    case "6" -> userController.updateUser();
                    case "7" -> adminPanel();
                    case "8" -> {
                        userController.logoutUser();
                        start();
                        return;
                    }
                    default -> System.out.println("⚠️ Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("❌ An error occurred: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    public void adminPanel (){
        while (true) {
            try {
                displayMessage("\n\033[1;35m----- ADMIN PANEL -----\033[0m");
                displayMessage(" 1. Display All Users");
                displayMessage(" 2. Find user by name");
                displayMessage(" 3. Delete a user");
                displayMessage(" 4. Promote a User to Admin");
                displayMessage(" 5. Back");
                displayMessage("\nChoose an option: ");
                String choice = getUserInput().trim();

                switch (choice) {
                    case "1" -> userController.displayAllUsers();
                    case "2" -> userController.findUser();
                    case "3" -> userController.deleteUser();
                    case "4" -> userController.promoteUserToAdmin();
                    case "5" -> {
                        return; // Go back to admin main menu
                    }
                    default -> System.out.println("⚠️ Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("❌ An error occurred: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }


    /**
     * Displays the shopping menu for logged-in users.
     * <p>
     * Users can manage shopping lists, view total prices, update account details,
     * and log out from their session.
     * </p>
     */
    public void userMenuShopping (){
        while (true){
            try{
                System.out.println("\n\033[1;36m----- SHOPPING MENU -----\033[0m");
                System.out.println(" 1. View Shopping Lists");
                System.out.println(" 2. Add Shopping List");
                System.out.println(" 3. Delete Shopping List");
                System.out.println(" 4. Manage Shopping List");
                System.out.println(" 5. Total price of Shopping List");
                System.out.println(" 6. Manage username and password");
                System.out.println(" 7. Logout");
                System.out.println("\nChoose an option: ");
                String choice = getUserInput().trim();

                switch (choice){
                    case "1" -> shoppingListController.viewShoppingList();
                    case "2" -> shoppingListController.addShoppingList();
                    case "3" -> shoppingListController.deleteShoppingList();
                    case "4" -> startItemMenu();
                    case "5" -> shoppingListController.viewTotalPrice();
                    case "6" -> userController.updateUser();
                    case "7" -> {
                        userController.logoutUser();
                        start();
                        return;
                    }
                    default -> System.out.println("⚠️ Invalid choice. Please try again");
                }
            } catch (Exception e) {
                System.err.println("❌ An error occurred " + e.getMessage());
                scanner.nextLine(); // Consume invalid input
            }
        }
    }


    /**
     * Displays the item management menu.
     * <p>
     * Users can add, delete, search, update quantity and view items of a shopping list.
     * They can also delete all items of a shopping list.
     * </p>
     */
    public void startItemMenu () {
        while (true) {
            try{
                System.out.println("\n\033[1;36m----- MANAGE SHOPPING LISTS -----\033[0m");
                System.out.println(" 1. Add Item");
                System.out.println(" 2. Delete Item");
                System.out.println(" 3. Find item by it's name");
                System.out.println(" 4. Update item quantity");
                System.out.println(" 5. View all items in a shopping list");
                System.out.println(" 6. Deletes all items in a shopping list");
                System.out.println(" 7. Back");
                System.out.println("\nChoose an option: ");
                String choice = getUserInput().trim();

                switch (choice) {
                    case "1" -> itemController.addItem();
                    case "2" -> itemController.deleteItem();
                    case "3" -> itemController.findItemByName();
                    case "4" -> itemController.updateItemQuantity();
                    case "5" -> itemController.viewAllItemsOfShoppingList();
                    case "6" -> itemController.deleteAllItemsOfShoppingList();
                    case "7" -> {
                        return; // Go back to the shopping menu
                    }
                    default -> System.out.println("⚠️ Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("❌ An error occurred " + e.getMessage());
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    /**
     * Requests a line of text from the user.
     *
     * @return the text of the user
     */
    public String getUserInput() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }

}
