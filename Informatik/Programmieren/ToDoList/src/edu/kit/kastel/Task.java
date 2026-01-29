package edu.kit.kastel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.kit.kastel.utility.PriorityTable;
import edu.kit.kastel.utility.Validate;


/**
 * Represents a concrete task entity with properties such as priority, deadline, and subtasks.
 * @author unweb
 * @version 1.0
 */
public class Task implements ToDoItem {
    private static int taskCounter = 0;
    private final List<Task> subTasks = new ArrayList<>();
    private final String name;
    private String priority;
    private String deadline;
    private String tag;
    private final String id;
    private boolean completed;
    private boolean isDeleted;

    /**
     * Constructs a new Task with a name, priority, and deadline.
     * @param name The name of the task.
     * @param priority The priority level (HI, MD, LO).
     * @param deadline The deadline date (yyyy-MM-dd).
     */
    public Task(String name, String priority, String deadline) {
        this.name = name;
        this.priority = priority;
        taskCounter++;
        this.id = "" + taskCounter;
        this.deadline = deadline;
        isDeleted = false;
    }

    /**
     * Constructs a new Task with only a name.
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.priority = null;
        taskCounter++;
        this.id = "" + taskCounter;
        this.deadline = null;
        isDeleted = false;
    }

    /**
     * Constructs a Task by determining if the second argument is a priority or a deadline.
     * @param name The name of the task.
     * @param eitherPriorityOrDeadline A string representing either a priority or a date.
     */
    public Task(String name, String eitherPriorityOrDeadline) {
        this.name = name;
        if (Validate.validatePriority(eitherPriorityOrDeadline)) {
            this.priority = eitherPriorityOrDeadline;
            this.deadline = null;
        } else if (Validate.validateDeadline(eitherPriorityOrDeadline)) {
            this.deadline = eitherPriorityOrDeadline;
            this.priority = null;
        } 
        taskCounter++;
        this.id = "" + taskCounter;
        isDeleted = false;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getTag() {
        return this.tag;
    }

    @Override
    public void setTag(String tag) {
        if (!Validate.validateTag(tag)) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_TAG, this.tag);
            return;
        }
        this.tag = tag;
        Message.printSystemMessage(Message.SystemMessage.SUCESSFULLY_TAGGED, this.id, this.tag);
    }

    @Override
    public void addSubTask(Task task) {
        this.subTasks.add(task);
        sortSubTasksByPriority();
    }

    @Override
    public void sortSubTasksByPriority() {
        subTasks.sort(Comparator.comparingInt(t -> PriorityTable.getPriorityValue(t.getPriority())));
    }

    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public List<Task> getSubTasks() {
        List<Task> activeSubTasks = new ArrayList<>();
        for (Task subTask : subTasks) {
            activeSubTasks.add(subTask);
        }
        return activeSubTasks;
    }

    /**
     * Retrieves the priority of the task.
     * @return The priority string or null if not set.
     */
    public String getPriority() {
        return this.priority;
    }

    /**
     * Retrieves the deadline of the task.
     * @return The deadline string or null if not set.
     */
    public String getDeadline() {
        return this.deadline;
    }

    /**
     * Checks if the task is marked as completed.
     * @return true if completed, false otherwise.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Checks if the task is marked as deleted.
     * @return true if deleted, false otherwise.
     */
    public boolean isDeleted() {
        return this.isDeleted;
    }

    /**
     * Sets the completion status of the task and resets subtasks to uncompleted.
     * @param completed The new completion status.
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
        for (Task subTask : subTasks) {
            subTask.setCompleted(false);
        }
    }

    /**
     * Updates the task's deadline if the format is valid.
     * @param newDeadline The new deadline string.
     */
    public void setDeadline(String newDeadline) {
        if (!Validate.validateDeadline(newDeadline)) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_DATE_FORMAT);
            return;
        }
        this.deadline = newDeadline;
        Message.printSystemMessage(Message.SystemMessage.SUCESSFULLY_CHANGED_DEADLINE, this.id, this.deadline);
    }

    /**
     * Updates the task's priority if the value is valid.
     * @param newPriority The new priority string.
     */
    public void setPriority(String newPriority) {
        if (!Validate.validatePriority(newPriority)) {
            Message.printSystemMessage(Message.SystemMessage.INVALID_PRIORITY);
            return;
        }
        this.priority = newPriority;
        Message.printSystemMessage(Message.SystemMessage.SUCESSFULLY_CHANGED_PRIORITY, this.id, this.priority);
    }

    /**
     * Sets the deletion status for this task and recursively for all its subtasks.
     * @param isDeleted The new deletion status.
     */
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
        for (Task subTask : subTasks) {
            subTask.setDeleted(isDeleted);
        }
    }
}