package org.prog3.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

/**
 Utility class for initializing the database schema and inserting initial data.
 *
 * <p>
 * This class is responsible for creating necessary tables and populating them
 * It ensures that the database is properly set up before application startup.
 * </p>
 */
class DatabaseInitializerTest {

    @BeforeEach
    void setUp() throws Exception {
        DatabaseInitializer.initialise();
        try(Connection connection= DatabaseConnection.getConnection();
            Statement statement = connection.createStatement()){
            statement.executeUpdate("DELETE FROM User");// clears the database before every test
        }
    }


    /**
     * Test for the User Table
     *
     * @throws SQLException
     */
    @Test
    void testUserTable() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet resultSet= stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='User'");
            assertTrue(resultSet.next(),"Table User was created");

            String insertQueryUser="INSERT INTO User (name,username,password,role) VALUES('Noel','Van24','No12','admin')";
            stmt.executeUpdate(insertQueryUser);
            ResultSet insertDataUser = stmt.executeQuery("SELECT * FROM User WHERE name = 'Noel'");
            assertTrue(insertDataUser.next(), " Insertion failed ");
        }

    }


    /**
     * Test for the ShoppingList table
     * @throws SQLException
     */
    @Test
    void testShoppingListTable () throws SQLException{
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet resultSet= stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='ShoppingList'");
            assertTrue(resultSet.next(),"Table ShoppingList was created");

            String insertQueryShoppingList ="INSERT INTO ShoppingList (name, username) VALUES('Fruits', 'TestUser')";
            stmt.executeUpdate(insertQueryShoppingList);
            ResultSet insertDataShoppingList= stmt.executeQuery("SELECT * FROM ShoppingList WHERE name = 'Fruits'");
            assertTrue(insertDataShoppingList.next(), " Insertion failed ");
        }

    }


    /**
     * Test for the Item Table
     * @throws SQLException
     */
    @Test
    void testItemTable() throws SQLException{
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet resultSet= stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='Item'");
            assertTrue(resultSet.next(),"Table Item was created");

            String insertQueryItem ="INSERT INTO Item (shopping_list_name,category,name,price,quantity) VALUES('Fruits','Kitchen','apples','2.99','3')";
            stmt.executeUpdate(insertQueryItem);
            ResultSet insertDataItem = stmt.executeQuery("SELECT * FROM Item WHERE category = 'kitchen'");
            assertFalse(insertDataItem.next(), " Insertion failed ");
        }
    }

}
