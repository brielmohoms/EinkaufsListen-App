package org.prog3.app;

import org.prog3.dao.DatabaseInitializer;

/**
 * entry point for the application
 */
public class App {
    public static void main(String[] args) {
        DatabaseInitializer.initialise();
        CLI app = new CLI();
        app.start();

    }
}