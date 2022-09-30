package command;

import java.io.IOException;
import java.util.Arrays;
import exception.InsufficentArgumentsException;
import task.Event;
import task.TaskManager;

public class AddEventCommand extends Command {
    private static final String DURATION_DIVIDER = "/at";

    private final String[] args;
    private final TaskManager taskManager;

    public AddEventCommand(TaskManager taskManager, String[] args)
            throws InsufficentArgumentsException {
        if (args.length == 0) {
            throw new InsufficentArgumentsException();
        }
        // Edge case: No duration passed
        if (Arrays.asList(args).indexOf(DURATION_DIVIDER) == NOT_FOUND_INDEX) {
            throw new InsufficentArgumentsException();
        }
        this.args = args;
        this.taskManager = taskManager;
    }

    /**
     * Add a event of type event to list of recorded tasks.
     * 
     * @return boolean whether this command exits
     * @throws IOException thrown when saving to local storage fails
     */
    public boolean executeCommand() throws IOException {
        int dividerIndex = Arrays.asList(args).indexOf(DURATION_DIVIDER);
        String title = String.join(DEFAULT_DELIMITER,
                Arrays.copyOfRange(args, DEFAULT_FIRST_INDEX, dividerIndex));
        String duration = String.join(DEFAULT_DELIMITER,
                Arrays.copyOfRange(args, dividerIndex + DEFAULT_INDEX_INCREMENT, args.length));
        taskManager.addTask(new Event(title, duration));
        return false;
    }
}
