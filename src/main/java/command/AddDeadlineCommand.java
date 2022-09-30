package command;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import exception.InsufficentArgumentsException;
import exception.InvalidTimeFormatException;
import parser.Parser;
import task.Deadline;
import task.TaskManager;

public class AddDeadlineCommand extends Command {
    private static final String DEADLINE_DIVIDER = "/by";

    private final String[] args;
    private final TaskManager taskManager;

    public AddDeadlineCommand(TaskManager taskManager, String[] args)
            throws InsufficentArgumentsException {
        if (args.length == EMPTY_ARRAY_SIZE) {
            throw new InsufficentArgumentsException();
        }
        // Edge case: No deadline passed
        if (Arrays.asList(args).indexOf(DEADLINE_DIVIDER) == NOT_FOUND_INDEX) {
            throw new InsufficentArgumentsException();
        }
        this.args = args;
        this.taskManager = taskManager;
    }

    public boolean executeCommand()
            throws IOException, InsufficentArgumentsException, InvalidTimeFormatException {
        int dividerIndex = Arrays.asList(this.args).indexOf(DEADLINE_DIVIDER);
        String title = String.join(DEFAULT_DELIMITER,
                Arrays.copyOfRange(args, DEFAULT_FIRST_INDEX, dividerIndex));
        String deadline = String.join(DEFAULT_DELIMITER,
                Arrays.copyOfRange(args, dividerIndex + DEFAULT_INDEX_INCREMENT, args.length));
        if (title.isEmpty() || deadline.isEmpty()) {
            throw new InsufficentArgumentsException();
        }
        if (!Parser.checkDeadlineFormat(deadline)) {
            throw new InvalidTimeFormatException();
        }
        taskManager.addTask(new Deadline(title, LocalDate.parse(deadline)));
        return false;
    }
}
