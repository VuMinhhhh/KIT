package edu.kit.kastel.utility;

import edu.kit.kastel.Task;

/**
 * Utility class providing static methods for validating various input formats and data integrity.
 * @author unweb
 * @version 1.0
 */
public final class Validate {
    /**
     * Private constructor to prevent instantiation of the class.
     */
    private Validate() {

    }
    /**
     * Validates if the provided string follows the correct date format.
     * @param deadline The date string to check.
     * @return true if valid, false otherwise.
     */
    public static boolean validateDeadline(String deadline) {
        return Date.validateDeadline(deadline);
    }

    /**
     * Checks if the provided priority string corresponds to a valid priority level.
     * @param priority The priority string to check.
     * @return true if the priority is recognized, false otherwise.
     */
    public static boolean validatePriority(String priority) {
        return PriorityTable.getPriorityValue(priority) != 4;
    }

    /**
     * Validates that a name string is not null and contains non-whitespace characters.
     * @param name The name to check.
     * @return true if valid, false otherwise.
     */
    public static boolean validateName(String name) {
        return name != null && name.matches("\\S+");
    }

    /**
     * Validates that a tag starts with '#' followed by alphanumeric characters.
     * @param tag The tag string to check.
     * @return true if the format is correct, false otherwise.
     */
    public static boolean validateTag(String tag) {
        return tag != null && tag.matches("#[a-zA-Z0-9]+");
    }

    /**
     * Validates that an ID string consists solely of numeric digits.
     * @param id The ID string to check.
     * @return true if numeric, false otherwise.
     */
    public static boolean validateId(String id) {
        return id != null && id.matches("\\d+");
    }

    /**
     * Validates that a list name consists only of alphabetic characters.
     * @param listName The list name to check.
     * @return true if the name is valid, false otherwise.
     */
    public static boolean validateListName(String listName) {
        if (listName == null || listName.isEmpty()) {
            return false;
        }
        return listName.matches("[a-zA-Z]+");
    }

    /**
     * Determines if two tasks are duplicates based on their names and deadlines.
     * @param taskOne The first task to compare.
     * @param taskTwo The second task to compare.
     * @return true if both tasks share the same name and deadline.
     */
    public static boolean validateDuplicateTask(Task taskOne, Task taskTwo) {
        if (!taskOne.getName().equals(taskTwo.getName())) {
            return false;
        } else {
            if (taskOne.getDeadline() != null && taskTwo.getDeadline() != null) {
                return taskOne.getDeadline().equals(taskTwo.getDeadline());
            } 
            return true;
        }
    }
}