package edu.kit.kastel;

import edu.kit.kastel.utility.Date;
import edu.kit.kastel.utility.TypeOfPrint;

/**
 * Utility class responsible for handling console output and formatting messages.
 * @author unweb
 * @version 1.0
 */
public final class Message {
    /** Marker string for completed tasks. */
    private static final String COMPLETED = " [x] ";
    /** Marker string for incomplete tasks. */
    private static final String INCOMPLETED = " [ ] ";  
    /** Separator string used between task details. */
    private static final String COLON = ": ";
    /** Format string for displaying task priority. */
    private static final String PRIORITY = " [%s]";
    /** Format string for displaying task tags. */
    private static final String TAG = "(%s)";
    /** Format string for displaying task deadlines. */
    private static final String DEADLINE = " --> %s";
    /** String used for indenting subtasks in the hierarchy. */
    private static final String INDENTATION = "  ";
    /** Prefix string for list items. */
    private static final String BULLET_POINT = "- ";
    
    /**
     * Enumeration containing all system messages and format strings.
     */
    public enum SystemMessage {
        /** Error message for invalid commands. */
        INVALID_COMMAND("Error, invalid command!"),
        /** Error message for invalid arguments. */
        INVALID_ARGUMENTS("Error, invalid arguments!"),
        /** Error message when a task is not found. */
        TASK_NOT_FOUND("Error, task not found!"),
        /** Error message when a list is not found. */
        LIST_NOT_FOUND("Error, list not found!"),
        /** Error message for incorrect date formats. */
        INVALID_DATE_FORMAT("Error, invalid date format!"),
        /** Error message for invalid tags. */
        INVALID_TAG("Error, invalid tag"),
        /** Error message for invalid priority levels. */
        INVALID_PRIORITY("Error, invalid priority!"),
        /** Error message for non-existent task IDs. */
        INVALID_TASK_ID("Error, invalid task ID!"),
        /** Error message for invalid list names. */
        INVALID_LIST_NAME("Error, invalid list name!"),
        /** Success message for adding a task. */
        SUCCESFULLY_ADDED_TASK("added %s: %s"),
        /** Success message for adding a list. */
        SUCESSFULLY_ADDED_LIST("added %s"),
        /** Success message for tagging an item. */
        SUCESSFULLY_TAGGED("tagged %s with %s"),
        /** Success message for assigning a subtask. */
        SUCESSFULLY_ASSIGNED("assigned %s to %s"),
        /** Success message for toggling task status. */
        SUCESSFULLY_TOGGLED("toggled %s and %d subtasks"),
        /** Success message for changing a deadline. */
        SUCESSFULLY_CHANGED_DEADLINE("changed %s to %s"),
        /** Success message for changing priority. */
        SUCESSFULLY_CHANGED_PRIORITY("changed %s to %s"),
        /** Success message for deleting items. */
        SUCESSFULLY_DELETED("deleted %s and %d subtasks"),
        /** Success message for restoring items. */
        SUCESSFULLY_RESTORED("restored %s and %d subtasks"),
        /** Success message showing found duplicates with details. */
        SUCESSFULLY_FIND_DUPLICATES("Found %d duplicates: %s"),
        /** Generic message for found duplicates. */
        FOUND_DUPLICATES("Found %d duplicates: %s");
        
        /** The format string of the message. */
        private final String message;
        
        /**
         * Constructs a SystemMessage with the specific format string.
         * @param message The format string.
         */
        SystemMessage(String message) {
            this.message = message;
        }
    }

    /**
     * Private constructor to prevent instantiation of the class.
     */
    private Message() {

    }
    /**
     * Prints a formatted system message to the console.
     * @param systemMessage The message template to print.
     * @param args The arguments to format into the message.
     */
    public static void printSystemMessage(SystemMessage systemMessage, Object... args) {
        System.out.println(String.format(systemMessage.message, args));
    }

    /**
     * Prints a ToDoItem and its hierarchy based on a specific printing condition.
     * @param item The root item (Task or List) to print.
     * @param type The type of filter/condition to apply (e.g., FIND, UPCOMING).
     * @param args Additional arguments required for the condition (e.g., date strings).
     */
    public static void printWithCondition(ToDoItem item, TypeOfPrint type,  Object... args) {
        if (item == null) {
            printSystemMessage(SystemMessage.INVALID_TASK_ID);
            return;
        }
        StringBuilder sb = new StringBuilder();
        printWithConditionsRecursive(item, type, sb, BULLET_POINT, args);
        System.out.println(sb.toString());
    }

