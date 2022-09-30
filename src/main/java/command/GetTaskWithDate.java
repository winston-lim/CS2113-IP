package command;

import java.time.LocalDate;
import exception.InsufficientArgumentsException;
import exception.InvalidTimeFormatException;
import parser.Parser;
import task.TaskManager;

public class GetTaskWithDate extends Command {
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
    public GetTaskWithDate(TaskManager taskManager, String[] args)
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
     * @throws InvalidTimeFormatException thrown when time given is of the wrong format
     */
    public boolean executeCommand()
            throws InsufficientArgumentsException, InvalidTimeFormatException {
        String deadline = this.args[DEFAULT_FIRST_INDEX];
        if (deadline.isEmpty()) {
            throw new InsufficientArgumentsException();
        }
        if (!Parser.checkDeadlineFormat(deadline)) {
            throw new InvalidTimeFormatException();
        }
        taskManager.listTasks(taskManager.searchByDate(LocalDate.parse(deadline)));
        return false;
    }
}
