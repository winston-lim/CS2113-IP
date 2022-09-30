package command;

import exception.InsufficientArgumentsException;
import task.TaskManager;

public class GetTaskWithTitle extends Command {
    // Properties
    private final String[] args;
    private final TaskManager taskManager;

    /**
     * Default constructor method.
     * 
     * @param taskManager used for delegating any task related operations
     * @param args arguments supplied for this command
     * @throws InsufficientArgumentsException thrown when no arguments where supplied for this
     *         command
     */
    public GetTaskWithTitle(TaskManager taskManager, String[] args)
            throws InsufficientArgumentsException {
        if (args.length == EMPTY_ARRAY_SIZE) {
            throw new InsufficientArgumentsException();
        }
        this.args = args;
        this.taskManager = taskManager;
    }

    /**
     * Prints a list of search results.
     * 
     * @return boolean whether this command exists
     * @throws InsufficientArgumentsException thrown when arguments are empty strings
     */
    public boolean executeCommand() throws InsufficientArgumentsException {
        String query = this.args[DEFAULT_FIRST_INDEX];
        if (query.isEmpty()) {
            throw new InsufficientArgumentsException();
        }
        taskManager.listTasks(taskManager.searchByTitle(query));
        return false;
    }
}
