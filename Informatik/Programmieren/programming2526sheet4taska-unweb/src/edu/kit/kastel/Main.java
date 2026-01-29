package edu.kit.kastel;

/**
 * Main class.
 * @author unweb
 */
final class Main {
    /**
     * Constructor of main class.
     */
    private Main() {
        
    }
    /**
     * The main programm.
     * @param args is the argument
     * @throws Validity when something is wrong
     */
    public static void main(String[] args) throws Validity {
        CommandHandler commandHandler = CommandHandler.getInstance();
        commandHandler.operation();
    }
}
