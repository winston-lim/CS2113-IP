package command;

import java.io.IOException;
import exception.InsufficentArgumentsException;
import task.TaskManager;
import task.Todo;

public class AddTodoCommand extends Command {
    private final String[] args;
    private final TaskManager taskManager;

    public AddTodoCommand(TaskManager taskManager, String[] args)
            throws InsufficentArgumentsException {
        if (args.length == EMPTY_ARRAY_SIZE) {
            throw new InsufficentArgumentsException();
        }
        this.args = args;
        this.taskManager = taskManager;
    }

    /**
     * Add a event of type todo to list of recorded tasks.
     * 
     * @return boolean whether this command exits
     * @throws IOException thrown when saving to local storage fails
     */
    public boolean executeCommand() throws IOException {
        String title = String.join(DEFAULT_DELIMITER, args);
        this.taskManager.addTask(new Todo(title));
        return false;
    }
}
