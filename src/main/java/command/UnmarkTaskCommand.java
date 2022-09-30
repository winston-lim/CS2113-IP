package command;

import java.io.IOException;
import exception.InsufficentArgumentsException;
import exception.TaskNotFoundException;
import task.TaskManager;

public class UnmarkTaskCommand extends Command {
    private final String[] args;
    private final TaskManager taskManager;

    public UnmarkTaskCommand(TaskManager taskManager, String[] args)
            throws InsufficentArgumentsException {
        if (args.length == EMPTY_ARRAY_SIZE) {
            throw new InsufficentArgumentsException();
        }
        this.taskManager = taskManager;
        this.args = args;
    }

    /**
     * Marks a specified task as NOT done, or throws an error if it does not exist.
     * 
     * @return boolean whether this command exits
     * @throws IOException thrown when saving to local storage fails
     * @throws TaskNotFoundException thrown when specified task does not exist
     */
    public boolean executeCommand() throws IOException, TaskNotFoundException {
        this.taskManager.unmarkTask(Integer.parseInt(args[DEFAULT_FIRST_INDEX]));
        return false;
    }
}
