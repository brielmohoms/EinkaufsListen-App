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

        public static void initialise () {
            String url = "";

            try (Connection conn = DriverManager.getConnection(url)) {
                DSLContext create = DSL.using(conn);

                // table Item
                create.createTableIfNotExists("Item")
                        .column("id ", SQLDataType.INTEGER.identity(true))
                        .column("name", SQLDataType.VARCHAR.length(255).nullable(false))
                        .column("category", SQLDataType.VARCHAR.length(255).nullable(false))
                        .column("quantity", SQLDataType.INTEGER.nullable(false))
                        .column("price", SQLDataType.DOUBLE.precision(10, 2).nullable(false))
                        .constraint(DSL.constraint("PK_Item").primaryKey("id"))
                        .execute();

                // table User
                create.createTableIfNotExists("User")
                        .column("id", SQLDataType.INTEGER.identity(true))
                        .column("username", SQLDataType.VARCHAR.length(255).nullable(false))
                        .column("email", SQLDataType.VARCHAR.length(255).nullable(false))
                        .constraint(DSL.constraint("PK_User").primaryKey("id"))
                        .execute();

                // table Shopping
                create.createTableIfNotExists("Shopping")
                        .column("id", SQLDataType.INTEGER.identity(true))
                        .column("total_price", SQLDataType.DOUBLE.precision(10, 2).nullable(false))
                        .execute();

                System.out.println("Connection to the database ");
            } catch (Exception e) {
                System.err.println("ERROR while connecting to the database :" + e.getMessage());
            }
        }
}
