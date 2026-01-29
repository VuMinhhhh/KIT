package edu.kit.kastel;

import java.util.Scanner;

import edu.kit.kastel.Message.SystemMessage;
import edu.kit.kastel.utility.Command;
import edu.kit.kastel.utility.Validate;


/**
 * Handles user input and executes corresponding commands for the Task Management system.
 * This class acts as the controller. It reads input from the console, parses the command,
 * validates the arguments, and delegates the business logic to the {@link TaskManager}.
 * @author unweb
 * @version 1.0
 */
public class CommandHandler {

    private static final String REGEX_SPACE = " ";
    
    // Constants defining the required number of arguments for each command
    private static final int NUMBER_OF_ARGS_FOR_ADD_TASK_WITH_NAME_ONLY = 2;
    private static final int NUMBER_OF_ARGS_FOR_ADD_TASK_WITH_NAME_AND_EITHER_PRIORITY_OR_DEADLINE = 3;
    private static final int NUMBER_OF_ARGS_FOR_ADD_TASK_WITH_NAME_PRIORITY_AND_DEADLINE = 4;
    private static final int NUMBER_OF_ARGS_FOR_ADD_LIST = 2;
    private static final int NUMBER_OF_ARGS_FOR_TAG = 3;
    private static final int NUMBER_OF_ARGS_FOR_ASSIGN = 3;
    private static final int NUMBER_OF_ARGS_FOR_TOGGLE = 2;
    private static final int NUMBER_OF_ARGS_FOR_CHANGE_DATE = 3;
    private static final int NUMBER_OF_ARGS_FOR_CHANGE_PRIORITY_TO_NONE = 2;
    private static final int NUMBER_OF_ARGS_FOR_CHANGE_PRIORITY = 3;
    private static final int NUMBER_OF_ARGS_FOR_DELETE = 2; 
    private static final int NUMBER_OF_ARGS_FOR_RESTORE = 2;
    private static final int NUMBER_OF_ARGS_FOR_SHOW = 2;
    private static final int NUMBER_OF_ARGS_FOR_TO_DO = 1;
    private static final int NUMBER_OF_ARGS_FOR_FIND = 2;
    private static final int NUMBER_OF_ARGS_FOR_TAGGED_WITH = 2;
    private static final int NUMBER_OF_ARGS_FOR_UPCOMING = 2;
    private static final int NUMBER_OF_ARGS_FOR_BEFORE = 2;
    private static final int NUMBER_OF_ARGS_FOR_BETWEEN = 3;
    private static final int NUMBER_OF_ARGS_FOR_DUPLICATE = 1;
    /** The singleton instance of the TaskManager. */
    private static final TaskManager TASK_MANAGER = TaskManager.getInstance();


    /** Flag to control the main execution loop. */
    private boolean quit;

    /**
     * Starts the main execution loop of the application.
     * Continuously reads user input from {@code System.in}, determines the command type,
     * and dispatches the request to the specific handler methods. The loop terminates
     * when the {@code QUIT} command is received.
     */
    public void operation() {
        Scanner input = new Scanner(System.in);
        while (!quit) {
            String userInput = input.nextLine();
            String[] parts = userInput.split(REGEX_SPACE);
            String commandStr = parts[0];
            Command command = Command.fromString(commandStr);
            
            if (command == null) {
                Message.printSystemMessage(Message.SystemMessage.INVALID_COMMAND);
                continue;
            }
            
            switch (command) {
                case ADD -> handleAddTask(parts);
                case ADD_LIST -> handleAddList(parts);
                case ASSIGN -> handleAssign(parts);
                case TAG -> handleTag(parts);
                case TOGGLE -> handleToggle(parts);
                case CHANGE_DATE -> handleChangeDate(parts);
                case CHANGE_PRIORITY -> handleChangePriority(parts);
                case DELETE -> handleDelete(parts);
                case RESTORE -> handleRestore(parts);
                case SHOW -> handleShow(parts);
                case TO_DO -> handleToDo(parts);
                case FIND -> handleFind(parts);
                case TAGGED_WITH -> handleTaggedWith(parts);
                case UPCOMING -> handleUpcoming(parts);
                case BEFORE -> handleBefore(parts);
                case BETWEEN -> handleBetween(parts);
                case DUPLICATE -> handleDuplicate(parts);
                case QUIT -> quit = true;
                default -> Message.printSystemMessage(SystemMessage.INVALID_COMMAND);
            }
        }
        input.close();
    }

