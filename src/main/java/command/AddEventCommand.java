package command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import exception.InsufficientArgumentsException;
import exception.InvalidTimeFormatException;
import parser.Parser;
import task.Event;
import task.TaskManager;

public class AddEventCommand extends Command {
    // Constants
    private static final String AUTOFILL_SECONDS = ":00";
    private static final String DURATION_DIVIDER = "/at";

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
    public AddEventCommand(TaskManager taskManager, String[] args)
            throws InsufficientArgumentsException {
        if (args.length == 0) {
            throw new InsufficientArgumentsException();
        }
        // Edge case: No duration passed
        if (Arrays.asList(args).indexOf(DURATION_DIVIDER) == NOT_FOUND_INDEX) {
            throw new InsufficientArgumentsException();
        }
        this.args = args;
        this.taskManager = taskManager;
    }



    /**
     * Add a task of type event to list of recorded tasks.
     * 
     * @return boolean whether this command exits
     * @throws IOException thrown when saving to local storage fails
     * @throws InsufficientArgumentsException thrown when given arguments are empty strings
     * @throws InvalidTimeFormatException thrown when given timing is of the wrong format
     */
    public boolean executeCommand()
            throws IOException, InsufficientArgumentsException, InvalidTimeFormatException {
        int dividerIndex = Arrays.asList(args).indexOf(DURATION_DIVIDER);
        String title = String.join(DEFAULT_DELIMITER,
                Arrays.copyOfRange(args, DEFAULT_FIRST_INDEX, dividerIndex));
        String duration = String.join(DEFAULT_DELIMITER,
                Arrays.copyOfRange(args, dividerIndex + DEFAULT_INDEX_INCREMENT, args.length));
        if (title.isEmpty() || duration.isEmpty()) {
            throw new InsufficientArgumentsException();
        }
        duration = duration + AUTOFILL_SECONDS;
        if (!Parser.checkDurationFormat(duration)) {
            throw new InvalidTimeFormatException();
        }
        taskManager.addTask(new Event(title, LocalDateTime.parse(duration)));
        return false;
    }
}
