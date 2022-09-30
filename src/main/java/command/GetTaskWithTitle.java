package command;

import exception.InsufficentArgumentsException;
import task.TaskManager;

public class GetTaskWithTitle extends Command {
    private final String[] args;
    private final TaskManager taskManager;

    public GetTaskWithTitle(TaskManager taskManager, String[] args)
            throws InsufficentArgumentsException {
        if (args.length == EMPTY_ARRAY_SIZE) {
            throw new InsufficentArgumentsException();
        }
        this.args = args;
        this.taskManager = taskManager;
    }

    public boolean executeCommand() throws InsufficentArgumentsException {
        String query = this.args[DEFAULT_FIRST_INDEX];
        if (query.isEmpty()) {
            throw new InsufficentArgumentsException();
        }
        taskManager.listTasks(taskManager.searchByTitle(query));
        return false;
    }
}
