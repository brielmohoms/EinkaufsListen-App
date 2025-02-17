package org.prog3.controllers;

import org.prog3.models.ShoppingList;
import org.prog3.services.ShoppingListService;

import java.util.List;
import java.util.Scanner;

public class ShoppingListController {

    private ShoppingListService shoppingListService = new ShoppingListService();
    private Scanner scanner = new Scanner(System.in) ;


    /**
     *
     */
    public void viewShoppingList() {
        try {
            List<ShoppingList> shoppingLists = shoppingListService.getAllShoppingLists();
            for (ShoppingList list : shoppingLists){
                System.out.println("ID :" + list.id + "Name : " + list.name);
            }
        } catch (Exception e){
            System.out.println("Error fetching shopping lists: " + e.getMessage());
        }
    }


    /**
     *
     */
    public void addShoppingList() {
        System.out.println("Enter a user name: ");
        String userName = scanner.next();
        System.out.println("Enter a shopping list name: ");
        scanner.nextLine();

        String name = scanner.next();
        try {
            shoppingListService.addShoppingList(userName, name);
            System.out.println("shopping list added successfully.");
        } catch (Exception e){
            System.out.println("Error adding shopping list: " + e.getMessage());
        }
    }


    /**
     *
     */
    public void deleteShoppingList() {
        System.out.print("Enter shopping list ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            shoppingListService.deleteShoppingListById(id);
            System.out.println("Shopping list deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting shopping list: " + e.getMessage());
        }
    }
}
