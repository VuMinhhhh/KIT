package edu.kit.kastel;

import java.util.List;

/**
 * Common interface for all entities within the task management system, such as tasks and lists.
 * @author unweb
 * @version 1.0
 */
public interface ToDoItem {
    /**
     * Retrieves the unique identifier or name representing this item.
     * @return The unique ID string.
     */
    String getId();

    /**
     * Retrieves the display name of the item.
     * @return The name string.
     */
    String getName();

    /**
     * Assigns a specific tag string to this item.
     * @param tag The tag to apply.
     */
    void setTag(String tag);

    /**
     * Retrieves the currently assigned tag.
     * @return The tag string or null if none exists.
     */
    String getTag();

    /**
     * Appends a child task to this item's hierarchy.
     * @param task The task to add as a child.
     */
    void addSubTask(Task task);

    /**
     * Sorts the collection of direct subtasks based on their priority values.
     */
    void sortSubTasksByPriority();

    /**
     * Retrieves the collection of child tasks associated with this item.
     * @return A list of subtasks.
     */
    List<Task> getSubTasks();
}