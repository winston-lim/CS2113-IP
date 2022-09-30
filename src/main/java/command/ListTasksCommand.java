package command;


import task.TaskManager;

public class ListTasksCommand extends Command {
    // Properties
    private final TaskManager taskManager;

    public ListTasksCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }


    /**
     * Lists all recorded tasks.
     * 
     * @return boolean whether this command exits
     */
    public boolean executeCommand() {
        taskManager.listTasks();
        return false;
    }
}