    /**
     * Handles the {@code ADD} command to create a new task.
     * Supports adding tasks with: name only, name + priority, name + deadline, or name + priority + deadline.
     * @param parts The split user input containing the arguments.
     */
    private void handleAddTask(String[] parts) {
        switch (parts.length) {
            case NUMBER_OF_ARGS_FOR_ADD_TASK_WITH_NAME_ONLY -> {
                String name = parts[1];
                if (Validate.validateName(name)) {
                    TASK_MANAGER.addTask(new Task(name));
                    return;
                } 
                Message.printSystemMessage(Message.SystemMessage.INVALID_ARGUMENTS);
            } 
            case NUMBER_OF_ARGS_FOR_ADD_TASK_WITH_NAME_AND_EITHER_PRIORITY_OR_DEADLINE -> {
                String name = parts[1];
                String eitherPriorityOrDeadline = parts[2];
                if (!Validate.validateListName(name) || !Validate.validatePriority(eitherPriorityOrDeadline) 
                    && !Validate.validateDeadline(eitherPriorityOrDeadline)) {
                    Message.printSystemMessage(Message.SystemMessage.INVALID_ARGUMENTS);
                    return;
                }
                TASK_MANAGER.addTask(new Task(name, eitherPriorityOrDeadline));
            } 
            case NUMBER_OF_ARGS_FOR_ADD_TASK_WITH_NAME_PRIORITY_AND_DEADLINE -> {
                String name = parts[1];
                String priority = parts[2];
                String date = parts[3];
                if (!Validate.validateListName(name) || !Validate.validatePriority(priority) || !Validate.validateDeadline(date)) {
                    Message.printSystemMessage(Message.SystemMessage.INVALID_ARGUMENTS);
                    return;
                }
                TASK_MANAGER.addTask(new Task(name, priority, date));
            }
            default -> {
                Message.printSystemMessage(Message.SystemMessage.INVALID_COMMAND);
            }
        }
    }

