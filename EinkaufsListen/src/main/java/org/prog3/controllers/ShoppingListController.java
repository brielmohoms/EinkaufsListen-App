package org.prog3.controllers;

import org.prog3.dao.ShoppingListDAO;
import org.prog3.models.ShoppingList;
import org.prog3.services.ShoppingListService;

import java.util.List;
import java.util.Scanner;

public class ShoppingListController {

    private static ShoppingListService shoppingListService;
    private static final Scanner scanner = null;


    /**
     *
     */
    public ShoppingListController() {
        //this.shoppingListService = shoppingListService;
        //this.scanner = new Scanner(System.in);
    }


    /**
     *
     */
    public static  void viewShoppingList(){
        try {
            List<ShoppingList> shoppingLists = ShoppingListService.getAllShoppingLists();
            for (ShoppingList list : shoppingLists){
                System.out.println("ID :" +list.id + "Name : " + list.name);
            }
        }catch (Exception e ){
            System.out.println("Error fetching shopping lists : " +e.getMessage());
        }
    }


    /**
     *
     */
    public static void addShoppingList(){
        System.out.println("Enter a shopping list name : ");
        String name = scanner.nextLine();
        try {
            shoppingListService.addShoppingList(name);
            System.out.println("shopping list added succesfully .");
        }catch (Exception e){
            System.out.println("Error adding shopping list  " + e.getMessage());
        }
    }


    /**
     *
     */
    public static void deleteShoppingList() {
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
