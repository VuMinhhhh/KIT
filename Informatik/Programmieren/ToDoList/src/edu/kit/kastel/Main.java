package edu.kit.kastel;

/**
 * Entry point class for the Task Management application execution.
 * @author unweb
 * @version 1.0
 */
public final class Main {
    
    /**
     * Private constructor to prevent instantiation of the class.
     */
    private Main() {
    }

    /**
     * Initializes the command handler and starts the main application loop.
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.operation();
    }
}