package command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import exception.InsufficentArgumentsException;
import exception.InvalidTimeFormatException;
import parser.Parser;
import task.Event;
import task.TaskManager;

public class AddEventCommand extends Command {
    private static final String AUTOFILL_SECONDS = ":00";
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

    public boolean executeCommand()
            throws IOException, InsufficentArgumentsException, InvalidTimeFormatException {
        int dividerIndex = Arrays.asList(args).indexOf(DURATION_DIVIDER);
        String title = String.join(DEFAULT_DELIMITER,
                Arrays.copyOfRange(args, DEFAULT_FIRST_INDEX, dividerIndex));
        String duration = String.join(DEFAULT_DELIMITER,
                Arrays.copyOfRange(args, dividerIndex + DEFAULT_INDEX_INCREMENT, args.length));
        if (title.isEmpty() || duration.isEmpty()) {
            throw new InsufficentArgumentsException();
        }
        duration = duration + AUTOFILL_SECONDS;
        if (!Parser.checkDurationFormat(duration)) {
            throw new InvalidTimeFormatException();
        }
        taskManager.addTask(new Event(title, LocalDateTime.parse(duration)));
        return false;
    }
}
