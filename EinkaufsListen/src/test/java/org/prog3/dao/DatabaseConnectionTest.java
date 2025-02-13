package org.prog3.dao;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionTest {

    @Test
    void testGetConnection() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            assertNotNull(connection, "Database connection should not be null.");
        } catch (Exception e) {
            fail("Failed to establish a database connection.");
        }
    }
}