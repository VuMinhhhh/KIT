package edu.kit.kastel.utility;

/**
 * Enum representing all available commands in the application.
 * @author unweb
 * @version 1.0
 */
public enum Command {

    /** Command to add a task. */
    ADD("add"),

    /** Command to add a new list. */
    ADD_LIST("add-list"),

    /** Command to assign an ID to a task. */
    ASSIGN("assign"),

    /** Command to tag a task. */
    TAG("tag"),

    /** Command to toggle task status (done/undone). */
    TOGGLE("toggle"),

    /** Command to change a task's date. */
    CHANGE_DATE("change-date"),

    /** Command to change a task's priority. */
    CHANGE_PRIORITY("change-priority"),

    /** Command to delete a task. */
    DELETE("delete"),

    /** Command to restore a deleted task. */
    RESTORE("restore"),

    /** Command to show tasks. */
    SHOW("show"),

    /** Command to show to-do items. */
    TO_DO("todo"),

    /** Command to find tasks by name. */
    FIND("find"),

    /** Command to list tasks by tag. */
    TAGGED_WITH("tagged-with"),

    /** Command to list upcoming tasks. */
    UPCOMING("upcoming"),

    /** Command to list tasks before a date. */
    BEFORE("before"),

    /** Command to list tasks between two dates. */
    BETWEEN("between"),

    /** Command to check for duplicates. */
    DUPLICATE("duplicate"),

    /** Command to quit the program. */
    QUIT("quit");

    private final String commandStr;

    /**
     * Creates a command with the given string alias.
     * @param commandStr The command string (e.g., "add-list").
     */
    Command(String commandStr) {
        this.commandStr = commandStr;
    }

    /**
     * Finds the Command enum from a user input string.
     * @param commandStr The user input to check.
     * @return The matching Command, or null if not found.
     */
    public static Command fromString(String commandStr) {
        for (Command command : Command.values()) {
            // Fix: Compare with the commandStr field (e.g., "add-list"), not the Enum name (ADD_LIST)
            if (command.commandStr.equalsIgnoreCase(commandStr)) {
                return command;
            }
        }
        return null;
    }
}