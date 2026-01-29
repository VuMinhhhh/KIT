package edu.kit.kastel;

import java.util.ArrayList;
import java.util.List;

import edu.kit.kastel.utility.TypeOfPrint;
import edu.kit.kastel.utility.Validate;


/**
 * Singleton class responsible for managing tasks, lists, and their operations.
 * @author unweb
 * @version 1.0
 */
public final class TaskManager {
    private static TaskManager instance;
    private final List<ToDoItem> items = new ArrayList<>();
    private final List<Task> allTasks = new ArrayList<>();

    /**
     * Private constructor to enforce Singleton pattern.
     */
    private TaskManager() {
    }

    /**
     * Retrieves the single instance of the TaskManager.
     * @return The singleton instance.
     */
    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }
    
    /**
     * Adds a newly created task to the manager's collections.
     * @param newTask The task to add.
     */
    public void addTask(Task newTask) {
        items.add(newTask);
        allTasks.add(newTask);
    }

    /**
     * Creates and adds a new task list with the specified name.
     * @param listName The name of the new list.
     */
    public void addList(String listName) {
        TasksList newList = new TasksList(listName);
        items.add(newList);
    }

    /**
     * Assigns a tag to the item identified by the given ID.
     * @param id The ID of the item to tag.
     * @param tag The tag string to apply.
     */
    public void tag(String id, String tag) {
        for (ToDoItem item : items) {
            if (item.getId().equals(id)) {
                item.setTag(tag);
                break;
            }
        }
    }

    /**
     * Moves a task to become a subtask of another item.
     * @param underId The ID of the task to be moved.
     * @param upperId The ID of the new parent item.
     */
    public void assign(String underId, String upperId) {
        ToDoItem upperItem = findToDoItem(upperId);
        ToDoItem underItem = findToDoItem(underId);
        if (upperItem != null && underItem != null && underItem instanceof Task) {
            upperItem.addSubTask((Task) underItem);
            Message.printSystemMessage(Message.SystemMessage.SUCESSFULLY_ASSIGNED, underItem.getId(), upperItem.getId());
            items.remove(underItem);
        }
    }

    /**
     * Toggles the completion status of the task identified by ID.
     * @param id The ID of the task.
     */
    public void toggle(String id) {
        Task task = findTask(id);
        if (task != null) {
            task.setCompleted(!task.isCompleted());
        }
    }

    /**
     * Updates the deadline of the specified task.
     * @param id The ID of the task.
     * @param newDate The new deadline date string.
     */
    public void changeDate(String id, String newDate) {
        Task task = findTask(id);
        if (task != null) {
            task.setDeadline(newDate);
        }
    }

    /**
     * Updates the priority level of the specified task.
     * @param id The ID of the task.
     * @param newPriority The new priority value.
     */
    public void changePriority(String id, String newPriority) {
        Task task = findTask(id);
        if (task != null) {
            task.setPriority(newPriority);
        }
    }

    /**
     * Marks the task identified by ID as deleted.
     * @param id The ID of the task to delete.
     */
    public void delete(String id) {
        Task task = findTask(id);
        if (task != null) {
            task.setDeleted(true);
        }
    }

    /**
     * Restores a previously deleted task identified by ID.
     * @param id The ID of the task to restore.
     */
    public void restore(String id) {
        Task task = findTask(id);
        if (task != null) {
            task.setDeleted(false);
        }
    }

    /**
     * Prints the details of a specific item.
     * @param id The ID of the item to show.
     */
    public void show(String id) {
        ToDoItem item = findToDoItem(id);
        Message.printWithCondition(item, TypeOfPrint.REGULAR);
    }

    /**
     * Prints all incomplete tasks and items.
     */
    public void toDo() {
        for (ToDoItem item : items) {
            Message.printWithCondition(item, TypeOfPrint.INCOMPLETED_ONLY);
        }
    }

    /**
     * Prints all items associated with a specific tag.
     * @param tag The tag to filter by.
     */
    public void taggedWith(String tag) {
        for (ToDoItem item : items) {
            Message.printWithCondition(item, TypeOfPrint.TAGGED_WITH, tag);
        }
    }

    /**
     * Searches for and prints items containing the specified name.
     * @param name The name substring to search for.
     */
    public void find(String name) {
        for (ToDoItem item : items) {
            Message.printWithCondition(item, TypeOfPrint.FIND, name);
        }
    }

    /**
     * Prints tasks due within the upcoming week relative to the date.
     * @param date The current reference date.
     */
    public void upcoming(String date) {
        for (ToDoItem item : items) {
            Message.printWithCondition(item, TypeOfPrint.UPCOMING, date);
        }
    }

    /**
     * Prints tasks due strictly before the specified date.
     * @param date The reference deadline date.
     */
    public void before(String date) {
        for (ToDoItem item : items) {
            Message.printWithCondition(item, TypeOfPrint.BEFORE, date);
        }
    }

    /**
     * Prints tasks due between the specified start and end dates.
     * @param startDate The start of the date range.
     * @param endDate The end of the date range.
     */
    public void between(String startDate, String endDate) {
        for (ToDoItem item : items) {
            Message.printWithCondition(item, TypeOfPrint.BETWEEN, startDate, endDate);
        }
    }

    /**
     * Identifies and reports potentially duplicate tasks.
     */
    public void duplicate() {
        List<String> duplicateIds = new ArrayList<>();
        for (int i = 0; i < allTasks.size(); i++) {
            Task task1 = allTasks.get(i);
            for (int j = i + 1; j < allTasks.size(); j++) {
                Task task2 = allTasks.get(j);
                if (Validate.validateDuplicateTask(task1, task2)) {
                    if (!duplicateIds.contains(task1.getId())) {
                        duplicateIds.add(task1.getId());
                    }
                    if (!duplicateIds.contains(task2.getId())) {
                        duplicateIds.add(task2.getId());
                    }
                }
            }
        }
        String duplicatesStr = String.join(", ", duplicateIds);
        Message.printSystemMessage(Message.SystemMessage.SUCESSFULLY_FIND_DUPLICATES, duplicateIds.size(), duplicatesStr);
    }

    /**
     * Helper method to find a ToDoItem by its unique ID.
     * @param id The ID to search for.
     * @return The found ToDoItem or null if not found.
     */
    private ToDoItem findToDoItem(String id) {
        for (ToDoItem item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Helper method to find a Task by its unique ID.
     * @param id The ID to search for.
     * @return The found Task or null if not found or type mismatch.
     */
    private Task findTask(String id) {
        ToDoItem item = findToDoItem(id);
        if (item instanceof Task) {
            return (Task) item;
        }
        return null;
    }
}