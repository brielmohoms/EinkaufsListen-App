package org.prog3.dao;

import java.sql.Connection;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

/**
 * A utility class responsible for defining and setting up the database schema.
 * This class ensures that all required tables exist when the application starts.
 */
public class DatabaseInitializer {

    /**
     * Initializes the database schema.
     */
    public static void initialise() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

            // User Table
            create.createTableIfNotExists("User")
                    .column("id", SQLDataType.INTEGER.identity(true))
                    .column("name", SQLDataType.VARCHAR.length(255).nullable(false))
                    .column("username", SQLDataType.VARCHAR.length(255).nullable(false))
                    .column("password", SQLDataType.VARCHAR.length(255).nullable(false))
                    .column("role", SQLDataType.VARCHAR.length(50).nullable(false).defaultValue("regular"))
                    .constraint(DSL.constraint("PK_User").primaryKey("id"))
                    .constraint(DSL.constraint("UQ_User_Username").unique("username"))
                    .execute();

            // ShoppingList Table
            create.createTableIfNotExists("ShoppingList")
                    .column("id", SQLDataType.INTEGER.identity(true))
                    .column("name", SQLDataType.VARCHAR.length(255).nullable(false))
                    .constraint(DSL.constraint("PK_ShoppingList").primaryKey("id"))
                    .execute();

            // Item Table (Linked to ShoppingList)
            create.createTableIfNotExists("Item")
                    .column("id", SQLDataType.INTEGER.identity(true))
                    .column("shopping_list_name", SQLDataType.VARCHAR.nullable(false))
                    .column("category", SQLDataType.VARCHAR.length(255).nullable(false))
                    .column("name", SQLDataType.VARCHAR.length(255).nullable(false))
                    .column("price", SQLDataType.DOUBLE.precision(10, 2).nullable(false))
                    .column("quantity", SQLDataType.DOUBLE.precision(10, 2).nullable(false))
                    .constraint(DSL.constraint("PK_Item").primaryKey("id"))
                    .constraint(DSL.constraint("FK_ShoppingList").foreignKey("shopping_list_name").references("ShoppingList", "name"))
                    .execute();

            System.out.println("✅ Database and tables initialized successfully.");
        } catch (SQLException e) {
            System.err.println("❌ ERROR while initializing the database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}