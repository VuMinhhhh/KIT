package edu.kit.kastel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.kit.kastel.utility.PriorityTable;
import edu.kit.kastel.utility.Validate;


/**
 * Represents a named container for tasks, acting as a high-level list entity.
 * @author unweb
 * @version 1.0
 */
public class TasksList implements ToDoItem {
    private final List<Task> tasks;
    private final String listName;
    private String tag;

    /**
     * Constructs a new TasksList with the specified name.
     * @param listName The unique name of the list.
     */
    public TasksList(String listName) {
        this.listName = listName;
        this.tasks = new ArrayList<>();
    }

    @Override
    public String getId() {
        return this.listName;
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
        Message.printSystemMessage(Message.SystemMessage.SUCESSFULLY_TAGGED, this.listName, this.tag);
    }

    @Override
    public void addSubTask(Task task) {
        this.tasks.add(task);
        sortSubTasksByPriority();
    }

    @Override
    public void sortSubTasksByPriority() {
        tasks.sort(Comparator.comparingInt(t -> PriorityTable.getPriorityValue(t.getPriority())));
    }

    @Override
    public String getName() {
        return this.listName;
    }

    @Override
    public List<Task> getSubTasks() {
        List<Task> activeSubTasks = new ArrayList<>();
        for (Task subTask : tasks) {
            activeSubTasks.add(subTask);
        }
        return activeSubTasks;
    }
}