    /**
     * Handles the {@code ADD_LIST} command to create a new task list.
     * @param parts The split user input.
     */
    private void handleAddList(String[] parts) {
        if (parts.length != NUMBER_OF_ARGS_FOR_ADD_LIST) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_COMMAND);
            return;
        }
        String listName = parts[1];
        if (!Validate.validateListName(listName)) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_ARGUMENTS);
            return;
        }
        TASK_MANAGER.addList(listName);
    }

    /**
     * Handles the {@code ASSIGN} command to link a task to a list.
     * @param parts The split user input.
     */
    private void handleAssign(String[] parts) {
        if (parts.length != NUMBER_OF_ARGS_FOR_ASSIGN) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_COMMAND);
            return;
        }
        String underId = parts[1];
        String upperId = parts[2];
        if (!Validate.validateId(underId) || !Validate.validateId(upperId) && !Validate.validateListName(upperId)) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_ARGUMENTS);
            return;
        }
        TASK_MANAGER.assign(underId, upperId);
    }

    /**
     * Handles the {@code TAG} command to add a tag to a task.
     * @param parts The split user input.
     */
    private void handleTag(String[] parts) {
        if (parts.length != NUMBER_OF_ARGS_FOR_TAG) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_COMMAND);
            return;
        }
        String id = parts[1];
        String tag = parts[2];
        TASK_MANAGER.tag(id, tag);
    }

    /**
     * Handles the {@code TOGGLE} command to change a task's completion status.
     * @param parts The split user input.
     */
    private void handleToggle(String[] parts) {
        if (parts.length != NUMBER_OF_ARGS_FOR_TOGGLE) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_COMMAND);
            return;
        }
        String id = parts[1];
        TASK_MANAGER.toggle(id);
    }

    /**
     * Handles the {@code CHANGE_DATE} command to update a task's deadline.
     * @param parts The split user input.
     */
    private void handleChangeDate(String[] parts) {
        if (parts.length != NUMBER_OF_ARGS_FOR_CHANGE_DATE) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_COMMAND);
            return;
        }
        String id = parts[1];
        String newDate = parts[2];
        if (!Validate.validateDeadline(newDate)) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_DATE_FORMAT);
            return;
        }
        TASK_MANAGER.changeDate(id, newDate);
    }

    /**
     * Handles the {@code CHANGE_PRIORITY} command to update a task's priority.
     * @param parts The split user input.
     */
    private void handleChangePriority(String[] parts) {
        if (parts.length == NUMBER_OF_ARGS_FOR_CHANGE_PRIORITY_TO_NONE) {
            String id = parts[1];
            TASK_MANAGER.changePriority(id, null);
        } else if (parts.length == NUMBER_OF_ARGS_FOR_CHANGE_PRIORITY) {
            String id = parts[1];
            String newPriority = parts[2];
            if (!Validate.validatePriority(newPriority)) {
                Message.printSystemMessage(Message.SystemMessage.INVALID_ARGUMENTS);
                return;
            }
            TASK_MANAGER.changePriority(id, newPriority);
        } else {
            Message.printSystemMessage(Message.SystemMessage.INVALID_COMMAND);
        }
    }

    /**
     * Handles the {@code DELETE} command to remove a task.
     * @param parts The split user input.
     */
    private void handleDelete(String[] parts) {
        if (parts.length != NUMBER_OF_ARGS_FOR_DELETE) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_COMMAND);
            return;
        }
        String id = parts[1];
        TASK_MANAGER.delete(id);
    }

    /**
     * Handles the {@code RESTORE} command to recover a deleted task.
     * @param parts The split user input.
     */
    private void handleRestore(String[] parts) {
        if (parts.length != NUMBER_OF_ARGS_FOR_RESTORE) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_COMMAND);
            return;
        }
        String id = parts[1];
        TASK_MANAGER.restore(id);
    }

    /**
     * Handles the {@code SHOW} command to display details of a task or list.
     * @param parts The split user input.
     */
    private void handleShow(String[] parts) {
        if (parts.length != NUMBER_OF_ARGS_FOR_SHOW) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_COMMAND);
            return;
        }
        String name = parts[1];
        TASK_MANAGER.show(name);
    }

    /**
     * Handles the {@code TO_DO} command to display pending tasks.
     * @param parts The split user input.
     */
    private void handleToDo(String[] parts) {
        if (parts.length != NUMBER_OF_ARGS_FOR_TO_DO) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_COMMAND);
            return;
        }
        TASK_MANAGER.toDo();
    }

    /**
     * Handles the {@code FIND} command to search for tasks.
     * @param parts The split user input.
     */
    private void handleFind(String[] parts) {
        if (parts.length != NUMBER_OF_ARGS_FOR_FIND) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_COMMAND);
            return;
        }
        String name = parts[1];
        TASK_MANAGER.find(name);
    }

    /**
     * Handles the {@code TAGGED_WITH} command to list tasks with a specific tag.
     * @param parts The split user input.
     */
    private void handleTaggedWith(String[] parts) {
        if (parts.length != NUMBER_OF_ARGS_FOR_TAGGED_WITH) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_COMMAND);
            return;
        }
        String tag = parts[1];
        TASK_MANAGER.taggedWith(tag);
    }

    /**
     * Handles the {@code UPCOMING} command to list tasks due soon.
     * @param parts The split user input.
     */
    private void handleUpcoming(String[] parts) {
        if (parts.length != NUMBER_OF_ARGS_FOR_UPCOMING) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_COMMAND);
            return;
        }
        String date = parts[1];
        TASK_MANAGER.upcoming(date); 
    }

    /**
     * Handles the {@code BEFORE} command to list tasks due before a date.
     * @param parts The split user input.
     */
    private void handleBefore(String[] parts) {
        if (parts.length != NUMBER_OF_ARGS_FOR_BEFORE) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_COMMAND);
            return;
        }
        String date = parts[1];
        TASK_MANAGER.before(date);
    }

    /**
     * Handles the {@code BETWEEN} command to list tasks due between two dates.
     * @param parts The split user input.
     */
    private void handleBetween(String[] parts) {
        if (parts.length != NUMBER_OF_ARGS_FOR_BETWEEN) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_COMMAND);
            return;
        }
        String startDate = parts[1];
        String endDate = parts[2];
        TASK_MANAGER.between(startDate, endDate);
    }

    /**
     * Handles the {@code DUPLICATE} command to find duplicate tasks.
     * @param parts The split user input.
     */
    private void handleDuplicate(String[] parts) {
        if (parts.length != NUMBER_OF_ARGS_FOR_DUPLICATE) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_COMMAND);
            return;
        }
        TASK_MANAGER.duplicate();
    }
}