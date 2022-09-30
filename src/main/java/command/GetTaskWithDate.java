package command;

import java.time.LocalDate;
import exception.InsufficentArgumentsException;
import exception.InvalidTimeFormatException;
import parser.Parser;
import task.TaskManager;

public class GetTaskWithDate extends Command {
    private final String[] args;
    private final TaskManager taskManager;

    public GetTaskWithDate(TaskManager taskManager, String[] args)
            throws InsufficentArgumentsException {
        if (args.length == EMPTY_ARRAY_SIZE) {
            throw new InsufficentArgumentsException();
        }
        this.args = args;
        this.taskManager = taskManager;
    }

    public boolean executeCommand()
            throws InsufficentArgumentsException, InvalidTimeFormatException {
        String deadline = this.args[DEFAULT_FIRST_INDEX];
        if (deadline.isEmpty()) {
            throw new InsufficentArgumentsException();
        }
        if (!Parser.checkDeadlineFormat(deadline)) {
            throw new InvalidTimeFormatException();
        }
        taskManager.listTasks(taskManager.searchByDate(LocalDate.parse(deadline)));
        return false;
    }
}
