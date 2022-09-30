package command;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import exception.InsufficientArgumentsException;
import exception.InvalidTimeFormatException;
import parser.Parser;
import task.Deadline;
import task.TaskManager;

public class AddDeadlineCommand extends Command {
    // Constants
    private static final String DEADLINE_DIVIDER = "/by";

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
    public AddDeadlineCommand(TaskManager taskManager, String[] args)
            throws InsufficientArgumentsException {
        if (args.length == EMPTY_ARRAY_SIZE) {
            throw new InsufficientArgumentsException();
        }
        // Edge case: No deadline passed
        if (Arrays.asList(args).indexOf(DEADLINE_DIVIDER) == NOT_FOUND_INDEX) {
            throw new InsufficientArgumentsException();
        }
        this.args = args;
        this.taskManager = taskManager;
    }


    /**
     * Add a task of type deadline to list of recorded tasks.
     * 
     * @return boolean whether this command exits
     * @throws IOException thrown when saving to local storage fails
     * @throws InsufficientArgumentsException thrown when given arguments are empty strings
     * @throws InvalidTimeFormatException thrown when given timing is of the wrong format
     */
    public boolean executeCommand()
            throws IOException, InsufficientArgumentsException, InvalidTimeFormatException {
        int dividerIndex = Arrays.asList(this.args).indexOf(DEADLINE_DIVIDER);
        String title = String.join(DEFAULT_DELIMITER,
                Arrays.copyOfRange(args, DEFAULT_FIRST_INDEX, dividerIndex));
        String deadline = String.join(DEFAULT_DELIMITER,
                Arrays.copyOfRange(args, dividerIndex + DEFAULT_INDEX_INCREMENT, args.length));
        if (title.isEmpty() || deadline.isEmpty()) {
            throw new InsufficientArgumentsException();
        }
        if (!Parser.checkDeadlineFormat(deadline)) {
            throw new InvalidTimeFormatException();
        }
        taskManager.addTask(new Deadline(title, LocalDate.parse(deadline)));
        return false;
    }
}
