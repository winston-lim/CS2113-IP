package command;

import java.io.IOException;
import exception.InsufficientArgumentsException;
import task.TaskManager;
import task.Todo;

public class AddTodoCommand extends Command {
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
    public AddTodoCommand(TaskManager taskManager, String[] args)
            throws InsufficientArgumentsException {
        if (args.length == EMPTY_ARRAY_SIZE) {
            throw new InsufficientArgumentsException();
        }
        this.args = args;
        this.taskManager = taskManager;
    }

    /**
     * Add a task of type todo to list of recorded tasks.
     * 
     * @return boolean whether this command exits
     * @throws IOException thrown when saving to local storage fails
     * @throws InsufficientArgumentsException thrown when given title is empty
     */
    public boolean executeCommand() throws IOException, InsufficientArgumentsException {
        String title = String.join(DEFAULT_DELIMITER, args);
        if (title.isEmpty()) {
            throw new InsufficientArgumentsException();
        }
        this.taskManager.addTask(new Todo(title));
        return false;
    }
}