    /**
     * Recursively appends incomplete tasks to the string builder.
     * @param task The task to check.
     * @param sb The StringBuilder to append to.
     * @param indent The current indentation string.
     */
    private static void printIncompletedRecursive(Task task, StringBuilder sb, String indent) {
        if (!task.isCompleted()) {
            if (!hasIncompletedSubtasks(task)) {
                return;
            }
            printItem(task, sb, indent);
            for (Task subTask : task.getSubTasks()) {
                printWithConditionsRecursive(subTask, TypeOfPrint.REGULAR, sb, indent + INDENTATION);
            }
        } else {
            printIncompletedRecursive(task, sb, indent);
        }
    }

    /**
     * Checks if a task tree contains any incomplete subtasks.
     * @param task The root task to check.
     * @return true if there is at least one incomplete subtask.
     */
    private static boolean hasIncompletedSubtasks(Task task) {
        if (task == null || task.getSubTasks() == null) {
            return false;
        }
        for (Task subTask : task.getSubTasks()) {
            if (subTask.isCompleted()) {
                return true;
            } 
            if (hasIncompletedSubtasks(subTask)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Recursively traverses and prints items that satisfy the specified condition.
     * @param item The current item to check.
     * @param type The condition type.
     * @param sb The StringBuilder to append to.
     * @param indent The current indentation level.
     * @param args Additional arguments for the condition.
     */
    private static void printWithConditionsRecursive(ToDoItem item, TypeOfPrint type, StringBuilder sb, String indent, Object... args) {
        boolean fullfillsCondition = true;
        switch (type) {
            case INCOMPLETED_ONLY -> {
                printIncompletedRecursive((Task) item, sb, indent);
                return;
            }
            case TAGGED_WITH -> {
                String tag = (String) args[0];
                fullfillsCondition = item instanceof Task task && tag.equals(task.getTag());
            }
            case FIND -> {
                String name = (String) args[0];
                fullfillsCondition = item instanceof Task task && task.getName().contains(name);
            }
            case BEFORE -> {
                String date = (String) args[0];
                fullfillsCondition = item instanceof Task task && Date.before(date, task);
            }
            case UPCOMING -> {
                String date = (String) args[0];
                fullfillsCondition = item instanceof Task task && Date.upcoming(date,  task);
            }
            case BETWEEN -> {
                String startDate = (String) args[0];
                String endDate = (String) args[1];
                fullfillsCondition = item instanceof Task task && Date.between(startDate, endDate, task);
            }
            default -> fullfillsCondition = true;
        }
        if (fullfillsCondition) {
            printItem(item, sb, indent);
            for (Task subTask : item.getSubTasks()) {
                printWithConditionsRecursive(subTask, TypeOfPrint.REGULAR, sb, indent + INDENTATION);
            }
            return;
        }
        if (item instanceof TasksList || item.getSubTasks() == null) {
            return;
        }
        for (Task subTask : item.getSubTasks()) {
            printWithConditionsRecursive(subTask, type, sb, indent);
        }
    }

    /**
     * Formats a single item's details into the StringBuilder.
     * @param item The item to format.
     * @param sb The StringBuilder to append to.
     * @param indent The indentation string.
     */
    private static void printItem(ToDoItem item, StringBuilder sb, String indent) {
        if (item instanceof Task task) {
            sb.append(indent)
              .append(task.isCompleted() ? COMPLETED : INCOMPLETED)
              .append(task.getName())
              .append(task.getPriority() != null ? String.format(PRIORITY, task.getPriority()) : null)
              .append(task.getTag() != null || task.getDeadline() != null ? COLON : null)
              .append(task.getTag() != null ?  String.format(TAG, task.getTag()) : null)
                .append(task.getDeadline() != null ? String.format(DEADLINE, task.getDeadline()) : null);
        } else if (item instanceof TasksList) {
            sb.append(indent)
                .append(item.getName());
        }
    }
}
