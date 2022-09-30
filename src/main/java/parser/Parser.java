package parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.Command;
import command.DeleteTaskCommand;
import command.ExitCommand;
import command.GetTaskWithTitle;
import command.GetTaskWithDate;
import command.ListTasksCommand;
import command.MarkTaskCommand;
import command.UnmarkTaskCommand;
import exception.CommandNotFoundException;
import exception.InsufficientArgumentsException;
import exception.InvalidFileDataException;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskManager;
import task.Todo;

public class Parser {
    /*
     * Constants line separated by utility
     */
    private static final String DEFAULT_DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int DEFAULT_FIRST_INDEX = 0;
    private static final int ARGS_INDEX = 1;

    private static final String DATA_TOKEN_SEPARATOR = " | ";
    private static final String DATA_TOKEN_SEPARATOR_REGEX = " \\| ";

    private static final int TASK_TYPE_INDEX = 0;
    private static final int TASK_STATUS_INDEX = 4;
    private static final int TASK_TITLE_INDEX = 8;
    private static final int TASK_TIMING_INDEX = 1;

    private static final String TODO_TASK_TYPE = "T";
    private static final String DEADLINE_TASK_TYPE = "D";
    private static final String EVENT_TASK_TYPE = "E";

    private static final int TASK_STATUS_MARKED = 1;
    private static final int TASK_STATUS_UNMARKED = 0;

    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE_TASK = "delete";
    private static final String COMMAND_MARK_TASK = "mark";
    private static final String COMMAND_UNMARK_TASK = "unmark";
    private static final String COMMAND_SEARCH_TITLE = "search-title";
    private static final String COMMAND_SEARCH_DATE = "search-date";
    private static final String COMMAND_EXIT = "bye";

    private static final String AUTOFILL_SECONDS = ":00";

    public static final String DATETIME_FORMAT_REGEX = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";
    public static final DateTimeFormatter DATETIME_DECODE_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd YYYY HH:mm a");
    public static final String DATE_FORMAT_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    public static final DateTimeFormatter DATE_DECODE_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd YYYY");

    // Properties
    private static final List<String> VALID_COMMAND_LIST = List.of(COMMAND_LIST, COMMAND_TODO,
            COMMAND_DEADLINE, COMMAND_EVENT, COMMAND_DELETE_TASK, COMMAND_MARK_TASK,
            COMMAND_UNMARK_TASK, COMMAND_SEARCH_TITLE, COMMAND_SEARCH_DATE, COMMAND_EXIT);


    /**
     * Parses a line of user input into a usable form.
     * 
     * @param input a single line of user input.
     * @return A list of String[] where first index is command and second is arguments.
     */
    private static final List<String[]> parseUserInput(String input) {
        String[] inputs = input.split(DEFAULT_DELIMITER);
        String[] command = Arrays.copyOfRange(inputs, COMMAND_INDEX, COMMAND_INDEX + 1);
        String[] args = Arrays.copyOfRange(inputs, COMMAND_INDEX + 1, inputs.length);
        return List.of(command, args);
    }

    /**
     * Gets the first word from user input, which is the command.
     * 
     * @param input a single line of user input
     * @return String
     */
    public static final String getCommand(String input) {
        return parseUserInput(input).get(COMMAND_INDEX)[COMMAND_INDEX];
    }

    /**
     * Gets all subsequent words from user input, which are the arguments.
     * 
     * @param input a single line of user input
     * @return String[] the arguments for a command
     */
    public static final String[] getArgs(String input) {
        return parseUserInput(input).get(ARGS_INDEX);
    }

    /**
     * Creaetes a specific command that represents what the user wants to do.
     * 
     * @param taskManager a manager containing a list of all recorded tasks
     * @param input a single line of user input
     * @return Command
     * @throws CommandNotFoundException thrown when a given command does not exist
     * @throws InsufficientArgumentsException thrown when a given command does not have enough
     *         arguments to run
     */
    public static final Command createCommand(TaskManager taskManager, String input)
            throws CommandNotFoundException, InsufficientArgumentsException {
        String command = getCommand(input);
        String[] args = getArgs(input);
        if (!VALID_COMMAND_LIST.contains(command)) {
            throw new CommandNotFoundException();
        }
        // assert that command exists
        switch (command) {
        case COMMAND_LIST:
            return new ListTasksCommand(taskManager);
        case COMMAND_TODO:
            return new AddTodoCommand(taskManager, args);
        case COMMAND_DEADLINE:
            return new AddDeadlineCommand(taskManager, args);
        case COMMAND_EVENT:
            return new AddEventCommand(taskManager, args);
        case COMMAND_DELETE_TASK:
            return new DeleteTaskCommand(taskManager, args);
        case COMMAND_MARK_TASK:
            return new MarkTaskCommand(taskManager, args);
        case COMMAND_UNMARK_TASK:
            return new UnmarkTaskCommand(taskManager, args);
        case COMMAND_SEARCH_TITLE:
            return new GetTaskWithTitle(taskManager, args);
        case COMMAND_SEARCH_DATE:
            return new GetTaskWithDate(taskManager, args);
        default:
            return new ExitCommand();
        }
    }

