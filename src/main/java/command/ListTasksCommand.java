package command;

import java.util.ArrayList;
import java.util.List;
import task.TaskManager;
import user.UserInteraction;

public class ListTasksCommand extends Command {
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
