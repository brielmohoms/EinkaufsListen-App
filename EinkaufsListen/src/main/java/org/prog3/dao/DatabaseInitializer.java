package org.prog3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;


/**
 * A utility class that is responsible for defining and setting up the database schema when the application starts.
 * The class ensures that the required database structure exists so the application can store and retrieve data.
 * Initializes the database schema
 */
public class DatabaseInitializer {

    public static void initialise() {

        String url = "jdbc:sqlite:C:/Users/briel/EinkaufsListen-App/EinkaufsListen/shopping_app.db";

        try {
            Class.forName("org.sqlite.JDBC");

            try (Connection conn = DriverManager.getConnection(url)) {
                DSLContext create = DSL.using(conn);

                //User Table
                create.createTableIfNotExists("User")
                        .column("id", SQLDataType.INTEGER.identity(true))
                        .column("username", SQLDataType.VARCHAR.length(255).nullable(false))
                        .column("email", SQLDataType.VARCHAR.length(255).nullable(false))
                        .constraint(DSL.constraint("PK_User").primaryKey("id"))
                        .execute();

                //ShoppingList Table
                create.createTableIfNotExists("ShoppingList")
                        .column("id", SQLDataType.INTEGER.identity(true))
                        .column("name", SQLDataType.VARCHAR.length(255).nullable(false))
                        .column("user_id", SQLDataType.INTEGER.nullable(false))
                        .constraint(DSL.constraint("PK_ShoppingList").primaryKey("id"))
                        .constraint(DSL.constraint("FK_User").foreignKey("user_id").references("User", "id"))
                        .execute();

                //Item Table (linked to ShoppingList)
                create.createTableIfNotExists("Item")
                        .column("id", SQLDataType.INTEGER.identity(true))
                        .column("shopping_list_id", SQLDataType.INTEGER.nullable(false))
                        .column("category", SQLDataType.VARCHAR.length(255).nullable(false))
                        .column("name", SQLDataType.VARCHAR.length(255).nullable(false))
                        .column("price", SQLDataType.DOUBLE.precision(10, 2).nullable(false))
                        .column("quantity", SQLDataType.INTEGER.nullable(false))
                        .constraint(DSL.constraint("PK_Item").primaryKey("id"))
                        .constraint(DSL.constraint("FK_ShoppingList").foreignKey("shopping_list_id").references("ShoppingList", "id"))
                        .execute();

                System.out.println("✅ Database and tables initialized successfully.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ SQLite JDBC Driver not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ ERROR while connecting to the database: " + e.getMessage());
        }
    }
}