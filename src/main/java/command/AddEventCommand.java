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
        if (this.args.length < 3) {
            throw new InsufficientArgumentsException();
        }
        int dividerIndex = Arrays.asList(args).indexOf(DURATION_DIVIDER);
        String title = String.join(DEFAULT_DELIMITER,
                Arrays.copyOfRange(args, DEFAULT_FIRST_INDEX, dividerIndex));
        String otherArguments = String.join(DEFAULT_DELIMITER,
                Arrays.copyOfRange(args, dividerIndex + DEFAULT_INDEX_INCREMENT, args.length));
        String[] times = otherArguments.split(DEFAULT_DELIMITER);
        if (times.length < 2 || title.isEmpty()) {
            throw new InsufficientArgumentsException();
        }

        String startDateTime = times[DEFAULT_FIRST_INDEX];
        String endDateTime = times[DEFAULT_FIRST_INDEX + DEFAULT_INDEX_INCREMENT];

        if (startDateTime.isEmpty() || endDateTime.isEmpty()) {
            throw new InsufficientArgumentsException();
        }

        startDateTime += AUTOFILL_SECONDS;
        endDateTime += AUTOFILL_SECONDS;

        if (!Parser.checkDurationFormat(startDateTime)
                || !Parser.checkDurationFormat(endDateTime)) {
            throw new InvalidTimeFormatException();
        }
        taskManager.addTask(new Event(title, LocalDateTime.parse(startDateTime),
                LocalDateTime.parse(endDateTime)));
        return false;
    }
}
