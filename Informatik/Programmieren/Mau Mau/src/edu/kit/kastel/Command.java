package edu.kit.kastel;

/**
 * This class contains all commands of the game.
 * @author unweb
 */
public enum Command {
    /**
     * Start command.
     */
    START("start"),
    /**
     * Show command.
     */
    SHOW("show"),
    /**
     * Discard command.
     */
    DISCARD("discard"),
    /**
     * Pick command.
     */
    PICK("pick"),
    /**
     * Quit command.
     */
    QUIT("quit");
    /**
     * Command.
     */
    private final String command;
    /**
     * Constructor of the class.
     * @param command command on which the programm runs
     */
    Command(String command) {
        this.command = command;
    }
    /**
     * Registered command.
     * @return command
     */
    private String getCommand() {
        return command;
    }   
    /**
     * Convert a string command to a command.
     * @param command the coomand
     * @return command
     */
    public static Command stringToCommand(String command) {
        for (Command cd : Command.values()) {
            if (cd.getCommand().equals(command)) {
                return cd;
            }
        }
        return null;
    }

}