    /**
     * Checks a given deadline for the format required for LocalDate.
     * 
     * @param deadline the deadline to check with
     * @return boolean
     */
    public static final boolean checkDeadlineFormat(String deadline) {
        return deadline.matches(DATE_FORMAT_REGEX);
    }

    /**
     * Checks a given duration for the format required for LocalDateTime.
     * 
     * @param duration the duration to check with
     * @return boolean
     */
    public static final boolean checkDurationFormat(String duration) {
        return duration.matches(DATETIME_FORMAT_REGEX);
    }

    /**
     * Converts a task to a string of a specific format for saving to local storage.
     * 
     * @param task the target task to be converted to a string
     * @return String
     */
    public static final String stringifyTask(Task task) {
        String taskType = task.getTaskType();
        boolean taskStatus = task.getStatus();
        int taskStatusNumber = taskStatus ? 1 : 0;
        String taskTitle = task.getTitle();
        if (taskType.equals(TODO_TASK_TYPE)) {
            return taskType + DATA_TOKEN_SEPARATOR + taskStatusNumber + DATA_TOKEN_SEPARATOR
                    + taskTitle;
        }
        String taskTiming = task.getTaskTiming();
        return taskType + DATA_TOKEN_SEPARATOR + taskStatusNumber + DATA_TOKEN_SEPARATOR + taskTitle
                + DATA_TOKEN_SEPARATOR + taskTiming;
    }

    /**
     * Converts a list of tasks to a specific format for saving to local storage.
     * 
     * @param tasks A list of Task
     */
    public static final String stringifyTasks(List<Task> tasks) {
        String fileContent = "";
        for (Task task : tasks) {
            fileContent += stringifyTask(task) + System.lineSeparator();
        }
        return fileContent;
    }

    /**
     * Given a line from local storage, returns a Task that is constructed from it.
     * 
     * @param data a single line from local storage
     * @return Task
     * @throws InvalidFileDataException thrown when the data is not of the correct format
     */
    public static final Task parseDataToTask(String data) throws InvalidFileDataException {
        try {
            String taskType = data.substring(TASK_TYPE_INDEX, TASK_TYPE_INDEX + 1);
            // Task type is not specified
            if (!taskType.equals(TODO_TASK_TYPE) && !taskType.equals(DEADLINE_TASK_TYPE)
                    && !taskType.equals(EVENT_TASK_TYPE)) {
                throw new InvalidFileDataException();
            }

            Integer taskStatusNumber =
                    Integer.parseInt(data.substring(TASK_STATUS_INDEX, TASK_STATUS_INDEX + 1));
            // Task Status Number is not valid
            if (!taskStatusNumber.equals(TASK_STATUS_MARKED)
                    && !taskStatusNumber.equals(TASK_STATUS_UNMARKED)) {
                throw new InvalidFileDataException();
            }
            boolean taskStatus = taskStatusNumber.equals(TASK_STATUS_MARKED);

            String[] otherData = data.substring(TASK_TITLE_INDEX, data.length())
                    .split(DATA_TOKEN_SEPARATOR_REGEX);
            String taskTitle = otherData[DEFAULT_FIRST_INDEX];
            if (taskType.equals(TODO_TASK_TYPE)) { // no deadline or duration
                return new Todo(taskTitle, taskStatus);
            }

            String taskTiming = otherData[TASK_TIMING_INDEX];
            if (taskType.equals(DEADLINE_TASK_TYPE)) {
                if (!checkDeadlineFormat(taskTiming)) {
                    throw new InvalidFileDataException();
                }
                return new Deadline(taskTitle, LocalDate.parse(taskTiming), taskStatus);
            }
            taskTiming += AUTOFILL_SECONDS;
            if (!checkDurationFormat(taskTiming)) {
                throw new InvalidFileDataException();
            }
            return new Event(taskTitle, LocalDateTime.parse(taskTiming), taskStatus);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidFileDataException();
        }
    }
}
