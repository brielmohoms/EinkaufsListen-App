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
            if (shoppingLists.isEmpty()) {
                System.out.println("No shopping lists found.");
                return;
            }
            for (ShoppingList list : shoppingLists){
                System.out.println("ID: " + list.getId() + " | Name: " + list.getName());
            }
        } catch (Exception e){
            System.out.println("Error fetching shopping lists: " + e.getMessage());
        }
    }


    /**
     *
     */
    public void addShoppingList() {
        System.out.println("\nEnter a shopping list name: ");
        String name = scanner.nextLine();

        try {
            shoppingListService.addShoppingList(name);
            System.out.println("Shopping list added successfully.");
        } catch (Exception e){
            System.out.println("Error adding shopping list: " + e.getMessage());
        }
    }


    /**
     *
     */
    public void deleteShoppingList() {
        System.out.print("\nEnter shopping list name to delete: ");
        String name = scanner.nextLine();

        try {
            shoppingListService.deleteShoppingListByName(name);
            System.out.println("Shopping list deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting shopping list: " + e.getMessage());
        }
    }
}
